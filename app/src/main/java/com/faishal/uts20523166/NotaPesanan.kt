package com.faishal.uts20523166

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class NotaPesanan : AppCompatActivity() {

    lateinit var txTanggal: TextView
    lateinit var txNamaNim: TextView
    lateinit var txPembayaran: TextView
    lateinit var txPesanan: TextView
    lateinit var txLokasiPengiriman: TextView
    lateinit var txWaktuPengiriman: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota_pesanan)

        txTanggal = findViewById(R.id.txTanggal)
        txNamaNim = findViewById(R.id.txNamaNim)
        txPembayaran = findViewById(R.id.txPembayaran)
        txPesanan = findViewById(R.id.txPesanan)
        txLokasiPengiriman = findViewById(R.id.txLokasiPengiriman)
        txWaktuPengiriman = findViewById(R.id.txWaktuPengiriman)

        txTanggal.text = DataPesanan.tanggal
        txNamaNim.text = DataPesanan.nama + " (" + DataPesanan.nim + ")"
        txPembayaran.text = "Metode Pembayaran: " + DataPesanan.metodePembayaran
        txPesanan.text = DataPesanan.daftarPesanan
        txLokasiPengiriman.text = DataPesanan.lokasiPengiriman
        txWaktuPengiriman.text = DataPesanan.waktuPengiriman


    }
}