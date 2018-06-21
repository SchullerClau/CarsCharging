package com.example.schuller.carscharging.stations;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.schuller.carscharging.adapter.StationBlacklistAdapter;
import com.example.schuller.carscharging.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class StationsBlacklistActivity extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> list;
    public static final String FIRST_COLUMN="First";
    public static final String SECOND_COLUMN="Second";

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference mDb = database.getReference("Stations");
    DatabaseReference mBlackDb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String intentEmail = intent.getStringExtra("stationEmailBlack");

        setContentView(R.layout.activity_station_blacklist);

        ListView listView=findViewById(R.id.blacklist);

        list=new ArrayList<HashMap<String,String>>();

        mDb.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (long i=0; i<dataSnapshot.getChildrenCount()-1; i++) {
                    if (dataSnapshot.child(Long.toString(i)).child("email").getValue(String.class).equals(intentEmail)) {
                        mBlackDb = database.getReference("Stations/" + Long.toString(i) + "/Blacklist");

                        mBlackDb.addValueEventListener(new ValueEventListener() {
                            public void onDataChange(DataSnapshot blackSnapshot) {
                                for (long j = 0; j < blackSnapshot.getChildrenCount(); j++) {
                                    String mCarId = blackSnapshot.child(Long.toString(j)).getValue(String.class);
                                    HashMap<String, String> hashmap = new HashMap<String, String>();
                                    hashmap.put(FIRST_COLUMN, mCarId);
                                    hashmap.put(SECOND_COLUMN, "Remove");
                                    list.add(hashmap);
                                }

                                StationBlacklistAdapter adapter = new StationBlacklistAdapter(StationsBlacklistActivity.this, list);
                                listView.setAdapter(adapter);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        long viewId = view.getId();
                                        if (viewId == R.id.blacklistButton){
                                            blackSnapshot.child(Long.toString(position)).getRef().removeValue();
                                            for (long u = position; u<blackSnapshot.getChildrenCount()-1; u++){
                                                blackSnapshot.child(Long.toString(u)).getRef().setValue(blackSnapshot.child(Long.toString(u+1)).getValue(String.class));
                                                blackSnapshot.child(Long.toString(u+1)).getRef().removeValue();
//                                                HashMap<String, Object> result = new HashMap<>();
//
//                                                result.put(Long.toString(u-1), blackSnapshot.child(Long.toString(u)).getValue(String.class));
//
//                                                blackSnapshot.child(Long.toString(u)).getRef().updateChildren(result);

                                            }
                                        }

                                    }
                                });
                            }
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });

                    }
                }
            }
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }
}


