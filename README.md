# 🍱 Sistem Pengelolaan Food Redistribution 🍱
Nama: Regina Jelita Ningsih
<br> NIM: 2509116061
<br> Kelas: B (2025)

## 🍽️ Deskripsi Singkat Program
Masalah **surplus makanan** sering terjadi di hotel, restoran, usaha kuliner, dan juga dalam acara pribadi seperti syukuran. Sementara itu, masih banyak individu dan lembaga sosial seperti panti asuhan atau rumah singgah yang membutuhkan makanan. Program **Sistem Pengelolaan Food Redistribution** dibuat untuk menghubungkan kedua pihak dengan sistem pencatatan yang sederhana dan terstruktur.

Sistem ini mengelola empat entitas utama yang saling berkaitan:
1. **Donatur** adalah pihak yang memberikan donasi makanan. Donatur terbagi menjadi dua jenis:
   * **Donatur Individu** adalah perseorangan yang memberikan donasi dari kegiatan pribadi, seperti syukuran, pernikahan, dan lainnya.
   * **Donatur Instansi** adalah badan usaha seperti hotel, restoran, atau usaha kuliner yang secara rutin menyumbangkan makanan.
2. **Donasi** adalah data makanan yang diberikan oleh donatur, meliputi nama makanan, jumlah porsi, dan status kelayakan konsumsi.
3. **Penerima** adalah pihak yang menerima makanan dari proses penyaluran. Penerima juga terbagi menjadi dua jenis:
   * **Penerima Individu** adalah perseorangan yang membutuhkan bantuan makanan, seperti pemulung, pengamen, dan orang lain yang membutuhkan.
   * **Penerima Lembaga** adalah organisasi sosial seperti panti asuhan atau rumah singgah yang menyalurkan makanan kepada para penghuninya.
4. **Penyaluran** adalah data kegiatan penyaluran makanan yang menghubungkan donasi dengan penerima tertentu. Data ini mencakup tanggal penyaluran, jumlah porsi yang disalurkan, dan petugas yang bertanggung jawab.


```
========================================
               MENU ADMIN
========================================
[1] Donatur
[2] Donasi
[3] Penerima
[4] Penyaluran
[5] Kembali
>>
```

## ⭐ Struktur Program (MVC)
Program ini disusun dengan kosep **MVC (Model–View–Controller)** yang dimodifikasi menjadi *layered architecture* sederhana. Struktur program ini dibagi ke beberapa *package* supaya setiap bagian punya tugas yang jelas.


