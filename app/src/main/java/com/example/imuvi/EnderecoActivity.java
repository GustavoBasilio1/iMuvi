package com.example.imuvi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class EnderecoActivity extends AppCompatActivity {
//localização do usuario
    LocationManager locationMangaer = null;
    LocationListener locationListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco);
        getSupportActionBar().hide();
        locationMangaer = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }
    //verifica se o gps ta ligado
    public Boolean displayGpsStatus() {
        ContentResolver contentResolver = getBaseContext().getContentResolver();
        boolean gpsStaus = Settings.Secure.isLocationProviderEnabled(contentResolver, LocationManager.GPS_PROVIDER);

        return gpsStaus;
    }
    //recebe as cordenadas
        public  class  MyLocation implements LocationListener{

            @Override
            public void onLocationChanged(@NonNull Location location) {

                String longitude = "Longitude: " +location.getLongitude();
                Log.i("Longitude: ", longitude);
                String latitude = "Latitude: " +location.getLatitude();
                Log.v("Latitude: ", latitude);

                String cidade=null;
                Geocoder geocoder= new Geocoder(getBaseContext(), Locale.getDefault());

                List<Address> addresses;
                try{
                    addresses=geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);
                    if(addresses.size()>0){
                        cidade=addresses.get(0).getLocality();
                        Log.v("city: ", "city: "+cidade);
                        String scity="city: "+cidade;

                        //alert para dizer coodenadas
                        AlertDialog.Builder alert = new AlertDialog.Builder(EnderecoActivity.this);
                        alert.setTitle("Your coordinate: ");
                        alert.setMessage(longitude+"\n "+latitude + "\n"+scity);
                        alert.setPositiveButton("OK",null);
                        alert.show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    //checando a permissão da localização
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                new AlertDialog.Builder(this)
                        .setTitle("Necessary Location Permission")
                        .setMessage("This function needs localization to work")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(EnderecoActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();
            } else {
                //soliita permissao
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }



    public void MostrarLoc(View v){
        Toast.makeText(getApplicationContext(), "Hold Up", Toast.LENGTH_LONG).show();
        Boolean flag = displayGpsStatus();
        if (flag) {

            if(ContextCompat.checkSelfPermission(EnderecoActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(EnderecoActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                            PackageManager.PERMISSION_GRANTED) {

                LocationListener locationListener = new MyLocation();
                locationMangaer.requestLocationUpdates(LocationManager
                        .GPS_PROVIDER, 5000, 10, locationListener);

            } else {
                Toast.makeText(getApplicationContext(), "PERMISSION DENIED ", Toast.LENGTH_LONG).show();
                checkLocationPermission();
            }

        } else {
            Log.i("Gps Status!!", "Your GPS is: OFF");
        }
    }




   /* public void buscarInformacoesGPS(View v) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)   != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(EnderecoActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(EnderecoActivity.this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(EnderecoActivity.this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            return;
        }

        LocationManager mLocManager  = (LocationManager) getSystemService(EnderecoActivity.this.LOCATION_SERVICE);
        MyLocationListener mLocListener = new MyLocationListener();

        mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) mLocListener);

        if (mLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            String texto = "Latitude.: " + MyLocationListener.latitude + "\n" +
                    "Longitude: " + MyLocationListener.longitude + "\n";
            Toast.makeText(EnderecoActivity.this, texto, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(EnderecoActivity.this, "GPS DESABILITADO.", Toast.LENGTH_LONG).show();
        }

        this.mostrarGoogleMaps(MyLocationListener.latitude, MyLocationListener.longitude);
    }

    public void mostrarGoogleMaps(double latitude, double longitude) {
        WebView wv = findViewById(R.id.webv);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude);
    }*/

    }
