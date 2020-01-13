#!/bin/sh
set -ex
gem install bundler
wget https://github.com/mbdebbeler/http_server_spec/archive/master.tar.gz
tar -xvzf master.tar.gz
cd http_server_spec-master && bundle install
bundle exec spinach --tags @not-allowed,@not-found,@simple-get,@simple-head,@simple-options,@simple-post,@simple-redirect
bundle exec spinach --tags @image-get
bundle exec spinach --tags @image-post-1
bundle exec spinach --tags @image-post-2
bundle exec spinach --tags @image-post-3
bundle exec spinach --tags @image-delete-1
bundle exec spinach --tags @image-delete-2
bundle exec spinach --tags @image-delete-3