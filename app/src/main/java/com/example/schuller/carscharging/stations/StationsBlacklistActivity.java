package com.example.schuller.carscharging.stations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.schuller.carscharging.adapter.StationBlacklistAdapter;
import com.example.schuller.carscharging.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class StationsBlacklistActivity extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> list;
    public static final String FIRST_COLUMN="First";
    public static final String SECOND_COLUMN="Second";


    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference mDb = database.getReference("Stations");
    final DatabaseReference mBlackDb = database.getReference("Stations/0/Blacklist");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_blacklist);

        ListView listView=findViewById(R.id.blacklist);

        list=new ArrayList<HashMap<String,String>>();

        mBlackDb.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (long i=0; i<dataSnapshot.getChildrenCount(); i++){
                    String mCarId = dataSnapshot.child(Long.toString(i)).getValue(String.class);
                    HashMap<String,String> hashmap=new HashMap<String, String>();
                    hashmap.put(FIRST_COLUMN, mCarId);
                    hashmap.put(SECOND_COLUMN, "Remove");
                    list.add(hashmap);
                }
                StationBlacklistAdapter adapter=new StationBlacklistAdapter(StationsBlacklistActivity.this, list);
                listView.setAdapter(adapter);
            }
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }

    private void populateList() {
        // TODO Auto-generated method stub


//        HashMap<String,String> hashmap=new HashMap<>();
//        hashmap.put(FIRST_COLUMN, "Allo messaging");
//        list.add(hashmap);
//
//        HashMap<String,String> hashmap2=new HashMap<String, String>();
//        hashmap2.put(FIRST_COLUMN, "Allo messaging");
//        list.add(hashmap2);
//
//        HashMap<String,String> hashmap3=new HashMap<String, String>();
//        hashmap3.put(FIRST_COLUMN, "Allo messaging");
//        list.add(hashmap3);
//
//        HashMap<String,String> hashmap4=new HashMap<String, String>();
//        hashmap4.put(FIRST_COLUMN, "Allo messaging");
//        list.add(hashmap4);
//
//        HashMap<String,String> hashmap5=new HashMap<String, String>();
//        hashmap5.put(FIRST_COLUMN, "Allo messaging");
//        list.add(hashmap5);
//
//        HashMap<String,String> hashmap6=new HashMap<String, String>();
//        hashmap6.put(FIRST_COLUMN, "Allo messaging");
//        list.add(hashmap6);
//
//        HashMap<String,String> hashmap7=new HashMap<String, String>();
//        hashmap7.put(FIRST_COLUMN, "Allo messaging");
//        list.add(hashmap7);

    }

//    String[] values = new String[] { "Android List View",
//            "Adapter implementation",
//            "Simple List View In Android",
//            "Create List View Android",
//            "Android Example",
//            "List View Source Code",
//            "List View Array Adapter",
//            "Android Example List View"
//    };
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_station_blacklist);
//        ArrayAdapter<String> itemsAdapter =
//                new ArrayAdapter<String>(this, R.layout.activity_station_blacklist_row, values);
//        ListView listView = (ListView) findViewById(R.id.blacklist);
//        listView.setAdapter(itemsAdapter);
//    }

}


