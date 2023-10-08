FROM       swr.bj-kjy-12.cloud.clic/cloud-chinalife/jdk:1.8-centos7
ENV  LANG   C.UTF-8
RUN  mkdir        /data
Add  target/resManage-1.0.0-SNAPSHOT.jar /data
WORKDIR           /data
CMD   java  -jar  resManage-1.0.0-SNAPSHOT.jar