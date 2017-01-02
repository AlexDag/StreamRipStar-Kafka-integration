#!/bin/sh
export pp=./lib

java -Dkafka_streaming="190.0.01::9092" -classpath $pp/snappy-java-1.0.5.jar:$pp/zkclient-0.3.jar:$pp/zookeeper-3.3.4.jar:$pp/slf4j-api-1.7.2.jar:$pp/scala-library-2.9.2.jar:$pp/metrics-core-2.2.0.jar:$pp/log4j-1.2.15.jar:$pp/kafka_2.9.2-0.8.1.1.jar:$pp/jopt-simple-3.2.jar:$pp/jline-0.9.94.jar:$pp/activation-1.1.jar:$pp/snappy-java-1.0.5.jar:$pp/zkclient-0.3.jar:$pp/zookeeper-3.3.4.jar:$pp/json-simple-1.1.1.jar:./StreamRipStar.jar:$pp/jna-3.2.4.jar misc.StreamRipStar 
#

