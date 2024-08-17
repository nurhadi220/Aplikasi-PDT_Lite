# Demo PDT Lite Android

### Ringkasan
Aplikasi ini pada dasarnya di ciptakan untuk mencari kode dari suatu barang.

* aplikasi ini akan terus di kembangkan.
* nantinya aplikasi ini dapat melakukan scanning pada barang dan akan mendapatkan kode dari barang tersebut.
* maka ijinkan saya untuk memperlihatkan tampilan depan dari aplikasinya

![Desain tanpa judul (1)](https://github.com/user-attachments/assets/dd050de8-c710-44f7-8a1a-8db4441313e2)

## Penjelasan singkat penggunaan aplikasi

<p>Pada awalnya anda dapat melihat tampilan untuk melakukan input.</p>
  lalu pada bagian input itu sendiri anda bisa memasukan kode.

kode itu berisi data dari barang itu sendiri berupa ``warehouse`` , ``toNumber`` , ``toItem``
tidak lupa juga untuk anda menambahkan `/` sebagai pemisah antara `toNumber` dan `toItem`
<br>
![Desain warehouse](https://github.com/user-attachments/assets/98cc51d3-4f50-4187-9780-6546393ab8af)

Setelah data yang anda inputkan benar maka otomatis akan masuk ke salah 1 dari 2 halaman di atas.

>Note : Pastikan bahwa anda membuat data pick/confirm dengan default (bawaan) 0 

## Cara Penggunaan

### 1. Masuk pada folder `APP` lalu cari folder `XML` dan buka File ``network_securityconfig.xml``
pada bagian `domain` anda dapat mengubah IP dengan IP yang anda miliki.
``` sh
<domain includeSubdomains="true">192.168.0.175</domain>
```
<br>

### 2. Masuk pada folder `java` lalu masuk lagi ke folder `com.example.unitedtractor` dan terakhir masuk folder `api` ubah IP pada file `ApiClient`.
Tugas anda cukup mengubah IP dan PORT yang sesuai dengan anda miliki.
``` sh
object ApiClient {

    private const val BASE_URL = "http://192.168.0.175:5000/warehouse-api/api/v1/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
```
<br>

### 3. Seharusnya dengan begini anda dapat mencoba aplikasi ini.

# Terima kasih 
