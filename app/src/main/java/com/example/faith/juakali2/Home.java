package com.example.faith.juakali2;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Home extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    int[] images = {R.drawable.carpenter, R.drawable.cleaning, R.drawable.electrician2, R.drawable.painter, R.drawable.plumber};

    String[] names = {"Carpenter", "Cleaner", "Electrician", "Painter", "Plumber"};

    ListView listView;

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = (ListView) findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void others(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.other_dialog);

        EditText etOher = (EditText) dialog.findViewById(R.id.etOther);
        Button btnAdd = (Button) dialog.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, i);
        calendar.set(Calendar.MONTH, i1);
        calendar.set(Calendar.DAY_OF_MONTH, i2);
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        Toast.makeText(getApplicationContext(), currentDate, Toast.LENGTH_LONG).show();
    }



    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.custom_dialog, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            TextView tvName = (TextView) view.findViewById(R.id.textName);
            TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
            //TextView tvDescription = (TextView)view.findViewById(R.id.tvDescription);

            imageView.setImageResource(images[i]);
            tvName.setText(names[i]);

            tvDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    populateDialog();
                }
            });

            /*view.setBackgroundColor(Color.WHITE);
            view.setBackgroundResource(R.drawable.white);*/

            return view;
        }

        public void populateDialog() {
            final Dialog dialog = new Dialog(Home.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.date_dialog);
            dialog.show();

            //Dates
            RadioButton radioToday = (RadioButton) dialog.findViewById(R.id.radioToday);
            RadioButton radioCalendar = (RadioButton) dialog.findViewById(R.id.radioCalendar);

            //Location
            RadioButton radioLocation = (RadioButton) dialog.findViewById(R.id.radioLocation);
            RadioButton radioMap = (RadioButton) dialog.findViewById(R.id.radioMap);

            //Get today's date
            radioToday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Date today = new Date();
                    Toast.makeText(getApplicationContext(), "Todays date is :" + today, Toast.LENGTH_LONG).show();
                }
            });

            //Get calendar
            radioCalendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragment datePicker = new com.example.faith.juakali2.DatePicker();
                    datePicker.show(getSupportFragmentManager(), "data_picker");
                }
            });

            radioLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                    locationListener = new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            Toast.makeText(getApplicationContext(),"Latitude is "+location.getLatitude()+"Longitude "+location.getLongitude(),Toast.LENGTH_LONG).show();
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();


                        }

                        @Override
                        public void onStatusChanged(String s, int i, Bundle bundle) {

                        }

                        @Override
                        public void onProviderEnabled(String s) {

                        }

                        @Override
                        public void onProviderDisabled(String s) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }
                    };
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(new String[]{
                                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.INTERNET}, 10);
                        }

                        return;
                    } else {
                        locationManager.requestLocationUpdates("gps", 30000, 0, locationListener);
                    }
                }
            });

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }locationManager.requestLocationUpdates("gps", 30000, 0, locationListener);
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}


