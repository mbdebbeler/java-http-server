#!/bin/sh
cd ../http_server_spec
bundle exec spinach --tags @not-allowed,@not-found,@simple-get,@simple-head,@simple-options,@simple-post,@simple-redirect
bundle exec spinach --tags @image-get
bundle exec spinach --tags @image-post-1
bundle exec spinach --tags @image-post-2
bundle exec spinach --tags @image-post-3
bundle exec spinach --tags @image-delete-1
bundle exec spinach --tags @image-delete-2
bundle exec spinach --tags @image-delete-3