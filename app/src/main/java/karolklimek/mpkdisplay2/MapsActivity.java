package karolklimek.mpkdisplay2;

import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{


    public static final String TAG = MapsActivity.class.getSimpleName();
    //on connection failed exception
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    //markers array class declaration
    double [][] markersPoints = new double [50][2];

    //actual GPS position
    double actualGpsLat;
    double actualGpsLon;
    //Latitude i Longitude combo variable of current location
    LatLng latLng;
    static int closestDistanceIteration;

    //skróty do usług google api
    private GoogleApiClient client;
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    private LocationRequest mLocationRequest;

    //flaga, sprawdzająca wykonanie szukania najbliższego przystanku
    boolean onStartFlag = false;

    public static double closestDistance;



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Sprawdzanie SupportMapFragment i powiadamianie, kiedy mapa jest gotowa do użycia
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


        //request położenia ustawiny na maksymalną odokładność oraz najwyższy piorytet
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 sekund w milisekunach
                .setFastestInterval(1 * 1000); // 1 sekunda w milisekundach
        System.out.println("Location Request");


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




        //shortcut
        mMap = googleMap;


        //pozycja początkowa kamery z wartoscią zoom określoną w folat
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.254, 22.543), 11.0f));
        System.out.println("move camera");




        //TODO: how name markers? maybe add another string arraxsy
        //markers adding


        //TODO: dynamic array size
      for (int i = 0; i <= 12; i++ )
      {

            mMap.addMarker(new MarkerOptions().position(new LatLng(markersPoints[i][0], markersPoints[i][1])));

      }

        //gps location active
        mMap.setMyLocationEnabled(true);




        //android.os.SystemClock.sleep(2000);

        System.out.print("closest distance iteration zoom:");
        System.out.println(closestDistanceIteration);



        //zoom camera to nearest marker

        if(onStartFlag == true) {


              mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.254, 22.543), 15.0f), 5000, new GoogleMap.CancelableCallback()
      //    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(actualGpsLat, actualGpsLon), 15.0f), 5000, new GoogleMap.CancelableCallback()

            {

                @Override
                public void onFinish() {



                    goToBrowserActivity();

                }

                @Override
                public void onCancel() {

                }

            });
        }
            System.out.println("closest distance iteration zoom");
            System.out.println(closestDistanceIteration);


        }




    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MPKdisplay", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://karolklimek.mpkdisplay2/http/host/path")

        );

        //markers position declaration have to be here cause of onStart priority before onMapReady
        markersPoints[0][0] = 51.240727;  markersPoints[0][1]= 22.520691;
        markersPoints[1][0] = 51.275169;  markersPoints[1][1]= 22.504519;
        markersPoints[2][0] = 51.195354;  markersPoints[2][1]= 22.538155;
        markersPoints[3][0] = 51.269831;  markersPoints[3][1]= 22.572518;
        markersPoints[4][0] = 51.248390;  markersPoints[4][1]= 22.558247;
        markersPoints[5][0] = 51.251402;  markersPoints[5][1]= 22.569785;
        markersPoints[6][0] = 51.255649;  markersPoints[6][1]= 22.575366;
        markersPoints[7][0] = 51.258825;  markersPoints[7][1]= 22.572615;
        markersPoints[8][0] = 51.263119;  markersPoints[8][1]= 22.572804;
        markersPoints[9][0] = 51.262898;  markersPoints[9][1]= 22.505339;
        markersPoints[10][0] = 51.260099;  markersPoints[10][1]= 22.510243;
        markersPoints[11][0] = 51.251085;  markersPoints[11][1]= 22.527075;
        markersPoints[12][0] = 51.250261;  markersPoints[12][1]= 22.532625;
        markersPoints[13][0] = 51.249540;  markersPoints[13][1]= 22.537101;
        markersPoints[14][0] = 51.231318;  markersPoints[14][1]= 22.482630;
        markersPoints[15][0] = 51.237895;  markersPoints[15][1]= 22.496918;
        markersPoints[16][0] = 51.242015;  markersPoints[16][1]= 22.507628;
        markersPoints[17][0] = 51.235336;  markersPoints[17][1]= 22.510941;
        markersPoints[18][0] = 51.241990;  markersPoints[18][1]= 22.507632;
        markersPoints[19][0] = 51.230040;  markersPoints[19][1]= 22.512911;
        markersPoints[20][0] = 51.227970;  markersPoints[20][1]= 22.525351;
        markersPoints[21][0] = 51.245067;  markersPoints[21][1]= 22.539607;
        markersPoints[22][0] = 51.248613;  markersPoints[22][1]= 22.542854;
        markersPoints[23][0] = 51.247591;  markersPoints[23][1]= 22.552802;
        markersPoints[24][0] = 51.248384;  markersPoints[24][1]= 22.558259;
        markersPoints[25][0] = 51.266376;  markersPoints[25][1]= 22.504949;
        markersPoints[26][0] = 51.272226;  markersPoints[26][1]= 22.504999;
        markersPoints[27][0] = 51.267658;  markersPoints[27][1]= 22.512973;
        markersPoints[28][0] = 51.265913;  markersPoints[28][1]= 22.516806;
        markersPoints[29][0] = 51.269781;  markersPoints[29][1]= 22.569370;


      //  Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
  //     handleNewLocation(location);





        System.out.print("najbliższy przystanek final iteracja: ");
        System.out.println(closestDistanceIteration);
        System.out.print("najbliszszy przystanek final odleglosc");
        System.out.println(closestDistance);





        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction2 = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://karolklimek.mpkdisplay2/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction2);

        onStartFlag = true;


    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://karolklimek.mpkdisplay2/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
        //added
        mGoogleApiClient.disconnect();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
          //  LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, (com.google.android.gms.location.LocationListener) this);
            mGoogleApiClient.disconnect();
       }
    }

    @Override
    protected void onResume() {
        super.onResume();

        mGoogleApiClient.connect();
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {


    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "Location services connected.");
        System.out.println("Location services connected");
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        //sprawdzanie wyjątku dla podanej lokacji
        if (location == null) {

            //location services exception
        // LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);

        }
        else {
            System.out.println();
            handleNewLocation(location);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        handleNewLocation(location);

    }



    @Override
    public void onConnectionSuspended(int i) {

        Log.i(TAG, "Location services suspended. Please reconnect.");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }


    private void handleNewLocation(Location location)
    {
    Log.d(TAG, location.toString());
        //logowanie pobierania wartośći szerokości i długości geograficznych
        System.out.println("handleNewLocation method called");

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        actualGpsLat = currentLatitude;
        actualGpsLon = currentLongitude;

        System.out.println("actualgpslat111");
        System.out.println(actualGpsLat);

        //nowa zmienia kumulująca wartości dla wygody użycia
    latLng = new LatLng(currentLatitude, currentLongitude);

        Location locationA = new Location("point A");
        locationA.setLatitude(actualGpsLat);
        locationA.setLongitude(actualGpsLon);


        System.out.println("actual gps lat");
        System.out.println(actualGpsLat);

        System.out.println("actual gps lon");
        System.out.println(actualGpsLon);



        //pętla przechodząca przez tablice z długościami i szerokościami danych przystanków

        double distance;
        //distance = 0.0;

        //zmienna do inicjalizacji warunku dla najbliższego przystanku

        closestDistance = 99999999.9999999;




        //pętla mierząca dystans do każdego elementu tablicy
        for (int i=0; i <= 19; i++) {
            Location locationB = new Location("point B");
            locationB.setLatitude(markersPoints[i][0]);
            locationB.setLongitude(markersPoints[i][1]);

            distance = locationA.distanceTo(locationB);
            System.out.print("Distance:  ");
            System.out.println(distance);

            System.out.print("closestDistance loop iteration: ");
            System.out.println(i);


            //warunek porównania najbliższego dystansu
            if(distance < closestDistance)
            {
                closestDistance = distance;
                closestDistanceIteration = i;


            }
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(actualGpsLat, actualGpsLon), 15.0f), 5000, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {

                goToBrowserActivity();

            }

            @Override
            public void onCancel() {

            }
        });


                //TODO: open desc on start
        //dodawanie markera pokazującego aktualną pozycję, z której liczymy dystans do przystanku
    MarkerOptions options = new MarkerOptions()
        .position(latLng);
          //  .title("Jesteś tutaj!")


    mMap.addMarker(options);

    }

    public void goToBrowserActivity()
    {
        //wywoływanie przejśćia do nowej aktywności aplikacji
        Intent i = new Intent(this, Browser.class);
        //przekazywanie wartosci o najbliżyszym przystanku do nowej klasy
        i.putExtra("closestDistanceIteration", closestDistanceIteration);
        startActivity(i);
    }


    public void animateCamera()
    {
        //animacja przybliżenia kamery do najbliższego przystanku
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(actualGpsLat, actualGpsLon), 15.0f), 5000, new GoogleMap.CancelableCallback()

        {

            @Override
            public void onFinish() {

                //wywołanie przejścia funkcji do nowej aktywności
                goToBrowserActivity();


            }

            @Override
            public void onCancel() {

            }

    } );

}}