| Package | Peran | Isi |
|---|---|---|
| `model` | **Model** yang digunakan untuk merepresentasikan data dan objek dalam sistem. | `Donatur`, `DonaturIndividu`, `DonaturInstansi`, `Penerima`, `PenerimaIndividu`, `PenerimaLembaga`, `Donasi`, `Penyaluran` |
| `service` | Menangani logika program dan operasi CRUD (tambah, lihat, update, hapus) untuk setiap entitas. | `DonaturService`, `DonasiService`, `PenerimaService`, `PenyaluranService` |
| `controller` | **Controller** yang mengatur alur program dan menentukan menu yang dapat diakses oleh pengguna `service` yang sesuai. | `MainController` / `MenuController` |
| `view` (tergabung dalam `controller` & `main` pada program | **View** Berisi fungsi bantu (helper) yang digunakan di beberapa bagian program, terutama untuk menangani validasi input (`System.out.println`) serta membaca input dari `Scanner`. | tampilan menu, tabel data |
| `util` | Berisi fungsi bantu (*helper*), termasuk validasi input. | `InputValidator` |
| `main` | Menjadi *Entry point* program yang menjalankan `Controller` pertama kali. | `Main.java` |

Pada aplikasi console ini, bagian **View** tidak dibuat sebagai _package_ terpisah. Menu dan data ditampilkan dengan `System.out.println`, dan input pengguna dibaca dengan `Scanner`. Proses ini dijalankan bersama `Controller` dan `Service` sesuai kebutuhan program.

Secara singkat, alur programnya adalah main ke controller, lalu ke service, dan akhirnya ke model. Setelah proses selesai, hasilnya ditampilkan lagi ke pengguna lewat output console. Dengan pembagian ini, setiap bagian program punya tanggung jawab yang lebih jelas, sehingga class model fokus pada data, service mengurus proses CRUD dan logika program, dan controller mengatur alur serta menu yang dijalankan.

## 🧩 Access Modifier
Program ini menggunakan _access modifier_ untuk menentukan bagian mana dari class yang bisa diakses dari luar. Pada _class model_, atribut dibuat `private` supaya tidak bisa diakses atau diubah langsung oleh class lain.

- **`private`** digunakan pada atribut (*field*), seperti `idDonatur`, `namaDonatur`, `idPenerima`, dan atribut lainnya. Dengan cara ini, data di dalam class tetap aman dan hanya bisa diakses lewat method yang sudah ada.
- **`public`** digunakan pada *constructor* dan method yang perlu dipanggil dari class lain, seperti *getter*, *setter*, dan method `getJenisDonatur()` atau `getJenisPenerima()`.
- **`final`** ipakai pada beberapa atribut ID yang tidak perlu diubah setelah objek dibuat, misalnya `idDonatur` pada class `Donatur`.

Contoh pada class `Donatur`:
```java
public class Donatur {
    private final int idDonatur;
    private String namaDonatur;

    public Donatur(int idDonatur, String namaDonatur) {
        this.idDonatur = idDonatur;
        this.namaDonatur = namaDonatur;
    }

    public int getIdDonatur() {
        return idDonatur;
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
Dengan penggunaan `private`, class lain seperti `service` dan `controller` tidak dapat mengakses atribut secara langsung. Untuk membaca data digunakan _getter_, sedangkan perubahan data yang diizinkan dilakukan lewat _setter_. Dengan cara ini, akses terhadap data menjadi lebih terkontrol.

## 📦 Encapsulation 
Program ini menerapkan **encapsulation** dengan menyimpan atribut di dalam class menggunakan access modifier `private`. Dengan cara ini, data tidak dapat diakses atau diubah secara langsung dari luar class. Akses terhadap data dilakukan melalui method seperti *getter* dan *setter* yang disediakan oleh masing-masing class.

Contoh pada class `Donatur`:
```java
public class Donatur {
    private final int idDonatur;
    private String namaDonatur;

    public Donatur(int idDonatur, String namaDonatur) {
        this.idDonatur = idDonatur;
        this.namaDonatur = namaDonatur;
    }

    public int getIdDonatur() {
        return idDonatur;
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
Pada contoh tersebut, getIdDonatur() dan getNamaDonatur() digunakan untuk membaca data. Sementara itu, setNamaDonatur() digunakan untuk mengubah namaDonatur. Atribut idDonatur tidak memiliki setter karena ID sudah dibuat final dan tidak perlu diubah setelah objek dibuat.

Konsep yang sama juga diterapkan pada class `Penerima`, `Donasi`, dan `Penyaluran`, termasuk atribut tambahan di setiap subclass. Misalnya, `jenisKegiatan` pada `DonaturIndividu`, `namaInstans` dan `jenisInstansi` pada `DonaturInstansi`, `deskripsiPenerima` pada `PenerimaIndividu`, serta `namaLembaga`, `jenisLembaga`, dan `namaPengelola` pada `PenerimaLembaga`.


## ⭐ Inheritance
### Donatur (Superclass)
Kelas `Donatur` adalah kelas dasar yang menyimpan atribut dan perilaku umum yang dimiliki semua jenis donatur, yaitu `idDonatur` dan `namaDonatur`. Kelas ini juga punya method `getJenisDonatur()` yang akan di-*override* oleh kelas turunannya.

```java
public class Donatur {
    private final int idDonatur;
    private String namaDonatur;

    public Donatur(int idDonatur, String namaDonatur) {
        this.idDonatur = idDonatur;
        this.namaDonatur = namaDonatur;
    }

    public int getIdDonatur() {
        return idDonatur;
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
    private final int idPenerima;

    public Penerima(int idPenerima) {
        this.idPenerima = idPenerima;
    }

    public int getIdPenerima() {
        return idPenerima;
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

## 👾 Polymorphism
Program ini menggunakan **polymorphism** dengan cara menerapkan **overriding**. Implementasinya terlihat pada method `getJenisDonatur()` awalnya dibuat di superclass `Donatur`, lalu diubah sesuai kebutuhan di `DonaturIndividu` dan `DonaturInstansi`.

Pada superclass `Donatur`, method tersebut memiliki nilai awal:

```java
public String getJenisDonatur() {
    return "Donatur";
}
```
Masing-masing subclass memberikan implementasi yang berbeda:

```
    @Override
    public String getJenisDonatur() {
        return "Donatur Individu";
    }
```

```
    @Override
    public String getJenisDonatur() {
        return "Donatur Instansi";
    }
```

Dengan cara ini, _method_ yang sama dapat menghasilkan nilai berbeda sesuai dengan objek yang digunakan. Contohnya saat data disimpan dalam `ArrayList<Donatur>`:

```
...
        for (Donatur d : dataDonatur) {
            System.out.println("ID Donatur: " + d.getIdDonatur());
            System.out.println("Nama Donatur: " + d.getNamaDonatur());
            System.out.println("Jenis Donatur: " + d.getJenisDonatur());

            if (d instanceof DonaturIndividu individu) {
                System.out.println("Jenis Kegiatan: " + individu.getJenisKegiatan());
            } else if (d instanceof DonaturInstansi instansi) {
                System.out.println("Nama Instansi: " + instansi.getNamaInstansi());
                System.out.println("Jenis Instansi: " + instansi.getJenisInstansi());
            }
            System.out.println("------------------------------------");
        }
...
```
Jika objek yang digunakan adalah `DonaturIndivid`u, maka program menjalankan `getJenisDonatur()` milik `DonaturIndividu`. Jika objeknya `DonaturInstansi`, maka yang dijalankan adalah milik `DonaturInstansi`.

Konsep yang sama juga diterapkan pada `getJenisPenerima()` pada kelas `Penerima`, `PenerimaIndividu`, dan `PenerimaLembaga`.

Selain menggunakan **overriding**, program juga memakai **instanceof** untuk memeriksa tipe objek. Cara ini digunakan saat ingin menampilkan atribut tambahan yang berbeda di setiap subclass.


## ✅ Validasi Input
Untuk mencegah kesalahan saat pengguna memasukkan data, program melakukan validasi input dengan `InputUtil` dan juga validasi tambahan di bagian service dan model. Jika ada input yang tidak sesuai, program akan menampilkan pesan kesalahan dan meminta pengguna untuk mengisi ulang data.

Berikut beberapa jenis validasi yang digunakan:

* Input angka seperti ID, jumlah porsi, dan pilihan menu harus benar-benar berupa angka.
* Input teks seperti nama donatur dan nama makanan tidak boleh dibiarkan kosong.
* ID yang digunakan saat menambah, mengubah, atau menghapus data harus sesuai dengan data yang sudah ada. Misalnya, idDonatur pada data Donasi harus sudah terdaftar sebelumnya.
* Input angka tidak boleh bernilai 0 atau negatif.
* Input konfirmasi seperti `y`/`n`hanya menerima pilihan yang benar.

Dengan validasi ini, jika ada kesalahan input, pengguna akan diminta untuk mengulangi input. Program pun tidak akan langsung berhenti atau crash saat menerima input yang salah.

<br> <img width="412" height="897" alt="image" src="https://github.com/user-attachments/assets/2a54e09e-02b8-419c-b833-760bfb78e166" />
<br> <img width="415" height="520" alt="image" src="https://github.com/user-attachments/assets/86cb1fda-0243-4f42-acf7-0ed98859eb5d" />
<br> <img width="420" height="875" alt="image" src="https://github.com/user-attachments/assets/c735f32b-add8-48f9-af28-a31b42c53f7c" />
<br> <img width="381" height="406" alt="image" src="https://github.com/user-attachments/assets/f25caad4-de36-46de-bc61-b06eaba7148c" />
<br><img width="342" height="786" alt="image" src="https://github.com/user-attachments/assets/d6fa0ddc-c2cd-40cb-aa3b-1b0a7ae546be" />
<br> <img width="342" height="640" alt="image" src="https://github.com/user-attachments/assets/54df4850-536e-41a7-a92e-8042f1298d8f" />


## 🍱 Alur Program
Berikut ini adalah alur program secara garis besar:

**1. Tampilan Menu Utama**
<br> Saat program pertama kali dijalankan, akan muncul tampilan sapaan sistem **Food Redistribution System** dan pengguna akan diminta memilih _role_ yang akan digunakan. Pada menu awal, terdapat tiga pilihan, yaitu **Admin**, **Petugas**, dan **Keluar**.
<br> Jika pengguna memilih Admin, pengguna akan masuk ke Menu Admin. Jika memilih Petugas, pengguna akan masuk ke Menu Petugas. Sementara itu, pilihan Keluar akan langsung mengakhiri program.

<img width="522" height="232" alt="image" src="https://github.com/user-attachments/assets/04e22257-3cd6-4dd1-9156-0be440ab8f24" />


<br> **2. Menu Admin**
<br> Setelah memilih _role_ **Admin**, pengguna masuk ke Menu Admin yang memberi akses untuk mengelola semua data di sistem. Menu ini terdiri dari `Donatur`, `Donasi,` `Penerima`, `Penyaluran`, dan `Kembali`.
<br> Pada Menu Admin, semua data bisa dilihat dan dikelola dengan operasi CRUD. Admin dapat menambah, melihat, memperbarui, atau menghapus data sesuai menu yang dipilih.

<img width="520" height="507" alt="image" src="https://github.com/user-attachments/assets/48fcd28b-91b3-4168-8a56-9c80afe68204" />


<br> **3. Menu Petugas**
<br> Jika pengguna memilih _role_ **Petugas**, pengguna akan masuk ke dalam Menu Petugas. Menu ini aksesnya lebih terbatas karena Petugas hanya bisa melihat data yang berhubungan dengan penyaluran makanan.
<br> Menu Petugas terdiri dari `Lihat Data Donasi`, `Lihat Data Penerima`, `Lihat Data Penyaluran`, dan `Kembali`. Petugas hanya bisa melihat data, tidak bisa menambah, memperbarui, atau menghapus data. Menu Donatur tidak tersedia untuk Petugas karena data donatur hanya dikelola lewat Menu Admin.

<img width="508" height="470" alt="image" src="https://github.com/user-attachments/assets/0e2a7e3e-a37c-4c75-ac52-d7df3e78104c" />
<br> <img width="417" height="838" alt="image" src="https://github.com/user-attachments/assets/785576fb-3b3c-4f13-b30a-1fb68ac98c38" />


<br> **4. Menu Data Donatur**
<br> Pada Menu Admin, pengguna bisa memilih menu “Donatur” untuk melihat dan mengelola data donatur yang sudah tercatat. Data yang ditampilkan meliputi ID, nama, dan jenis donatur.
<br> Tampilan data berbeda tergantung jenis donatur. Untuk “Donatur Individu”, ada tambahan “Jenis Kegiatan”. Untuk “Donatur Instansi”, tampil “Nama Instansi” dan “Jenis Instansi”. Perbedaan ini terkait konsep _inheritance_ dan _instanceof_ dalam program.

<img width="380" height="696" alt="image" src="https://github.com/user-attachments/assets/bb92bb76-2308-4c66-b3d5-72903cbfbafe" />


<br> **5. Menambah Data Donatur Baru**
<br> Ketika Admin memilih “Tambah” di menu Donatur, program akan meminta ID dan nama donatur terlebih dahulu. Setelah itu, pengguna memilih jenis donatur, yaitu Individu atau Instansi.
<br> Jika memilih Donatur Individu, program meminta “Jenis Kegiatan”. Jika memilih Donatur Instansi, program meminta “Nama Instansi” dan “Jenis Instansi”. Setelah semua data diisi, program menampilkan pesan bahwa data berhasil ditambahkan.

<img width="375" height="580" alt="image" src="https://github.com/user-attachments/assets/0fa65aa7-7866-4b1d-80e5-24881ce7e084" />
<br> <img width="376" height="243" alt="image" src="https://github.com/user-attachments/assets/a2817bc7-fbeb-47f8-8e99-eef19618b99f" />


<br> **6. Menu Data Donasi**
<br> Menu “Donasi” digunakan untuk melihat dan mengelola data donasi makanan. Data yang ditampilkan meliputi ID Donasi, ID Donatur, nama makanan, jumlah porsi, dan status kelayakan konsumsi.
<br> Kolom “ID Donatur” digunakan untuk menunjukkan hubungan antara data donasi dan donatur. Admin dapat mengelola data dengan pilihan Tambah, Update, Hapus, dan Keluar. Sementara itu, Petugas juga bisa mengakses menu Donasi, tapi hanya untuk melihat data yang sudah ada.

<img width="373" height="717" alt="image" src="https://github.com/user-attachments/assets/68f978be-a154-44f6-9ce9-14c0ee35f326" />


<br> **7. Menu Data Penerima**
<br> Menu “Penerima” digunakan untuk melihat dan mengelola data penerima makanan. Tampilan data disesuaikan dengan jenis penerima.
<br> Untuk Penerima Individu, ada tambahan “Deskripsi Penerima”. Untuk Penerima Lembaga, ada “Nama Lembaga”, “Jenis Lembaga”, dan “Nama Pengelola”. Perbedaan ini berasal dari jenis objek yang disimpan di `ArrayList<Penerima>`.
<br> Admin bisa menambah, memperbarui, menghapus, dan melihat data penerima. Petugas hanya bisa melihat data penerima yang sudah ada.

<img width="377" height="717" alt="image" src="https://github.com/user-attachments/assets/511f71f4-2d38-48db-a25b-cecaa9e4ce6a" />


<br> **8. Menu Data Penyaluran**
<br>Menu “Penyaluran” digunakan untuk mencatat dan mengelola kegiatan penyaluran makanan. Data yang ditampilkan meliputi ID Penyaluran, ID Donasi, ID Penerima, nama kegiatan, tanggal penyaluran, jumlah porsi, dan nama petugas.
<br> Admin bisa menambah, memperbarui, menghapus, dan melihat data penyaluran. Petugas hanya bisa melihat data penyaluran yang sudah tercatat.

<img width="457" height="895" alt="image" src="https://github.com/user-attachments/assets/58aa8e8a-1295-408f-901d-5600718e4f12" />


<br> **9. Proses Update Data**
<br> Proses Update hanya tersedia untuk Admin karena proses ini akan mengubah data yang tersimpan. Pada proses Update, Admin memasukkan ID data yang ingin diubah. Program menampilkan data tersebut dan meminta konfirmasi sebelum perubahan dilakukan. Jika memilih “y”, program meminta data baru dan menyimpan perubahan. Jika memilih selain itu, proses dibatalkan.

<img width="402" height="582" alt="image" src="https://github.com/user-attachments/assets/6732c5ca-4f0b-4ba8-beac-7214165bfc8b" />
<br> <img width="377" height="247" alt="image" src="https://github.com/user-attachments/assets/02237781-49af-4ef8-9d16-0c6259211617" />


<br> **10. Proses Hapus Data**
<br> Proses Hapus hanya tersedia untuk Admin karena proses ini juga akan mengubah data yang tersimpan. Pada proses Hapus, Admin memasukkan ID data yang ingin dihapus. Program menampilkan detail data dan meminta konfirmasi “Yakin ingin menghapus data? (y/n)”. Jika memilih “y”, data akan dihapus. Jika memilih selain itu, proses dibatalkan.

<img width="380" height="537" alt="image" src="https://github.com/user-attachments/assets/1cf08b41-4f75-47a6-be0e-455588fdf2b8" />
<br> <img width="387" height="243" alt="image" src="https://github.com/user-attachments/assets/6c22e429-65ba-460e-99d1-81c26f37f585" />


<br> **11. Keluar dari Program**
<br> Setiap menu memiliki pilihan Kembali untuk kembali ke menu sebelumnya. Setelah kembali dari Menu Admin atau Menu Petugas, pengguna diarahkan ke menu pilihan role. Jika pengguna memilih Keluar di menu awal, program akan menampilkan pesan penutup.

<img width="532" height="510" alt="image" src="https://github.com/user-attachments/assets/5d72474f-822c-40ab-9152-463eaf27e962" />

