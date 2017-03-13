#!/bin/bash
rm travis.enc
chmod 600 travis
ssh -i travis deploy@139.59.61.26<<EOF
cd feedfusion
git pull
mvn package
cp target/hello.war /var/lib/tomcat8/webapps/feedfusion.war
echo "deployed"
EOF

