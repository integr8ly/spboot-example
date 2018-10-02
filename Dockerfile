FROM maven:alpine AS build
COPY pom.xml .
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  

RUN mvn -f /usr/src/app/pom.xml -e -Popenshift -Dcom.redhat.xpaas.repo.redhatga -Dfabric8.skip=true package --batch-mode -Djava.net.preferIPv4Stack=true -pl .
FROM openjdk:8-jdk-alpine
ADD . ./spboot-crud-app
COPY --from=build /usr/src/app/target/booster-1.0.0-SNAPSHOT.jar /spboot-crud-app/booster-1.0.0-SNAPSHOT.jar
VOLUME /tmp
ENTRYPOINT ["java","-Dspring.profiles.active=openshift","-Xms192m","-Xmx768m","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-XX:+UseParallelOldGC","-XX:MinHeapFreeRatio=10","-XX:MaxHeapFreeRatio=20","-XX:GCTimeRatio=4","-XX:AdaptiveSizePolicyWeight=90","-XX:MaxMetaspaceSize=100m","-XX:ParallelGCThreads=1","-Djava.util.concurrent.ForkJoinPool.common.parallelism=1","-XX:CICompilerCount=2","-XX:+ExitOnOutOfMemoryError","-cp",".","-jar","/spboot-crud-app/booster-1.0.0-SNAPSHOT.jar"]