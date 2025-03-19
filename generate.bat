rd /s /q "./services/alertnotify"
python ./generate-sources.py ./docs/spec/services/alert-notify/openapi.yaml alertnotify

rd /s /q "./services/automatedresponse"
python ./generate-sources.py ./docs/spec/services/automated-response/openapi.yaml automatedresponse

rd /s /q "./services/identitymanagement"
python ./generate-sources.py ./docs/spec/services/identity-management/openapi.yaml identitymanagement

rd /s /q "./services/logmonitor"
python ./generate-sources.py ./docs/spec/services/log-monitoring/openapi.yaml logmonitor

rd /s /q "./services/playbookmanagement"
python ./generate-sources.py ./docs/spec/services/playbook-management/openapi.yaml playbookmanagement

rd /s /q "./services/threatintelligence"
python ./generate-sources.py ./docs/spec/services/threat-intelligence/openapi.yaml threatintelligence