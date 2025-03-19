rd /s /q "./logmonitor"
openapi-generator-cli generate -g spring -i ./docs/spec/services/log-monitoring/openapi.yaml -o ./logmonitor ^
--additional-properties=^
useSpringBoot3=true,^
basePackage=nl.utwente.sosoc.logmonitor,^
modelPackage=nl.utwente.sosoc.logmonitor.model,^
apiPackage=nl.utwente.sosoc.logmonitor.api,^
configPackage=nl.utwente.sosoc.logmonitor.configuration,^
generateModels=true