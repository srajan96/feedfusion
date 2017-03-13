#!/bin/bash
ssh deploy@139.59.61.26<<EOF
cd feedfusion
git pull
mvn package
cp target/hello.war /var/lib/tomcat8/webapps
echo "deployed"
EOF

