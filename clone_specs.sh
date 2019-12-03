#!/bin/sh
set -ex
gem install bundler
wget https://github.com/8thlight/http_server_spec/archive/master.tar.gz
tar -xvzf master.tar.gz
cd http_server_spec-master && bundle install
bundle exec spinach --tags @simple-get