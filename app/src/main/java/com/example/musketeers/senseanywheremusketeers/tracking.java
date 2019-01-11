package com.example.musketeers.senseanywheremusketeers;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.musketeers.senseanywheremusketeers.Models.TempLocation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class tracking extends AppCompatActivity {

    ListView listView;
    List<TempLocation> locations = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Timer timer = new Timer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        listView = findViewById(R.id.listViewTracking);
        load();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                load();
            }
        };
        timer.schedule(task,0,60*5000);
    }



    private void load(){

        CollectionReference documentReference = db.collection("temperatureloc1");
        documentReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        TempLocation tempLocation = new TempLocation();
                        tempLocation.setDate((String) document.get("date"));
                        tempLocation.setTemperature((String) document.get("temperature"));
                        tempLocation.setAddress((String) document.get("address"));
                        if(tempLocation.getAddress() == null){
                            tempLocation.setAddress("empty");
                        }
                        locations.add(tempLocation);

                    }
                    fillListView();
            }
            }
        });

    }

    private void fillListView(){
        listView.setAdapter(null);
        adapter adapter = new adapter();
        listView.setAdapter(adapter);
    }

    public class adapter extends BaseAdapter {
        @Override
        public int getCount(){
            return locations.size();
        }
        @Override
        public Object getItem(int i){
            return locations.get(i);
        }
        @Override
        public long getItemId(int i){
            return 0;
        }
        @Override
        public View getView(int i , View view, ViewGroup viewGroup){
            view = getLayoutInflater().inflate(R.layout.tracking_adapter,null);
            TextView date = view.findViewById(R.id.texttViewDate);
            TextView location = view.findViewById(R.id.textViewLocation);
            TextView temperature = view.findViewById(R.id.textViewTemp);
            date.setText(locations.get(i).getDate());
            location.setText(locations.get(i).getAddress());
            temperature.setText(locations.get(i).getTempCalc() + "°C");

            return view;
        }
    }

}
