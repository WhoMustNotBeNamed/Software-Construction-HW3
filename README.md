# Домашнее задание №3

## Евсюков Александр </br> Группа БПИ224

### Набор требований к проекту:

* `Java` версии 21
* `Gradle` версии 8.2
* `Spring Boot` версии 3.2.2

### Инструкция по запуску:

1. Склонируйте репозиторий с помощью команды `git clone`
2. Откройте проект в `IntelliJ IDEA`
3. Перейдите в директорию `dev-env` и запустите shell скрипт командой `./run-script.sh` (если вы пользуетесь Windows, то
   запустите скрипт из терминала Bash)


P.S. Если у вас возникли проблемы с запуском, то попробуйте убрать запуск `SecurityService` и `TicketSalesService` из `docker-compose.yml` и
   запустить их отдельно.

### Инструкция по использованию:

Основным сервисом является `SecurityService`, который позволяет пользователю войти в систему и получить токен для работы
с заказами. Также позволяет
управлять заказами за счет связи двух микросервисов через `OpenFeign`.

```
http://localhost:8080/swagger-ui/index.html
```

Сервис `TicketSalesService` предоставляет методы для работы с заказами.

```
http://localhost:8090/swagger-ui/index.html
```

Пользователю доступны следующие станции на выбор:
*  Moscow
*  Saint-Petersburg
*  Novosibirsk
*  Yekaterinburg
*  Kazan
*  Nizhny Novgorod
*  Ufa
*  Volgograd
*  Perm
*  Krasnoyarsk
*  Voronezh

### Структура проектов:

![img_2.png](screenshots/img_2.png) ![img.png](screenshots/img.png)

