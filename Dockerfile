FROM openjdk:jdk-slim
MAINTAINER indyaah@gmail.com

COPY ./target/rest-*.jar /tmp/
COPY ./run.sh /run.sh

EXPOSE 8080
CMD ["/run.sh"]