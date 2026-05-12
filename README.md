# 🗡️ Java Dungeon RPG: Ultimate Engine 🏰

Selamat datang di **Dungeon RPG**, sebuah game berbasis terminal yang dibangun dengan **Java Core**. Proyek ini merupakan implementasi mendalam dari konsep *Object-Oriented Programming* (OOP) dengan sistem pertarungan dinamis dan mekanisme manajemen karakter yang kompleks.

---

## 🌟 Fitur Unggulan

### 🛡️ 5 Class Hero Unik
Pilih gaya bertarungmu dengan spesialisasi yang berbeda:
* **`Warrior.java`** - Si tangguh dengan pertahanan fisik tak tertembus.
* **`Assassin.java`** - Ahli serangan senyap dengan *Critical Rate* mematikan.
* **`Healer.java`** - Penjaga nyawa yang mampu memulihkan HP di saat kritis.
* **`Mage.java`** - Pengendali elemen dengan daya hancur sihir yang luas.
* **`Archer.java`** - Penembak jitu yang mengandalkan kecepatan dan akurasi.

### 🐾 Companion System (Pet)
* **`Pet.java`**: Kamu tidak bertarung sendirian. Sistem Pet memungkinkanmu membawa teman yang memberikan *buff* status tambahan dan berkembang seiring level pemain.

### 💰 Economy & Interaction
* **`ShopSystem.java`**: Kelola *Gold* hasil jarahanmu untuk membeli ramuan atau senjata legendaris.
* **`Interactable.java`**: Dunia yang hidup! Interface ini memungkinkan pemain berinteraksi dengan peti harta, NPC, hingga pintu rahasia.

---

## 📂 Bedah Arsitektur Kode

| Nama File | Peran Utama |
| :--- | :--- |
| `GameEngine.java` | **Otak Program** yang mengatur jalannya alur permainan (*Game Loop*). |
| `Entity.java` | **Base Class** (Induk) yang mengatur logika HP, Attack, dan Nama. |
| `CombatSystem.java` | Logika perhitungan damage dan mekanisme pertarungan *turn-based*. |
| `Interactable.java` | **Interface** yang memastikan semua objek bisa diajak berinteraksi. |

---

## 🚀 Konsep OOP yang Diterapkan

Proyek ini dirancang sebagai bukti pemahaman struktur data dan kodingan bersih:
1.  **Inheritance**: Menggunakan pewarisan untuk membuat berbagai jenis Hero dari satu induk `Entity`.
2.  **Polymorphism**: Interaksi objek yang beragam melalui satu *Interface* yang sama.
3.  **Encapsulation**: Menjaga data sensitif karakter menggunakan *private fields* dan *getter-setter*.

---

## 🎮 Cara Menjalankan

1. Clone repositori ini:
   ```bash
   git clone [https://github.com/fajarbuchori/dungeon-rpg-engine.git](https://github.com/fajarbuchori/dungeon-rpg-engine.git)