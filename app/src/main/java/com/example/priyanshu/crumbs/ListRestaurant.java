package com.example.priyanshu.crumbs;

import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ListRestaurant extends AppCompatActivity {

    ArrayList<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_restaurant);
        FetchLocationTask flt = new FetchLocationTask();
        flt.execute();
    }

    private ArrayList<String[]> getLocationDataFromJson(String JsonStr)
            throws JSONException {
        Log.d("FRIENDS", JsonStr);
        // These are the names of the JSON objects that need to be extracted.
        final String OWM_RESULTS = "objects";
        final String OWM_GEOMETRY = "name";
        final String OWM_LOCATION = "street_address";
        HashMap hm = new HashMap();

        JSONObject locationJson = new JSONObject(JsonStr);
        JSONArray locationArray = locationJson.getJSONArray(OWM_RESULTS);
        ArrayList<String[]> result= new ArrayList<>();
        for(int i=0;i<locationArray.length();++i){
            JSONObject JObject= locationArray.getJSONObject(i);
            String[] result_temp = new String[2];
            result_temp[0] =JObject.getString(OWM_GEOMETRY);
            result_temp[1] =JObject.getString(OWM_LOCATION);
            result.add(result_temp);
        }
        return result;
    }
    public class FetchLocationTask extends AsyncTask<Void, Void, ArrayList<String[]>> {

        @Override
        protected ArrayList<String[]> doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String JsonStr = null;

            try {
                final String BASE_ADDR = "https://api.locu.com/v1_0/venue/search/?";
                final String ADDRESS_PARAM = "country";
                final String ADDRESS_VALUE = "Singapore";
                final String KEY_PARAM = "api_key";
                final String KEY_VALUE = "f4f39bf9ed72132112cd25e9ddcdc4063fd553fd";
                Uri builtUri = Uri.parse(BASE_ADDR).buildUpon()
                        .appendQueryParameter(ADDRESS_PARAM, ADDRESS_VALUE)
                        .appendQueryParameter(KEY_PARAM, KEY_VALUE)
                        .build();
                Log.d("REST",builtUri.toString());
                URL url = new URL(builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                JsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e("FRIENDS", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("FRIENDS", "Error closing stream", e);
                    }
                }
            }
            try {
                return getLocationDataFromJson(JsonStr);
            } catch (JSONException e) {
                Log.e("FRIENDS", e.getMessage(), e);
                e.printStackTrace();
            }

            // This will only happen if there was an error getting or parsing the forecast.
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String[]> result) {
            if (result != null) {
                 names = new ArrayList<>() ;
                         ArrayList <String> address = new ArrayList<>();
                for(int i=0;i<result.size();++i){
                    names.add(result.get(i)[0]);
                    address.add(result.get(i)[1]);
                }

                String[] names_list = new String[names.size()];
                names_list = names.toArray(names_list);
                ArrayAdapter<String> rest_list_adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,names_list){
                    @Override
                    public View getView(int position, View convertView,
                                        ViewGroup parent) {
                        View view =super.getView(position, convertView, parent);
                        TextView textView=(TextView) view.findViewById(android.R.id.text1);
            /*YOUR CHOICE OF COLOR*/
                        textView.setTextColor(Color.BLACK);
                        return view;
                    }
                };
                ListView lw = (ListView) findViewById(R.id.rest_list_view);
                lw.setAdapter(rest_list_adapter);
                AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.rest_name);
                actv.setAdapter(rest_list_adapter);
                actv.setThreshold(1);

            }
        }
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
        if (id == R.id.done) {
            AutoCompleteTextView ac= (AutoCompleteTextView)findViewById(R.id.rest_name);
            String name;
            if(ac.getListSelection()==-1)
                name="Prata Wala";
            else
             name=names.get(ac.getListSelection());
            changeRestData(name);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void changeRestData(String name)
    {
        TextView restName = (TextView) findViewById(R.id.rest_text);
        restName.setText(name);
        ImageView restImg = (ImageView) findViewById(R.id.order_rest_img);
        restImg.setImageResource(R.drawable.i);
        finish();
    }
}
