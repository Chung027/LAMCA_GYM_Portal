# LAMCA GYM Portal

A web application for a fictional gym, **LAMCA GYM**, where members can create an
account, browse the weekly class schedule, and book or cancel training sessions.

The project was originally built as a school assignment together with my classmates.
I'm now continuing its development by adding new features and integrating additional technologies.

## Tech stack

| Layer       | Technology                                                         |
| ----------- | ------------------------------------------------------------------ |
| Backend     | Java 21, Spring Boot 3.2, Spring Data JPA / Hibernate, Spring Mail |
| Security    | Spring Security (BCrypt password hashing)                          |
| Frontend    | HTML, CSS, JavaScript, Thymeleaf, FullCalendar                     |
| Database    | MySQL 8                                                            |
| Build & Run | Maven, Docker, Docker Compose                                      |

## Features

- **User accounts** – register and log in. Passwords are hashed with BCrypt.
- **Class booking** – an interactive weekly calendar shows every session with its
  instructor and available seats. Members can book and cancel classes.
- **Persistent bookings** – bookings are stored in the database, so a member's booked
  sessions stay highlighted (in blue) even after logging out and back in.
- **Contact form** – visitors can send a message that is delivered to the gym's inbox
  via Gmail SMTP.
- **Forgot password** – users can request a secure, time-limited reset link by email
  and set a new password.
- **Dockerised** – the whole stack (application + MySQL) runs with a single command.

## Quick start (Docker – recommended)

**1. Create a `.env` file** for VSC in the project root and set environment variables in your IDE's run configuration:

```env
MYSQL_USERNAME=root
MYSQL_PASSWORD=your_database_password
GMAIL_USERNAME=your_address@gmail.com
GMAIL_PASSWORD=your_gmail_app_password
```

> **Gmail note:** `GMAIL_PASSWORD` must be a Google **App Password**, not your normal Gmail password.

**2. Build and start the app and database:**
Build base image (replace X.Y.Z with a version number):
```bash
docker build -t lamca-gym:X.Y.Z .
```

Start the containers
```bash
docker compose up -d
```

**3. Open the app:**

```
http://localhost:8080
```

## Running locally (without Docker)

Requirements: **Java 21**, **Maven**, and a running **MySQL 8** instance with a
database named `lamca_database`.

1. Set the same environment variables as above (e.g. in your IDE's run configuration).
2. Make sure `spring.datasource.url` points at your local MySQL.
3. Start the application:

```bash
./mvnw spring-boot:run
```

## Configuration

All secrets are provided at runtime through environment variables and are read by
`application.properties`. Nothing sensitive is committed to the repository — the
`.env` file is git-ignored.

| Variable         | Description                                                     |
| ---------------- | --------------------------------------------------------------- |
| `MYSQL_USERNAME` | Database user the application connects with                     |
| `MYSQL_PASSWORD` | Password for that user (also the MySQL root password in Docker) |
| `GMAIL_USERNAME` | Gmail address used to send contact / reset emails               |
| `GMAIL_PASSWORD` | Gmail **App Password** for that address                         |
