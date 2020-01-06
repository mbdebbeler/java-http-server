#!/bin/sh
set -ex
gem install bundler
wget https://github.com/mbdebbeler/http_server_spec/archive/master.tar.gz
tar -xvzf master.tar.gz
cd http_server_spec-master && bundle install
sed -i -e "s/127.0.0.1/${AWS_EC2_ADDRESS}/" ./features/support/config.yml
bundle exec spinach
