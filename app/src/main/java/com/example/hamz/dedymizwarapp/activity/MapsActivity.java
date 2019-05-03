package com.example.hamz.dedymizwarapp.activity;



import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;




import android.util.Log;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;


import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.api.ApiClient;
import com.example.hamz.dedymizwarapp.api.ApiInterface;
import com.example.hamz.dedymizwarapp.list.CityList;
import com.example.hamz.dedymizwarapp.list.DistrictList;
import com.example.hamz.dedymizwarapp.list.MapList;
import com.example.hamz.dedymizwarapp.model.District;
import com.example.hamz.dedymizwarapp.model.Map;
import com.example.hamz.dedymizwarapp.model.City;
import com.example.hamz.dedymizwarapp.utils.CommonUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private String TAG = MapsActivity.class.getSimpleName();

    private Context context;

    private GoogleMap mMap;
    private Marker myMarker[];
    private List<Map> mListMarker = new ArrayList<>();

    private ImageView searchIcon;
    private ArrayList<City> cities;
    private ArrayList<District> districts;

    private TextView tps, lihatLokasi, description, lokasi;
    private LinearLayout layoutBottomSheet;
    private BottomSheetBehavior sheetBehavior;

    private ApiInterface apiInterface;
//    private AlertDialog dialog;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        context = this;
        lihatLokasi = findViewById(R.id.lihat_lokasi);
        description = findViewById(R.id.description);
        lokasi = findViewById(R.id.lokasi);

        searchIcon = findViewById(R.id.search_icon);

        tps = findViewById(R.id.tps);
        layoutBottomSheet = findViewById(R.id.bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        apiInterface = new ApiClient().getClient().create(ApiInterface.class);
//        dialog = new SpotsDialog(context);




        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);


        CameraPosition bandung = new CameraPosition(new LatLng(-6.90389, 107.61861),
                7.5f,
                mMap.getCameraPosition().tilt,
                mMap.getCameraPosition().bearing);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(bandung), 4000, null);

        strokeGeoJSON();

        getAllDataLocationLatLng();

        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                for (int i = 0; i < mListMarker.size(); i++) {
                    myMarker[i].setIcon(BitmapDescriptorFactory.fromResource(R.drawable.point_on));
                    sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        });


    }

    private void strokeGeoJSON() {
        try {
            GeoJsonLayer layer = new GeoJsonLayer(mMap, R.raw.jawa_barat, getApplicationContext());

            GeoJsonPolygonStyle style = layer.getDefaultPolygonStyle();
            style.setFillColor(Color.TRANSPARENT);
            style.setStrokeColor(Color.MAGENTA);
            style.setStrokeWidth(3F);

            layer.addLayerToMap();

        } catch (IOException ex) {
            Log.e("IOException", ex.getLocalizedMessage());
        } catch (JSONException ex) {
            Log.e("JSONException", ex.getLocalizedMessage());
        }
    }

    private void showAlertDialog(){
         View view = getLayoutInflater().inflate(R.layout.search_modal, null);

        final Spinner spnrKota = view.findViewById(R.id.spinner_kota);
        final Spinner spnrKec  = view.findViewById(R.id.spinner_kec);
        TextView cariLokasi = view.findViewById(R.id.cari_lokasi);

        ArrayAdapter<City> adapter = new ArrayAdapter<City>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrKota.setAdapter(adapter);

        spnrKota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                districts = new ArrayList<>();
                loadDistrict(cities.get(position).getId(), spnrKec);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        cariLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "ID Kec : " + districts.get(spnrKec.getSelectedItemPosition()).getName(), Toast.LENGTH_SHORT).show();
                int id = districts.get(spnrKec.getSelectedItemPosition()).getId();
                lokasi.setText(spnrKota.getSelectedItem() + ", " + spnrKec.getSelectedItem());
                searchLocaation(id);
                alertDialog.dismiss();
            }
        });

    }

    private void getAllDataLocationLatLng() {
        mProgressDialog = CommonUtils.showLoadingDialog(this);

        ApiInterface apiService = new ApiClient().getClient().create(ApiInterface.class);
        Call<MapList> call = apiService.location();
        call.enqueue(new Callback<MapList>() {
            @Override
            public void onResponse(Call<MapList> call, Response<MapList> response) {
                mProgressDialog.cancel();
                mListMarker = response.body().getmData();
                myMarker = new Marker[mListMarker.size()];
                initMarker(mListMarker);
                loadCities();
            }

            @Override
            public void onFailure(Call<MapList> call, Throwable t) {
                mProgressDialog.cancel();
                Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initMarker(List<Map> listData) {
        //iterasi semua data dan tampilkan markernya
        for (int i = 0; i < mListMarker.size(); i++) {
            //set latlng nya
            LatLng location = new LatLng(Double.parseDouble(mListMarker.get(i).getLatutide()), Double.parseDouble(mListMarker.get(i).getLongitude()));
            //tambahkan markernya
            MarkerOptions marker = new MarkerOptions().position(location).title(mListMarker.get(i).getImageLocationName()).snippet("This is destination").icon(BitmapDescriptorFactory.fromResource(R.drawable.point_on));
            Marker markers = mMap.addMarker(marker);
            myMarker[i] = markers;

            //set latlng index ke 0
//            LatLng latLng = new LatLng(Double.parseDouble(mListMarker.get(0).getLatutide()), Double.parseDouble(mListMarker.get(0).getLongitude()));
            //lalu arahkan zooming ke marker index ke 0
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude,latLng.longitude), 11.0f));
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

//        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.point_select));
        for (int i = 0; i < mListMarker.size(); i++) {
            if (marker.equals(myMarker[i])) {
                myMarker[i].setIcon(BitmapDescriptorFactory.fromResource(R.drawable.point_on));
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                tps.setText(mListMarker.get(i).getImageLocationName());
                final int finalI = mListMarker.get(i).getId();
                lihatLokasi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MapsActivity.this, DetailActivity.class);
                        intent.putExtra("id", finalI);
                        startActivity(intent);
                    }
                });
                description.setText(mListMarker.get(i).getDescription());
            } else {
                myMarker[i].setIcon(BitmapDescriptorFactory.fromResource(R.drawable.point_off));
            }
        }
        return false;
    }

    private void loadCities()  {

        mProgressDialog = CommonUtils.showLoadingDialog(this);
        apiInterface.cities().enqueue(new Callback<CityList>() {
            @Override
            public void onResponse(Call<CityList> call, Response<CityList> response) {
                String http_status = Integer.toString(response.code());

                if (response.isSuccessful()) {
                    cities = response.body().getCities();
                    mProgressDialog.cancel();
                }

                Log.d(TAG, "http status code : " + http_status);
            }

            @Override
            public void onFailure(Call<CityList> call, Throwable t) {
                Log.e(TAG, "on Failure : " + t.getMessage());
            }
        });
    }

    private void loadDistrict(int city_id, final Spinner spinner) {
        mProgressDialog = CommonUtils.showLoadingDialog(this);
        apiInterface.districts(city_id).enqueue(new Callback<DistrictList>() {
            @Override
            public void onResponse(Call<DistrictList> call, Response<DistrictList> response) {
                String http_status = Integer.toString(response.code());

                if (response.isSuccessful()) {
                    districts = response.body().getDistricts();

                    ArrayAdapter<District> adapterDistrict = new ArrayAdapter<District>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, districts);
                    adapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapterDistrict);

                    mProgressDialog.cancel();
                }

                Log.d(TAG, "http status code : " + http_status);
            }

            @Override
            public void onFailure(Call<DistrictList> call, Throwable t) {

            }
        });
    }

    private void searchLocaation(int district_id) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Menampilkan data marker ..");
        dialog.show();

        ApiInterface apiService = new ApiClient().getClient().create(ApiInterface.class);
        Call<MapList> call = apiService.locationId(district_id);
        call.enqueue(new Callback<MapList>() {
            @Override
            public void onResponse(Call<MapList> call, Response<MapList> response) {
                dialog.dismiss();

                mMap.clear();
                mListMarker = response.body().getmData();
                myMarker = new Marker[mListMarker.size()];
                strokeGeoJSON();
                initMarker(mListMarker);

            }

            @Override
            public void onFailure(Call<MapList> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
