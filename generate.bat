rd /s /q "./services/alertnotify/generated-sources"
python ./generate-sources.py ./docs/spec/services/alert-notify/openapi.yaml alertnotify

rd /s /q "./services/automatedresponse/generated-sources"
python ./generate-sources.py ./docs/spec/services/automated-response/openapi.yaml automatedresponse

rd /s /q "./services/identitymanagement/generated-sources"
python ./generate-sources.py ./docs/spec/services/identity-management/openapi.yaml identitymanagement

rd /s /q "./services/logmonitor/generated-sources"
python ./generate-sources.py ./docs/spec/services/log-monitoring/openapi.yaml logmonitor

rd /s /q "./services/playbookmanagement/generated-sources"
python ./generate-sources.py ./docs/spec/services/playbook-management/openapi.yaml playbookmanagement

rd /s /q "./services/threatintelligence/generated-sources"
python ./generate-sources.py ./docs/spec/services/threat-intelligence/openapi.yaml threatintelligence

rd /s /q "./services/userinterface/generated-sources"
python ./generate-sources.py ./docs/spec/services/user-interface/openapi.yaml userinterface