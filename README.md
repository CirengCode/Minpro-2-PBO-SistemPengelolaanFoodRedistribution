# 🍲 Sistem Pengelolaan Food Redistribution 🍲
Nama: Regina Jelita Ningsih
<br> NIM: 2509116061
<br> Kelas: B (2025)

## 🍽️ Deskripsi Singkat Program
Masalah **surplus makanan** sering muncul di hotel, restoran, usaha kuliner, dan juga dalam kegiatan pribadi seperti acara syukuran.
Sementara itu, masih banyak individu dan lembaga sosial seperti panti asuhan atau rumah singgah yang membutuhkan bantuan pangan.
Program **Sistem Pengelolaan Food Redistribution** ini dibuat untuk menghubungkan kedua pihak melalui sistem pencatatan sederhana.

Sistem ini mengelola empat entitas utama yang saling berkaitan:
1. **Donatur**, yaitu pihak yang memberikan donasi makanan. Donatur dibedakan menjadi dua jenis:
   * **Donatur Individu** — perseorangan yang berdonasi dari suatu kegiatan pribadi (misalnya acara syukuran, pernikahan, dan sebagainya).
   * **Donatur Instansi** — badan usaha seperti hotel, restoran, atau usaha kuliner yang menyumbangkan makanan secara rutin.
2. **Donasi**, yaitu data makanan yang didonasikan, mencakup nama makanan, jumlah porsi, dan status kelayakan konsumsi.
3. **Penerima**, yaitu pihak yang menerima penyaluran makanan, juga dibedakan menjadi dua jenis:
   * **Penerima Individu** — perseorangan yang membutuhkan bantuan makanan (misalnya pemulung, pengamen, dan orang tidak mampu lainnya).
   * **Penerima Lembaga** — organisasi sosial seperti panti asuhan atau rumah singgah yang menyalurkan makanan kepada penghuninya.
4. **Penyaluran**, yaitu data aktivitas penyaluran yang menghubungkan sebuah donasi dengan penerima tertentu, lengkap dengan tanggal, jumlah porsi yang disalurkan, dan petugas yang bertanggung jawab.

```
========================================
       FOOD REDISTRIBUTION SYSTEM
========================================
[1] Donatur
[2] Donasi
[3] Penerima
[4] Penyaluran
[5] Keluar
>>
```

---

## 🏗️ Struktur Program (MVC)
Program ini disusun dengan pola **MVC (Model–View–Controller)** yang dimodifikasi menjadi *layered architecture* sederhana khas aplikasi console, terbagi ke dalam beberapa *package* berikut:

| Package | Peran | Isi |
|---|---|---|
| `model` | **Model** — merepresentasikan entitas/struktur data | `Donatur`, `DonaturIndividu`, `DonaturInstansi`, `Penerima`, `PenerimaIndividu`, `PenerimaLembaga`, `Donasi`, `Penyaluran` |
| `service` | Menjembatani Model dan Controller — berisi logika bisnis & operasi CRUD (tambah, lihat, update, hapus) untuk tiap entitas | mis. `DonaturService`, `DonasiService`, `PenerimaService`, `PenyaluranService` |
| `controller` | **Controller** — mengatur alur menu, menerima input pengguna, lalu memanggil `service` yang sesuai | mis. `MainController` / `MenuController` |
| `view` (tergabung dalam `controller`/`main` pada aplikasi console) | **View** — menampilkan menu dan data ke layar (`System.out.println`) serta membaca input dari `Scanner` | tampilan menu, tabel data |
| `util` | Berisi fungsi bantu (*helper*), termasuk validasi input | mis. `InputValidator` |
| `main` | *Entry point* program yang menjalankan `Controller` pertama kali | `Main.java` |

> ⚠️ **Catatan:** nama-nama class pada tabel di atas mengikuti pola umum yang dipakai pada program ini — sesuaikan kembali dengan nama file/class yang sebenarnya ada di dalam repo sebelum README ini di-*commit*.

Alur kerjanya: **`main` → `controller` → `service` → `model`**, lalu hasilnya ditampilkan kembali oleh `controller` (sebagai *view*) ke pengguna. Pemisahan ini membuat setiap bagian punya tanggung jawab yang jelas: `model` tidak tahu-menahu soal tampilan, `controller` tidak menyimpan logika bisnis, dan `service` tidak berurusan dengan input/output langsung.

