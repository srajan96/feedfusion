#!/bin/bash
echo $TRAVISKEY|cat > key
cat key
chmod 600 key
ssh -i key.pem deploy@139.59.61.26<<EOF
cd feedfusion
git pull
mvn package
cp target/hello.war /var/lib/tomcat8/webapps
echo "deployed"
EOF

