#!/bin/bash
rm  -r deploy.enc
chmod 600 deploy
ssh -i deploy deploy@139.59.64.249<<EOF
cd feedfusion
git pull
mvn package
rm /var/lib/tomcat8/webapps/feedfusion.war
cp target/feedfusiontest.war /var/lib/tomcat8/webapps/feedfusion.war
echo "deployed"
EOF
