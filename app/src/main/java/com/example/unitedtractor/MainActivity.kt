package com.example.unitedtractor

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.unitedtractor.api.ApiClient
import com.example.unitedtractor.api.model.TransferResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi view
        val submitButton: Button = findViewById(R.id.submit)
        val toFieldInput: EditText = findViewById(R.id.toFieldInput)


        // Set onClickListener untuk tombol submit
        submitButton.setOnClickListener {
            val toField = toFieldInput.text.toString()

            if (toField.isNotEmpty()) {
                // Misalkan format input adalah "CKG2005/010"
                val regex = Regex("([A-Za-z]+)(\\d+)/(\\d+)")
                val matchResult = regex.matchEntire(toField)

                if (matchResult != null) {
                    // Pisahkan input menjadi warehouse, toNumber, dan toItem
                    val (warehouse, toNumber, toItem) = matchResult.destructured

                    // Memanggil API dengan field yang dipisahkan
                    ApiClient.transferServices.getTransferByFields(warehouse, toNumber, toItem).enqueue(object : Callback<TransferResponse> {
                        override fun onResponse(call: Call<TransferResponse>, response: Response<TransferResponse>) {
                            if (response.isSuccessful && response.body()?.status == 200) {
                                val transferData = response.body()?.data
                                if (transferData != null) {
                                    when {
                                        transferData.qtyConfirm != null && transferData.qtyConfirm > 0 && transferData.confirm == true -> {
                                            // Tampilkan AlertDialog jika qtyConfirm lebih dari 0 dan confirm bernilai true
                                            AlertDialog.Builder(this@MainActivity)
                                                .setTitle("Informasi")
                                                .setMessage("TO Number ini sudah dikonfirmasi.")
                                                .setPositiveButton("OK", null)
                                                .show()
                                        }
                                        transferData.qtyPick != null && transferData.qtyPick > 0 && transferData.pick == true -> {
                                            val intent = Intent(this@MainActivity, EditConfirmActivity::class.java)
                                            intent.putExtra("transfer_data", transferData)
                                            startActivity(intent)
                                        }
                                        else -> {
                                            val intent = Intent(this@MainActivity, EditPickingActivity::class.java)
                                            intent.putExtra("transfer_data", transferData)
                                            startActivity(intent)
                                        }
                                    }
                                }
                            } else {
                                // Data tidak ditemukan
                                Toast.makeText(this@MainActivity, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<TransferResponse>, t: Throwable) {
                            // Gagal menghubungi server
                            Toast.makeText(this@MainActivity, "Gagal menghubungi server", Toast.LENGTH_SHORT).show()
                        }
                    })
                } else {
                    // Format input salah
                    Toast.makeText(this@MainActivity, "Format input salah", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Jika input belum diisi
                Toast.makeText(this, "Harap masukkan field yang benar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
