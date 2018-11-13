package com.example.ibnsina.socialtrafficmonitoring;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import static android.content.ContentValues.TAG;
import static com.example.ibnsina.socialtrafficmonitoring.R.id.layout_root;
import static com.example.ibnsina.socialtrafficmonitoring.R.id.map;


/**
 * Created by ibnsina on 1/19/18.
 */

public class Journey_Fragment extends Fragment implements OnMapReadyCallback,LocationListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    Required_data_Set_Get Server_data_get = new Required_data_Set_Get();

    private View mProgressView;
    JSONArray arrayPersonal;

    private loadflag lAuthTask = null;



    private GoogleMap mMap;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;

    View viewer;
    //private GoogleMap mMap;
    MapView mapView;

    public Journey_Fragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewer = inflater.inflate(R.layout.activity_fragment_journey,container,false);



        return viewer;
    }



    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) viewer.findViewById(R.id.map);
        mProgressView = viewer.findViewById(R.id.loadingprogress);

        lAuthTask = new loadflag(1);
        lAuthTask.execute();


        if(mapView !=null)
        {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);

        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(viewer.getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        //multiple marker.
        int itr;
        MapsInitializer.initialize(getContext());
        mMap = googleMap;
        googleMap.setMapType(googleMap.MAP_TYPE_NORMAL);
        LatLng Dhaka = new LatLng(23.777176, 90.399452);
//        for(itr=0;itr<arrayPersonal.length(); itr++)
//        {
//            try {
//                JSONObject jsonChildNode = arrayPersonal.getJSONObject(1);
//                LatLng latlong = new LatLng(Double.parseDouble(jsonChildNode.getString("signal_lat")), Double.parseDouble(jsonChildNode.getString("signal_long")));
//                googleMap.addMarker(new MarkerOptions().position(latlong).title(""+jsonChildNode.getString("signal_name")+"").snippet(""+jsonChildNode.getString("signal_details")+""));
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
        //JSONObject jsonChildNode = arrayPersonal.getJSONObject(0);
        //flag = jsonChildNode.getString("success");

        LatLng Mirpur_10=new LatLng(23.807001,90.368611);
        LatLng Mirpur_2=new LatLng(23.805052,90.363472 );
        LatLng Mirpur_Zoo_Circle=new LatLng(23.799785,90.355290);
        LatLng Mirpur_1=new LatLng(23.798589,90.353288);
        LatLng Mirpur_PSC=new LatLng(23.803211,90.378455);

        googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).position(Mirpur_10).title("Mirpur 10").snippet(" "));
        googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).position(Mirpur_2).title("Mirpur 2").snippet(" "));
        googleMap.addMarker(new MarkerOptions().position(Mirpur_Zoo_Circle).title("Mirpur Zoo Circle").snippet(" "));
        googleMap.addMarker(new MarkerOptions().position(Mirpur_1).title("Mirpur 1").snippet(" "));
        googleMap.addMarker(new MarkerOptions().position(Mirpur_PSC).title("Mirpur PSC").snippet(" "));

        CameraPosition cameraPosition = CameraPosition.builder().target(new LatLng(23.777176, 90.399452)).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Mirpur_10));

    }


    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(viewer.getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(viewer.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }

    public class loadflag extends AsyncTask<Void, Void, Boolean> {

        private int areaid;
        int flag;

        loadflag(int areaid) {
            this.areaid = areaid;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            lAuthTask = null;
            showProgress(false);


            if (flag == 1) {
                //Toast.makeText(LoginActivity.this, verifylogin(mEmail,mPassword), Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(viewer.getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            lAuthTask = null;
            showProgress(false);
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            if (areaid != 0) {
                try {
                    arrayPersonal = new JSONArray(Server_data_get.getmagflag(areaid));
                    //JSONObject jsonChildNode = arrayPersonal.getJSONObject(0);
                    //flag = jsonChildNode.getString("success");

                    if (arrayPersonal.length() > 0)
                        flag = 1;
                    else
                        flag = 0;

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());


                }
            } else {


            }

            return true;

        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }


}
