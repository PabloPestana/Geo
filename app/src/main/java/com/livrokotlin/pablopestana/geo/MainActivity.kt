package com.livrokotlin.pablopestana.geo

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.locationManager

class MainActivity : AppCompatActivity() {
    val ID_REQUISICAO_GPS = 1
    val tempoAtualizacao : Long = 0
    val distanciaAtualizacao : Float = 0f
    val locationListener = MyLocationListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        validaPermissao()

    }

    class MyLocationListener : LocationListener{
        override fun onLocationChanged(location: Location?){

        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?){

        }

        override fun onProviderDisabled(provider: String?){

        }

        override fun onProviderEnabled(provider: String?){

        }

    }

    fun validaPermissao(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//Permissão concedida (PERMISSION_GRANTED)
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,tempoAtualizacao,distanciaAtualizacao,locationListener)
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,tempoAtualizacao,distanciaAtualizacao,locationListener)
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,tempoAtualizacao,distanciaAtualizacao,locationListener)

        } else {
//Permissão não está concedida (PERMISSION_DANIED)

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),ID_REQUISICAO_GPS)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == ID_REQUISICAO_GPS){
            //resposta da requisição
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //A permissão foi concedida o app vai poder usar o recurso
            }else {
                //A permissão não foi concedida, dasabilitar a funcao que utiliza o recurso negado
            }
        }
    }
    
}
