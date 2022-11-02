package com.example.aula9;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.aula9.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnPolylineClickListener, GoogleMap.OnPolygonClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(-35.016, 143.321),
                        new LatLng(-34.747, 145.592),
                        new LatLng(-34.364, 147.891),
                        new LatLng(-33.501, 150.217),
                        new LatLng(-32.491, 147.309),
                        new LatLng(-32.491, 147.309)));
        polyline1.setTag("A");
        stylePolyline(polyline1);

        Polyline polyline2 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(-29.501, 119.700),
                        new LatLng(-27.456, 119.672),
                        new LatLng(-25.971, 124.187),
                        new LatLng(-28.081, 126.555),
                        new LatLng(-28.848, 124.229),
                        new LatLng(-28.215, 123.938)));
        polyline2.setTag("B");
        stylePolyline(polyline2);

        Polygon polygon1 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(
                        new LatLng(-27.457, 153.040),
                        new LatLng(-33.852, 151.211),
                        new LatLng(-37.813, 144.962),
                        new LatLng(-34.928, 138.599)));
        polygon1.setTag("alpha");
        stylePolygon(polygon1);

        Polygon polygon2 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(
                        new LatLng(-31.673, 128.892),
                        new LatLng(-31.952, 115.857),
                        new LatLng(-17.785, 122.258),
                        new LatLng(-12.4258, 130.7932)));
        polygon2.setTag("beta");
        stylePolygon(polygon2);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-23.684, 133.903), 4));
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);
    }
    private static final int POLYLINE_STROKE_WIDTH_PX = 12;
    private void stylePolygon(Polygon polygon1) {
    }
    private void stylePolyline(Polyline polyline2){
    }

    @Override
    public void onPolygonClick(Polygon polygon) {
        String type = "";
        Polyline polyline = null;
        if (polyline.getTag() != null) {
            type = polyline.getTag().toString();
        }
        switch (type) {
            case "A":
                polyline.setStartCap(
                        new CustomCap(
                                BitmapDescriptorFactory.fromResource(R.drawable.ic_arrow), 10));
                break;
            case "B":
                polyline.setStartCap(new RoundCap());
                break;
        }
        polyline.setEndCap(new RoundCap());
        polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
        polyline.setColor(COLOR_BLACK_ARGB);
        polyline.setJointType(JointType.ROUND);
        final int PATTERN_GAP_LENGHT_PX = 20;
        final PatternItem DOT = new Dot();
        final PatternItem GAP = new Gap(PATTERN_GAP_LENGHT_PX);

        final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);
    }
    @Override
    public void onPolylineClick(Polyline polyline) {
        if ((polyline.getPattern()) == null && (!polyline.getPattern().contains(DOT)))
        {
        polyline.setPattern(PATTERN_POLYLINE_DOTTED);
        } else{
            polyline.setPattern(null);
        }
        Toast.makeText(this, "Route type" + polyline.getTag().toString(),
                Toast.LENGTH_SHORT.show());
    }
    private static final int COLOR_BLACK_ARGB = 0x000000 ;
    private static final int COLOR_WHITE_ARGB = 0xfffffff;
    private static final int COLOR_GREEN_ARGB = 0xff388E3c;
    private static final int COLOR_PURPLE_ARGB = 0xff81C784;
    private static final int COLOR_ORANGE_ARGB = 0xffF57F17;
    private static final int COLOR_BLUE_ARGB = 0xffF9A825;

    private static final int POLYGON_STROKE_WIDTH_PX = 8;
    private static final int PATTERN_DASH_LENGHT_PX = 20;
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGHT_PX);

    private static final List<PatternItem> PATTERN_POLYGON_BETA = Arrays.asList(DOT, GAP, DASH, GAP);


}