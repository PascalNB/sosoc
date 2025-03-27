from typing import Any

import yaml
import os
import argparse
import posixpath
from pathlib import Path
import openapi_generator_cli


def get_generation_command(input_path, output_name) -> list[str]:
    return [
        'generate', '-g', 'spring', '-i', input_path, '-o', f'services/{output_name}/generated-sources',
        '--additional-properties='
        f'title={output_name},'
        'useSpringBoot3=true,'
        'sourceFolder=./,'
        f'artifactId={output_name},'
        f'groupId=nl.utwente.sosoc,'
        f'basePackage=nl.utwente.sosoc.{output_name},'
        f'modelPackage=nl.utwente.sosoc.{output_name}.model,'
        f'apiPackage=nl.utwente.sosoc.{output_name}.api,'
        f'configPackage=nl.utwente.sosoc.{output_name}.configuration,'
        'interfaceOnly=true,'
        'useTags=true,'
        'generateSupportingFiles=true,'
        'dateLibrary=java8'
    ]


def load_yaml(file_path):
    """Load a YAML file and return its contents as a dictionary."""
    with open(file_path, 'r') as file:
        return yaml.safe_load(file)


def flatten_openapi_spec(input_path) -> Any:
    """Flatten an OpenAPI spec by resolving external references into internal components."""
    with open(input_path, 'r') as file:
        openapi_spec = yaml.safe_load(file)

    components = openapi_spec.get('components', {}).get('schemas', {})
    base_path = os.path.dirname(input_path)

    resolve_references(openapi_spec, components, base_path, dict())

    if 'components' not in openapi_spec:
        openapi_spec['components'] = {}
    openapi_spec['components']['schemas'] = components

    return openapi_spec


def resolve_references(node, components, base_path, resolved_cache):
    """Recursively resolve external $ref references and replace them with internal schema references."""
    if isinstance(node, dict):
        for key, value in list(node.items()):
            if isinstance(value, dict) and '$ref' in value:
                ref_path: str = value['$ref']
                if ref_path.startswith('#'):
                    continue  # Already an internal reference, skip

                ref_file, ref_name = ref_path.split('#', maxsplit=1)
                ref_file_path = posixpath.normpath(posixpath.join(base_path, ref_file))

                if ref_file_path in resolved_cache:
                    schema_yaml = resolved_cache[ref_file_path]  # Already loaded before
                else:
                    schema_yaml = load_yaml(ref_file_path)
                    resolved_cache[ref_file_path] = schema_yaml  # Store to avoid reloading

                for schema_name, schema_content in list(schema_yaml["components"]["schemas"].items()):
                    if schema_name not in components:  # Avoid overwriting if already included
                        components[schema_name] = schema_content
                        # Recursively resolve references in each schema
                        parent_dir = posixpath.dirname(ref_file_path)
                        resolve_references(components[schema_name], components, parent_dir, resolved_cache)

                schema_name = ref_name.split('/')[-1]  # Extract the schema name
                # Replace external $ref with an internal reference
                value['$ref'] = f'#/components/schemas/{schema_name}'
            else:
                resolve_references(value, components, base_path, resolved_cache)


def execute_source_generation(openapi_spec, output_name):
    output_path = posixpath.normpath(posixpath.join('services', output_name, 'generated-sources'))
    os.makedirs(output_path, exist_ok=True)
    file_path = posixpath.normpath(posixpath.join(output_path, 'spec.yml'))
    try:
        with open(file_path, 'w') as f:
            yaml.dump(openapi_spec, f, default_flow_style=False)
            relative_path = file_path.replace('\\', '/')
            command_args = get_generation_command(relative_path, output_name)
            openapi_generator_cli.run(command_args)
    finally:
        Path(file_path).unlink()


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Flatten an OpenAPI spec by resolving external $ref references.")
    parser.add_argument("i", help="Path to the input OpenAPI YAML file")
    parser.add_argument("o", help="Name of the generated Java project")
    args = parser.parse_args()
    spec = flatten_openapi_spec(args.i.replace('\\', '/'))
    execute_source_generation(spec, args.o)
