#!/bin/bash
# shellcheck disable=SC2164

#Переходим в директорию ticket.sales.service c файлом Dockerfile и выполняем сборку образа
cd ../ticket.sales.service
./gradlew bootJar

#Переходим в директорию security.service c файлом Dockerfile и выполняем сборку образа
cd ../security.service
./gradlew bootJar

# Переходим в директорию dev-env и выполняем команду docker-compose
cd ../dev-env
docker-compose up --build