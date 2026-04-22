@echo off
start cmd /k "cd config-server && mvn spring-boot:run"
timeout /t 10
start cmd /k "cd eureka-server && mvn spring-boot:run"
timeout /t 10
start cmd /k "cd api-gateway && mvn spring-boot:run"
timeout /t 10
start cmd /k "cd auth-service && mvn spring-boot:run"