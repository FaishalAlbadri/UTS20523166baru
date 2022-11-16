package com.faishal.uts20523166

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {


    lateinit var etNama: EditText
    lateinit var etNim: EditText
    lateinit var cbGeprek: CheckBox
    lateinit var cbEsTeh: CheckBox
    lateinit var etGeprek: EditText
    lateinit var etEstTeh: EditText
    lateinit var teksWaktu: TextView
    lateinit var spPembayaran: Spinner
    lateinit var spLokasi: Spinner

    var jam = 0
    var menit = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNama = findViewById(R.id.etNama)
        etNim = findViewById(R.id.etNim)
        cbGeprek = findViewById(R.id.cbGeprek)
        cbEsTeh = findViewById(R.id.cbEsTeh)
        etGeprek = findViewById(R.id.etGeprek)
        etEstTeh = findViewById(R.id.etEsTeh)
        spPembayaran = findViewById(R.id.spPembayaran)
        spLokasi = findViewById(R.id.spLokasi)
        teksWaktu = findViewById(R.id.txWaktu)

        ArrayAdapter.createFromResource(
            this,
            R.array.jenisBayar,
            android.R.layout.simple_spinner_item
        ).also { listBayar ->
            listBayar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spPembayaran.adapter = listBayar
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.lokasiPengiriman,
            android.R.layout.simple_spinner_item
        ).also { listlokasi ->
            listlokasi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spLokasi.adapter = listlokasi
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fBtnOrder(view: View) {
        val sekarang = LocalDateTime.now()
        val formatTanggal = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)")

        DataPesanan.tanggal = sekarang.format(formatTanggal).toString()
        DataPesanan.nama = etNama.text.toString()
        DataPesanan.nim = etNim.text.toString()
        DataPesanan.daftarPesanan = RangkumPesanan()
        DataPesanan.metodePembayaran = spPembayaran.selectedItem.toString()
        DataPesanan.lokasiPengiriman = spLokasi.selectedItem.toString()
        DataPesanan.waktuPengiriman = teksWaktu.text.toString()

        val intNotaPesanan = Intent(this, NotaPesanan::class.java)
        startActivity(intNotaPesanan)
    }

    fun fSetWaktu(view: View) {
        getWaktuSaatIni()
        TimePickerDialog(this, this, jam, menit, true).show()
    }

    private fun getWaktuSaatIni() {
        val kal: Calendar = Calendar.getInstance()
        jam = kal.get(Calendar.HOUR_OF_DAY)
        menit = kal.get(Calendar.MINUTE)
    }

    fun RangkumPesanan(): String {
        var pesanan = ""

        if (cbGeprek.isChecked) {
            pesanan += etGeprek.text.toString() + " - "
            pesanan += cbGeprek.text.toString() + "\n"
        }

        if (cbEsTeh.isChecked) {
            pesanan += etEstTeh.text.toString() + " - "
            pesanan += cbEsTeh.text.toString() + "\n"
        }

        return pesanan
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        jam = hourOfDay
        menit = minute

        var jamS = jam.toString()
        var menitS = menit.toString()


        if (jamS.length == 1) {
            jamS = "0" + jamS
        }

        if (menitS.length == 1) {
            menitS = "0" + menitS
        }


        teksWaktu.text = "${jamS}:${menitS}"
    }
}