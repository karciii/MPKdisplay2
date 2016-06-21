package karolklimek.mpkdisplay2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.lang.System;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import static karolklimek.mpkdisplay2.R.*;

public class Browser extends AppCompatActivity {



    //pobieranie zmniennej z poprzedniej aktywnośći
//  double closestDistance =  getIntent().getExtras().getInt("closestDistance");

   int test = MapsActivity.closestDistanceIteration;




    WebView mWebView;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_browser);


       // closestDistanceIteration =  getIntent().getExtras().getInt("closestDistanceIteration");


        //tablica URL'i przystanków
        String [] markerURLs = new String[50];

        //wypełnianie tablicy linkami
        markerURLs[0]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=503";
        markerURLs[1]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=609";
        markerURLs[2]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=407";
        markerURLs[3]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=109";
        markerURLs[4]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=6";
        markerURLs[5]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=116";
        markerURLs[6]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=121";
        markerURLs[7]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=100";
        markerURLs[8]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=104";
        markerURLs[9]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=617";
        markerURLs[10]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=614";
        markerURLs[11]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=579";
        markerURLs[12]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=577";
        markerURLs[13]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=575";
        markerURLs[14]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=554";
        markerURLs[15]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=551";
        markerURLs[16]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=549";
        markerURLs[17]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=547";
        markerURLs[18]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=545";
        markerURLs[19]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=511";
        markerURLs[20]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=489";
        markerURLs[21]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=478";
        markerURLs[22]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=572";
        markerURLs[23]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=3";
        markerURLs[24]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=6";
        markerURLs[25]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=605";
        markerURLs[26]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=607";
        markerURLs[27]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=641";
        markerURLs[28]="http://sip.ztm.lublin.eu/odjazdyFS.aspx?id=639";
        markerURLs[29]="http://www.sip.ztm.lublin.eu/odjazdyFS.aspx?id=82";




        mWebView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = mWebView.getSettings();

        //włączenie obsługi Java Script
        webSettings.setJavaScriptEnabled(true);


        //wymuszenie otwierania linków przez element WebView, a nie przez przeglądarkę
        mWebView.setWebViewClient(new WebViewClient());

        mWebView.setInitialScale(97);

        //wczytywanie strony tablicy danego przystanku
        mWebView.loadUrl(markerURLs[test]);






    /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }

        });
        */


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Browser Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://karolklimek.mpkdisplay2/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Browser Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://karolklimek.mpkdisplay2/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
