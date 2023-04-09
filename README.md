# Проект "Pooh JMS"

[![Java CI](https://github.com/PerpetuumEbner/job4j_pooh/actions/workflows/maven.yml/badge.svg)](https://github.com/PerpetuumEbner/job4j_pooh/actions/workflows/maven.yml)
## Общее описание:

Аналог асинхронной очереди.

* Приложение запускает Socket и ждет клиентов.
* Клиенты могут быть двух типов: отправители (publisher), получатели (subscriber).
* В качестве клиента cURL.
* В качестве протокола HTTP.

***

## Технологии:
[![java](https://img.shields.io/badge/java-17-red)](https://www.java.com/)
[![maven](https://img.shields.io/badge/apache--maven-3.8.3-blue)](https://maven.apache.org/)

***

## Запуск проекта:
* maven install
* java -jar target/job4j_pooh-1.0.jar