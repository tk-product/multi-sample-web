# 1. �r���h�p�C���[�W
FROM gradle:8.7-jdk21 AS build

WORKDIR /app

# �v���W�F�N�g�S�̂��R�s�[�iroot + sub�j
COPY . .

# Gradle Wrapper ���s�����t�^
RUN chmod +x multi-sample-web/gradlew

# multi-sample-web ���r���h�isub-project �� settings.gradle �Ŏ����ˑ��j
RUN ./multi-sample-web/gradlew :multi-sample-web:clean :multi-sample-web:bootJar --no-daemon

# 2. ���s�p�C���[�W
FROM openjdk:21-jdk-slim
WORKDIR /app

# �r���h���ʕ����R�s�[
COPY --from=build /app/multi-sample-web/build/libs/*.jar app.jar

# �R���e�i�N�����̃R�}���h
ENTRYPOINT ["java", "-jar", "app.jar"]
