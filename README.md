# Railway Ticket Notification System

Railway ticket notification system. This system is an extension of `Railway Ticket Management System`. This system is
responsible for sending notification (SMS/Email).

## Table of Contents

- [Technology Stack](#technology-stack)
- [Project Setup](#project-setup)

## Technology Stack

This repository is built upon following technologies:

* Java 21
* Spring Boot 3.3.x
* Kafka

## Project Setup

To build and run the project, follow these steps:

* First clone the ticket management repository by running the command
  `git clone https://github.com/mdgiasuddin/railway-ticket-management.git` & follow its instruction to run this project.
* Clone the repository: `git clone https://github.com/mdgiasuddin/railway-ticket-notification.git`.
* Change the properties `spring.mail.username` & `spring.mail.password` in the `application.properties` file by
  replacing `${MAIL_USERNAME}` & `${MAIL_PASSWORD}` with your suitable email & its password.
* Run the project.