```
Donatur (super class)
│── DonaturIndividu (sub class)
│── DonaturInstansi (sub class)

Penerima (super class)
│── PenerimaIndividu (sub class)
│── PenerimaLembaga (sub class)
```

Selain dua hierarki di atas, ada dua *class* lain yang berdiri sendiri tanpa pewarisan, yaitu `Donasi`, yang menyimpan data makanan yang didonasikan, dan `Penyaluran`, yang menyimpan data transaksi penyaluran dan menghubungkan `Donasi` dengan `Penerima`. Hubungan antara keempat entitas ini dijembatani oleh ID seperti `idDonatur`, `idDonasi`, dan `idPenerima`, yang saling mereferensikan.

---

## 🔒 Access Modifier
Setiap *class model* pada program ini menerapkan aturan *access modifier* yang konsisten:
- **`private`** digunakan pada seluruh atribut (*field*), misalnya `idDonatur`, `namaDonatur`, `idPenerima`, dan seterusnya. Tujuannya agar atribut tidak bisa diakses atau diubah langsung dari luar class, melainkan hanya lewat method yang disediakan.
- **`public`** digunakan pada *constructor* dan method (termasuk *getter*, *setter*, serta method seperti `getJenisDonatur()`/`getJenisPenerima()`) agar bisa dipanggil dari *package* lain, seperti `service` dan `controller`.

