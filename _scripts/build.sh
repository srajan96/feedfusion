#!/bin/bash
set -x # Show the output of the following commands (useful for debugging)
    
# Import the SSH deployment key
openssl aes-256-cbc -K $encrypted_22009518e18d_key -iv $encrypted_22009518e18d_iv -in travis.enc -out travis -d
rm travis.enc # Don't need it anymore
chmod 600 travis
mv travis ~/.ssh/id_rsa
    
# Install zopfli
git clone https://code.google.com/p/zopfli/
cd zopfli
make
chmod +x zopfli
cd ..