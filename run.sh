#!/bin/bash
java -jar `ls /tmp/rest-*.jar` --spring.config.location=classpath:docker.properties