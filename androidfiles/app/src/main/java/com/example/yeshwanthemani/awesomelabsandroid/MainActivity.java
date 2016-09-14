package com.example.yeshwanthemani.awesomelabsandroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.client.HttpClient;

import android.content.Intent;
import android.util.Log;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import java.util.ArrayList;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    public void signup(View v)
    {
        EditText eid = (EditText) findViewById(R.id.editText);
        String id = eid.getText().toString();

        EditText ename = (EditText) findViewById(R.id.editText2);
        String name = ename.getText().toString();

        EditText eaddress = (EditText) findViewById(R.id.editText3);
        String address = eaddress.getText().toString();

        EditText ephone = (EditText) findViewById(R.id.editText4);
        String phone = ephone.getText().toString();

        if(phone.length() == 10) { //phone number validation

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("uid", id));
            nameValuePairs.add(new BasicNameValuePair("uname", name));
            nameValuePairs.add(new BasicNameValuePair("uaddr", address));
            nameValuePairs.add(new BasicNameValuePair("uphone", phone));

            InputStream is;
            String result = null;
            String line = null;

            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://awesomelabs/postapi.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
                result = convertStreamToString(is); //userbuiltstring function
                Toast.makeText(getApplicationContext(), result,
                        Toast.LENGTH_LONG).show();

            } catch (Exception e) {

                Toast.makeText(getApplicationContext(), "Server Error",
                        Toast.LENGTH_LONG).show();

            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Invalid Phone number",
                    Toast.LENGTH_LONG).show();
        }

    }

    // Method to convert input stream to string
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public void nextactivity(View v)
    {
        Intent i = new Intent(this,Main2Activity.class);
        startActivity(i);
    }
}
