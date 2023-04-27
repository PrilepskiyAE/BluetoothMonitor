package com.prilepskiy.bluetoothmonitor

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.androidgamesdk.gametextinput.Listener
import com.prilepskiy.bluetoothmonitor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //private var btAdapter: BluetoothAdapter? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var sManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // val btManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
       // btAdapter = btManager.adapter

//        val pairedDevices: Set<BluetoothDevice>? = if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) btAdapter?.bondedDevices else null
//        if (pairedDevices!=null)
//            pairedDevices.forEach {
//                Log.d(TAG, "Bluetooh_Device: $it")
//            }


        sManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sListener = object : SensorEventListener {
            override fun onSensorChanged(sEvent: SensorEvent?) {
              binding.tvSensor.setText( "X: ${sEvent?.values?.get(0)}\nY: ${sEvent?.values?.get(1)}\nZ: ${sEvent?.values?.get(2)}")
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

            }

        }
        sManager.registerListener(sListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }






    companion object{
        const val TAG= "MainActivity"

    }
}