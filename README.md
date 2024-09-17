# Task Management System

## Loyihaning Tavsifi

Task Management System (TMS) — vazifalarni yaratish, ko‘rish, yangilash va o‘chirish imkoniyatlarini taqdim etuvchi REST
API. Ushbu loyiha Java 11+, Spring Boot 3+, va H2 ma'lumotlar bazasidan foydalanadi. Kod sifatiga va SOLID tamoyillariga
rioya qilingan.

## Funksional Talablar

- Vazifa yaratish
    - Endpoint: POST /tasks
    - Kirish: JSON formatda title (majburiy), description (ixtiyoriy), dueDate (ixtiyoriy)
    - Chiqish: Yaratilgan vazifa haqida ma'lumot va noyob identifikator
- Vazifalar ro‘yxatini olish
    - Endpoint: GET /tasks
    - Chiqish: Barcha vazifalar ro‘yxati (id, title, description, dueDate, status)
- Vazifani yangilash
    - Endpoint: PUT /tasks/{id}
    - Kirish: JSON formatda title, description, dueDate, status (OPEN, IN_PROGRESS, COMPLETED)
    - Chiqish: Yangilangan vazifa haqida ma'lumot
- Vazifani o‘chirish
    - Endpoint: DELETE /tasks/{id}
    - Ta’rif: Vazifani identifikatori bo‘yicha o‘chiradi

## Texnik Talablar

- Dasturlash tili: Java 11+
- Framework: Spring Boot 3+
- Ma'lumotlar bazasi: H2 (soddalik uchun, ichki ma'lumotlar bazasi)
- Loyiha yig‘ish: Maven yoki Gradle
- Testlash: JUnit 5, Mockito
- Loglash: SLF4J + Logback

## O‘rnatish

### 1. Loyiha Fayllarini Klonlash

``` bash 
git clone https://github.com/username/task-management-system.git
cd task-management-system
```

### 2. Bog‘liqliklarni O‘rnatish

``` bash
mvn install
```

### 3. Ilovani Ishga Tushurish

```bash
mvn spring-boot:run
```

## API So‘rovlar

### Vazifa Yaratish

```http request
POST localhost:8080/tasks
Content-Type: application/json

{
  "title": "Walking every night"
}
```

### Vazifalar Ro‘yxatini Olish
```http request
GET localhost:8080/tasks
```
### Vazifani Yangilash
```http request
PUT localhost:8080/tasks/2
Content-Type: application/json

{
  "title": "Walking every night",
  "description": "Walking with my friend because it's useful for healthy",
  "status": "IN_PROGRESS"
}
```
### Vazifani O‘chirish
```http request
DELETE localhost:8080/tasks/1
```
## Testlar
### Testlarni ishlatish uchun:

```bash
mvn test
```
Yoki Gradle bilan:

## Mualliflar
Sarvarbek - Asosiy ishlab chiquvchi

## Contact
### Telegram: t.me/sarvargo
### Phone: +998972322903