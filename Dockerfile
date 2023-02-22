# 베이스 이미지를 지정
FROM adoptopenjdk/openjdk11:latest

# 번역해보면 spring boot의 Tomcat의 default 저장소가 /tmp인데
# 위와 같이 볼륨 마운트를 해주면 호스트의 /var/lib/docker에 임시파일을 만들고 컨테이너 안의 /tmp 와 연결할 수 있다는 뜻입니다.
# VOLUME /tmp

ARG JAR_FILE=./build/libs/todoit-0.0.1-SNAPSHOT.jar

# src(로컬) -> dest(이미지) 위치로 복사
COPY ${JAR_FILE} app.jar

#환경변수 설정
ENV JAVA_OPTS=""

# 프로젝트를 실행하는 명령
ENTRYPOINT ["java","-jar", "app.jar"]