Contoh pada class `Donatur`:
```java
public class Donatur {
    private int idDonatur;       // private -> tidak bisa diakses langsung dari luar class
    private String namaDonatur;  // private -> tidak bisa diakses langsung dari luar class

    public Donatur(int idDonatur, String namaDonatur) { // public -> bisa dipanggil dari class lain
        this.idDonatur = idDonatur;
        this.namaDonatur = namaDonatur;
    }
    // ...
}
```
Dengan aturan ini, `service` dan `controller` **wajib** melewati *getter*/*setter* untuk membaca atau mengubah data, bukan mengakses field secara langsung.

---

## 📦 Encapsulation (Getter & Setter)
Karena semua atribut di dalam `model` bersifat `private`, program menyediakan pasangan *getter* dan *setter* untuk setiap atribut agar data tetap bisa diakses secara terkontrol dari luar class. Ini adalah penerapan **encapsulation** — membungkus data (`field`) bersama method yang mengelolanya dalam satu class.

Contoh pada class `Donatur`:
```java
public int getIdDonatur() {
    return idDonatur;
}

public void setIdDonatur(int idDonatur) {
    this.idDonatur = idDonatur;
}

public String getNamaDonatur() {
    return namaDonatur;
}

public void setNamaDonatur(String namaDonatur) {
    this.namaDonatur = namaDonatur;
}
```
Pola yang sama juga diterapkan pada seluruh atribut di class `Penerima`, `Donasi`, dan `Penyaluran`, termasuk atribut tambahan milik masing-masing *subclass* seperti `jenisKegiatan` (pada `DonaturIndividu`), `namaInstansi` dan `jenisInstansi` (pada `DonaturInstansi`), `deskripsiPenerima` (pada `PenerimaIndividu`), serta `namaLembaga`, `jenisLembaga`, dan `namaPengelola` (pada `PenerimaLembaga`).

---

## ⭐ Inheritance
### Donatur (Superclass)
Kelas `Donatur` adalah kelas dasar yang menyimpan atribut dan perilaku umum yang dimiliki semua jenis donatur, yaitu `idDonatur` dan `namaDonatur`. Kelas ini juga punya method `getJenisDonatur()` yang akan di-*override* oleh kelas turunannya.

```java
public class Donatur {
    private int idDonatur;
    private String namaDonatur;

    public Donatur(int idDonatur, String namaDonatur) {
        this.idDonatur = idDonatur;
        this.namaDonatur = namaDonatur;
    }

    public int getIdDonatur() {
        return idDonatur;
    }

    public void setIdDonatur(int idDonatur) {
        this.idDonatur = idDonatur;
    }

    public String getNamaDonatur() {
        return namaDonatur;
    }

    public void setNamaDonatur(String namaDonatur) {
        this.namaDonatur = namaDonatur;
    }

    public String getJenisDonatur() {
        return "Donatur";
    }
}
```

### DonaturIndividu dan DonaturInstansi (Subclass)
Kedua kelas ini menggunakan `extends` agar bisa mewarisi semua atribut dan method dari `Donatur`. Dengan begitu, tidak perlu menulis ulang `idDonatur`, `namaDonatur`, dan *getter-setter*-nya. Setiap *subclass* hanya perlu menambah atribut khusus miliknya sendiri.

```java
public class DonaturIndividu extends Donatur {
    private String jenisKegiatan;

    public DonaturIndividu(int idDonatur, String namaDonatur, String jenisKegiatan) {
        super(idDonatur, namaDonatur);
        this.jenisKegiatan = jenisKegiatan;
    }

    public String getJenisKegiatan() {
        return jenisKegiatan;
    }

    public void setJenisKegiatan(String jenisKegiatan) {
        this.jenisKegiatan = jenisKegiatan;
    }

    @Override
    public String getJenisDonatur() {
        return "Donatur Individu";
    }
}
```

```java
public class DonaturInstansi extends Donatur {
    private String namaInstansi;
    private String jenisInstansi;

    public DonaturInstansi(
            int idDonatur,
            String namaDonatur,
            String namaInstansi,
            String jenisInstansi) {

        super(idDonatur, namaDonatur);
        this.namaInstansi = namaInstansi;
        this.jenisInstansi = jenisInstansi;
    }

    public String getNamaInstansi() {
        return namaInstansi;
    }

    public void setNamaInstansi(String namaInstansi) {
        this.namaInstansi = namaInstansi;
    }

    public String getJenisInstansi() {
        return jenisInstansi;
    }

    public void setJenisInstansi(String jenisInstansi) {
        this.jenisInstansi = jenisInstansi;
    }

    @Override
    public String getJenisDonatur() {
        return "Donatur Instansi";
    }
}
```

### Penerima (Superclass)
Konsep yang sama seperti pada `Donatur` juga digunakan dalam hierarki `Penerima`. Kelas `Penerima` memiliki atribut dasar yang dimiliki oleh semua jenis penerima, yaitu `idPenerima`, serta method `getJenisPenerima()` yang nantinya akan di-*override* oleh *subclass*-nya.

```java
public class Penerima {
    private int idPenerima;

    public Penerima(int idPenerima) {
        this.idPenerima = idPenerima;
    }

    public int getIdPenerima() {
        return idPenerima;
    }

    public void setIdPenerima(int idPenerima) {
        this.idPenerima = idPenerima;
    }

    public String getJenisPenerima() {
        return "Penerima";
    }
}
```

### PenerimaIndividu dan PenerimaLembaga (Subclass)
Seperti halnya `DonaturIndividu` dan `DonaturInstansi`, kedua kelas ini memakai `extends` untuk mewarisi `idPenerima` dari `Penerima`. Mereka juga memanggil `super(idPenerima)` di *constructor*, lalu menambahkan atribut khusus masing-masing.

```java
public class PenerimaIndividu extends Penerima {
    private String deskripsiPenerima;

    public PenerimaIndividu(int idPenerima, String deskripsiPenerima) {
        super(idPenerima);
        this.deskripsiPenerima = deskripsiPenerima;
    }

    public String getDeskripsiPenerima() {
        return deskripsiPenerima;
    }

    public void setDeskripsiPenerima(String deskripsiPenerima) {
        this.deskripsiPenerima = deskripsiPenerima;
    }

    @Override
    public String getJenisPenerima() {
        return "Individu";
    }
}
```

```java
public class PenerimaLembaga extends Penerima {
    private String namaLembaga;
    private String jenisLembaga;
    private String namaPengelola;

    public PenerimaLembaga(
            int idPenerima,
            String namaLembaga,
            String jenisLembaga,
            String namaPengelola) {

        super(idPenerima);
        this.namaLembaga = namaLembaga;
        this.jenisLembaga = jenisLembaga;
        this.namaPengelola = namaPengelola;
    }

    public String getNamaLembaga() {
        return namaLembaga;
    }

    public void setNamaLembaga(String namaLembaga) {
        this.namaLembaga = namaLembaga;
    }

    public String getJenisLembaga() {
        return jenisLembaga;
    }

    public void setJenisLembaga(String jenisLembaga) {
        this.jenisLembaga = jenisLembaga;
    }

    public String getNamaPengelola() {
        return namaPengelola;
    }

    public void setNamaPengelola(String namaPengelola) {
        this.namaPengelola = namaPengelola;
    }

    @Override
    public String getJenisPenerima() {
        return "Lembaga";
    }
}
```

---

## 🔁 Polymorphism
Program ini menerapkan **polymorphism** dalam bentuk **method overriding**. Method `getJenisDonatur()` yang didefinisikan di superclass `Donatur` di-*override* oleh `DonaturIndividu` dan `DonaturInstansi`, begitu pula `getJenisPenerima()` di superclass `Penerima` yang di-*override* oleh `PenerimaIndividu` dan `PenerimaLembaga` (lihat kode lengkapnya pada bagian **Inheritance** di atas).

Berkat *polymorphism* ini, program bisa menyimpan seluruh objek donatur di dalam satu `ArrayList<Donatur>` (begitu juga `ArrayList<Penerima>`), lalu memanggil method yang sama (`getJenisDonatur()` / `getJenisPenerima()`) untuk setiap objek — namun hasil yang dikembalikan akan berbeda tergantung objek sebenarnya (`DonaturIndividu` atau `DonaturInstansi`) tanpa perlu mengecek tipe objek secara manual di setiap pemanggilan:

```java
for (Donatur d : daftarDonatur) {
    System.out.println(d.getNamaDonatur() + " - " + d.getJenisDonatur());
    // otomatis memanggil versi override sesuai objek aslinya
}
```

Selain itu, saat menampilkan detail data, program memakai `instanceof` untuk mengecek tipe objek asli (`DonaturIndividu`/`DonaturInstansi`, `PenerimaIndividu`/`PenerimaLembaga`) agar bisa menampilkan atribut tambahan yang spesifik untuk tiap jenis — ini terlihat pada perbedaan tampilan tabel di bagian **Alur Program** (mis. kolom "Jenis Kegiatan" hanya muncul untuk Donatur Individu, sedangkan "Nama Instansi" & "Jenis Instansi" hanya muncul untuk Donatur Instansi).

> *(Opsional) Jika project ini juga menerapkan method overloading — misalnya beberapa method di `service` dengan nama sama namun parameter berbeda — tambahkan contoh kodenya di bagian ini.*

---

## ✅ Validasi Input
Untuk mencegah kesalahan input dari pengguna, program menerapkan validasi input pada bagian `util` (helper) sebelum data disimpan ke dalam `ArrayList`. Beberapa validasi yang diterapkan antara lain:
- Memastikan input angka (ID, jumlah porsi, pilihan menu) benar-benar berupa angka, bukan teks.
- Memastikan input teks wajib (nama donatur, nama makanan, dsb.) tidak kosong.
- Memastikan ID yang dimasukkan (saat update/hapus) benar-benar terdaftar di dalam data.
- Memastikan input konfirmasi (`y`/`n`) hanya menerima pilihan yang valid, dan akan meminta input ulang jika pengguna mengetik selain itu.

Jika input tidak valid, program akan menampilkan pesan *error* dan meminta pengguna memasukkan ulang data tersebut, sehingga program tidak akan berhenti tiba-tiba (*crash*) karena input yang salah.

*(ss: tampilan validasi input — misalnya saat memasukkan huruf pada kolom angka, atau ID yang tidak ditemukan)*

---

## 🥗 Alur Program
Berikut ini adalah alur program secara garis besar:

**1. Tampilan Menu Utama**
<br> Saat program pertama kali dijalankan, akan muncul judul dari sistem beserta lima pilihan menu, mulai dari Donatur, Donasi, Penerima, Penyaluran, dan Keluar. Cukup input angka sesuai menu yang ingin dituju, lalu menekan Enter. Tampilan menu ini akan muncul berulang kali setiap kali pengguna kembali dari salah satu sub-menu, karena disusun dengan struktur perulangan yang baru berhenti ketika pengguna memilih "Keluar".

*(ss: tampilan menu utama)*

**2. Menu Data Donatur**
<br> Setelah memilih menu "Donatur", pengguna akan melihat daftar semua donatur yang sudah tercatat, lengkap dengan ID, nama, dan jenisnya. Tampilan data bisa berbeda untuk setiap donatur: jika donatur berjenis "Individu", akan ada baris tambahan "Jenis Kegiatan" (misalnya Acara Syukuran); untuk donatur "Instansi", yang muncul adalah "Nama Instansi" dan "Jenis Instansi". Perbedaan tampilan ini menunjukkan konsep *inheritance* dan *instanceof* yang sudah dijelaskan sebelumnya. Di bawah daftar, ada sub-menu Tambah, Update, Hapus, dan Keluar untuk mengelola data donatur.

*(ss: tampilan menu & daftar data donatur)*

**3. Menambah Data Donatur Baru**
<br> Proses saat pengguna memilih opsi "Tambah" di menu Donatur. Program akan meminta ID dan nama donatur terlebih dahulu, lalu menanyakan jenis donatur yang ingin dibuat (Individu atau Instansi) lewat sub-menu. Pertanyaan berikutnya akan menyesuaikan pilihan pengguna: jika memilih Individu, program hanya menanyakan "Jenis Kegiatan"; jika memilih Instansi, program menanyakan "Nama Instansi" dan "Jenis Instansi". Setelah semua data diisi, akan muncul pesan konfirmasi bahwa data berhasil ditambahkan, dan data baru langsung muncul di daftar Donatur.

*(ss: proses tambah data donatur individu & instansi)*

**4. Menu Data Donasi**
<br> Menu ini menampilkan semua data donasi makanan yang sudah tercatat, termasuk ID Donasi, ID Donatur, nama makanan, jumlah porsi, dan status kelayakan konsumsi. Kolom "ID Donatur" penting karena menunjukkan hubungan antara data donasi dan donatur, meskipun nama donatur belum langsung ditampilkan di tabel Donasi. Seperti pada menu Donatur sebelumnya, di bawah tabel juga ada sub-menu Tambah, Update, dan Hapus untuk mengelola data donasi.

*(ss: tampilan menu & daftar data donasi)*

**5. Menu Data Penerima**
<br> Seperti pada menu Donatur, menu ini menampilkan daftar penerima manfaat dengan tampilan yang berbeda tergantung jenisnya. Untuk penerima "Individu", hanya ada satu baris tambahan "Deskripsi Penerima" yang berisi deskripsi spesifik dari penerima donasi yang identitas formalnya tidak dicatat seperti penerima yang berasal dari suatu lembaga. Untuk penerima "Lembaga", tampilannya lebih lengkap dengan "Nama Lembaga", "Jenis Lembaga", dan "Nama Pengelola". Perbedaan ini berasal dari jenis objek yang disimpan di `ArrayList<Penerima>`, bukan dari tabel data yang berbeda.

*(ss: tampilan menu & daftar data penerima)*

**6. Menu Data Penyaluran**
<br> Menu ini menampilkan detail aktivitas penyaluran makanan yang berupa ID Penyaluran, ID Donasi, ID Penerima, nama kegiatan, tanggal penyaluran, jumlah porsi yang disalurkan, dan nama petugas yang menangani. Tabel ini merangkum seluruh alur sistem, mulai dari makanan dari donatur mana, disalurkan ke penerima mana, kapan, dan oleh siapa.

*(ss: tampilan menu & daftar data penyaluran)*

**7. Proses Update Data**
<br> Langkah-langkah saat pengguna memperbarui data yang sudah ada. Proses ini bisa dilakukan di menu Donatur, Donasi, Penerima, atau Penyaluran, karena polanya sama. Setelah pengguna memasukkan ID data yang ingin diubah, program menampilkan ulang data lama sebagai pengingat, lalu meminta konfirmasi dengan mengetik "y" (ya) atau "n" (tidak). Jika pengguna menjawab "y", program akan meminta input data baru satu per satu, lalu menampilkan pesan bahwa data berhasil diperbarui. Konfirmasi dua langkah ini dibuat untuk mencegah perubahan data yang tidak disengaja.

*(ss: proses konfirmasi & hasil update data)*

**8. Proses Hapus Data**
<br> Prosesnya mirip dengan update data: pengguna akan memasukkan ID data yang ingin dihapus, lalu program menampilkan detail lengkap data tersebut sebagai konfirmasi. Setelah itu, program menanyakan "Yakin ingin menghapus data? (y/n)". Jika pengguna mengetik "y", data langsung dihapus dari daftar dan muncul pesan bahwa penghapusan berhasil. Jika mengetik selain itu, proses dibatalkan dan data tetap ada.

*(ss: proses konfirmasi & hasil hapus data)*

**9. Keluar dari Program**
<br> Pesan penutup "Terima kasih sudah menggunakan Food Redistribution System" muncul saat pengguna memilih menu "Keluar" di menu utama. Setelah pesan ini tampil, program benar-benar berhenti berjalan — perulangan pada `Controller` yang menjaga menu tetap muncul dihentikan, dan kendali kembali ke sistem operasi.

*(ss: pesan penutup program)*
