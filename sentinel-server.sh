#!/bin/bash
java -Dserver.port=8088 -Dsentinel.dashboard.auth.username=sentinel -Dsentinel.dashboard.auth.password=123456 -jar sentinel-dashboard-1.8.0.jar