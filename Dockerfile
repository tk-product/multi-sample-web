# 1. ビルド用イメージ
FROM gradle:8.7-jdk21 AS build

WORKDIR /app

# プロジェクト全体をコピー（root + sub）
COPY . .

# Gradle Wrapper 実行権限付与
RUN chmod +x multi-sample-web/gradlew

# multi-sample-web をビルド（sub-project は settings.gradle で自動依存）
RUN ./multi-sample-web/gradlew :multi-sample-web:clean :multi-sample-web:bootJar --no-daemon

# 2. 実行用イメージ
FROM openjdk:21-jdk-slim
WORKDIR /app

# ビルド成果物をコピー
COPY --from=build /app/multi-sample-web/build/libs/*.jar app.jar

# コンテナ起動時のコマンド
ENTRYPOINT ["java", "-jar", "app.jar"]
