package com.jerehmiah.testredirects;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.HttpURLConnection;

public class MainActivity extends Activity {

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        wv = (WebView) findViewById(R.id.webView);
        loadPage("http://10.184.188.180:4567/302me");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void loadPage(final String url){
        RequestQueue queue = Volley.newRequestQueue(this);

        // Disable redirects to track last loaded URL for resolving local URLs in HTML content.
        // This would appear to be the setting which is no longer honored in marshmallow
        HttpURLConnection.setFollowRedirects(false);


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                wv.loadDataWithBaseURL(url,response, "text/html", "UTF-8", null);
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse.statusCode == HttpURLConnection.HTTP_SEE_OTHER || error.networkResponse.statusCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                    loadPage(error.networkResponse.headers.get("Location"));
                }

            }
        });
        queue.add(request);


    }
}
