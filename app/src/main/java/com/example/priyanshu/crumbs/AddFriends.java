package com.example.priyanshu.crumbs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

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
            public void done(List<ParseUser> objects, ParseException e) {
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
                    ArrayAdapter<String> friends_list_adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, user_list);
                    ListView lv = (ListView) findViewById(R.id.friends_list);
                    AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.friends_list_search);
                    actv.setAdapter(friends_list_adapter);
                    actv.setThreshold(1);
                    lv.setAdapter(friends_list_adapter);

                } else {
                    e.printStackTrace();
                    // Something went wrong.
                }
            }
        });

    }
}
