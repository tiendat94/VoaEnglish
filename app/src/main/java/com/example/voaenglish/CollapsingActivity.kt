package com.example.voaenglish

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.voaenglish.adapter.DeleteAdapter
import com.example.voaenglish.database.DatabaseManager
import com.example.voaenglish.date.DatePickerHelper
import com.example.voaenglish.model.Data
import com.example.voaenglish.model.Dog
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_scrolling.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*
import kotlin.collections.ArrayList

class CollapsingActivity : AppCompatActivity() {

    private lateinit var deleteAdapter: DeleteAdapter
    private val item_list: ArrayList<Data> = ArrayList()
    private var count: Int? = 0

    private lateinit var datePickerDialog: DatePickerHelper

    private val LOCATION_PERMISSION_REQ_CODE = 1000
    private lateinit var fusedLocationClinet: FusedLocationProviderClient
    private var latitude: Double? = 0.0
    private var longitude: Double? = 0.0

    private val countInterface = object : DeleteAdapter.CountCheckBox {
        override fun getCountCheckBox(count: Int) {
            count?.let { textviewCount.text = count.toString() }
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setupAdapter()
        initData()
        datePickerDialog = DatePickerHelper(this@CollapsingActivity, false)
        fusedLocationClinet = LocationServices.getFusedLocationProviderClient(this@CollapsingActivity)
        val realm = Realm.getDefaultInstance()

        chk_select_all?.setOnClickListener {
            if (chk_select_all.isChecked) {
                for (data: Data in item_list) {
                    data.isSelected = true
                    count = count?.plus(data?.count!!)
                }
            } else {
                for (data: Data in item_list) {
                    data.isSelected = false
                    count = count?.minus(data?.count!!)
                }
            }
            textviewCount.text = count.toString()
            deleteAdapter?.notifyDataSetChanged()
        }
        doAsync {
            uiThread {
                btn_delete_all?.setOnClickListener {
                    if (chk_select_all.isChecked) {
                        item_list.clear()
                        deleteAdapter?.notifyDataSetChanged()
                        chk_select_all.isChecked = false
                    }
                    checkPermissionLocation()
                    showDatePickerDialog()
                }
            }

            btn_open_map?.setOnClickListener {
                openMap()
            }
            val dog = Dog("BLack", 2)
            DatabaseManager.instance.saveDog(this@CollapsingActivity, dog)

            var testDB = DatabaseManager.instance.getDog(this@CollapsingActivity, 2)
            testDB?.let {
                Log.d("dog = ", it?.name)
            }

        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQ_CODE -> {
                if (grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission granted
                } else {
                    Toast.makeText(this@CollapsingActivity, "You need to grant permission to access location", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun openMap() {
        val uri = Uri.parse("geo:${latitude},${longitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    private fun getCurrentLocation() {
        //checking location permission
        if (ActivityCompat.checkSelfPermission(this@CollapsingActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@CollapsingActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQ_CODE)
            return
        }
        fusedLocationClinet?.lastLocation.addOnSuccessListener {
            latitude = it.latitude
            longitude = it.longitude

            Log.d("getCurrentLocation", latitude.toString())
            Log.d("getCurrentLocation", longitude.toString())
        }.addOnFailureListener {
            Toast.makeText(this@CollapsingActivity, "Faild on getting current location", Toast.LENGTH_LONG).show()
        }
    }

    fun checkPermissionLocation() {
        Dexter.withActivity(this@CollapsingActivity)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        getCurrentLocation()
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {

                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {

                    }

                }).check()
    }

    private fun setupAdapter() {
        deleteAdapter = DeleteAdapter(this@CollapsingActivity, countInterface)
        recyclerview?.layoutManager = LinearLayoutManager(this@CollapsingActivity)
        recyclerview?.adapter = deleteAdapter
    }

    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)

        datePickerDialog.showDialog(d, m, y, object : DatePickerHelper.CallBackDate {
            override fun onDateSelected(dayofMonth: Int?, month: Int?, year: Int?) {
                val dayStr = if (dayofMonth!! < 10) "0${dayofMonth}" else "${dayofMonth}"
                val mon = month?.plus(1)
                val monthStr = if (mon!! < 10) "0${mon}" else "${mon}"
                Toast.makeText(this@CollapsingActivity, "${dayStr} - ${monthStr} - ${year}", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun initData() {
        item_list?.add(Data("Alpha", false, 10))
        item_list?.add(Data("Beta", false, 20))
        item_list?.add(Data("Cup Cake", false, 30))
        item_list?.add(Data("Donut", false, 40))
        item_list?.add(Data("Ginger Bread", false, 50))
        item_list?.add(Data("Honycomb", false, 60))
        item_list?.add(Data("Jelly Bean", false, 67))
        item_list?.add(Data("Kitkat", false, 22))
        item_list?.add(Data("Lolly Pop", false, 12))
        item_list?.add(Data("Marsh Mallow", false, 43))
        item_list?.add(Data("Nougat", false, 56))
        deleteAdapter?.updateData(item_list)
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}


