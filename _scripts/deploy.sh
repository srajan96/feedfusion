#!/bin/bash
rm  -r deploy.enc
chmod 600 deploy
ssh -i deploy deploy@139.59.12.220<<EOF
cd feedfusion
git pull
mvn package
rm /var/lib/tomcat8/webapps/feedfusiontest.war
cp target/feedfusiontest.war /var/lib/tomcat8/webapps/feedfusiontest.war
echo "deployed"
EOF
