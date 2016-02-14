package com.example.priyanshu.crumbs;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class AddFriends extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        final ArrayList<ParseUser> users = new ArrayList<>();
        final ArrayList<String> user_list_array_list = new ArrayList<>();
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username", "");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(final List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    Log.d("FRIENDS", String.valueOf(objects.size()));
                    users.addAll(objects);
                    if (users.size() != 0) {
                        for (int i = 0; i < users.size(); ++i) {
                            if (users.get(i) != ParseUser.getCurrentUser()) {
                                user_list_array_list.add(users.get(i).getString("name"));
                            }
                        }
                    } else {
                        Log.d("FRIENDS", "Size is 0");
                    }
                    String[] user_list = new String[user_list_array_list.size()];
                    user_list = user_list_array_list.toArray(user_list);
                    ArrayAdapter<String> friends_list_adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, user_list){
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
                    ListView lv = (ListView) findViewById(R.id.friends_list);
                    AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.friends_list_search);
                    actv.setAdapter(friends_list_adapter);
                    actv.setThreshold(1);
                    lv.setAdapter(friends_list_adapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ParseUser user = ParseUser.getCurrentUser();
                            ArrayList<String> friends_ar = (ArrayList<String>) user.get("friends");
                            if (friends_ar == null){
                                friends_ar = new ArrayList<String>();
                            }
                            if (friends_ar.contains(objects.get((int)id).get("name"))) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Already There!", Toast.LENGTH_SHORT);
                                toast.show();
                            } else {
//                                        Log.d(LOG_TAG,friends_ar.toString());
                                friends_ar.add((String)objects.get((int)id).get("name"));
                                user.put("friends", friends_ar);
                                user.saveInBackground();
                                Toast toast = Toast.makeText(getApplicationContext(), "Friend Added!", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    });

                } else {
                    e.printStackTrace();
                    // Something went wrong.
                }
            }
        });

    }
}
