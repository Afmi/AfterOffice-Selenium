# Rest-Assured + TestNG API Automation

Project ini berisi automation test untuk API menggunakan **Java**, **Rest-Assured**, dan **TestNG**.

---

## 📦 Requirements

- Java 17+ (direkomendasikan Java 21)
- Maven

---

## 🚀 Cara Menjalankan

1. **Clone repository**

```bash
git clone https://github.com/username/Rest-Assured-TestNG.git
cd Rest-Assured-TestNG
```

2. **Install dependencies dan build project**

```bash
mvn clean install
```

3. **Jalankan test**

```bash
mvn test
```

> Test akan dijalankan berdasarkan file `testng.xml`

---

## 🧪 Struktur Test

- `BaseTest.java` – Melakukan login otomatis dan simpan token
- `RegisterLoginTest.java` – Test register dan login user
- `GetObjectsTest.java` – Test GET: semua object, object by ID, single object by UUID, list department
- `PostObjectTest.java` – Test POST untuk menambahkan object baru
- `Put.java` – Test PUT untuk pembaruan object baru
- `Patch.java` – Test PATCH untuk pembaruan parsial object
- `Delete.java` – Test DELETE untuk menghapus object

---

## 🔐 Autentikasi
Token login akan otomatis diambil dari endpoint `/login` dan digunakan pada semua test yang membutuhkan header `Authorization: Bearer <token>`.

---

---
