package shadowspy.developement.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton add;
    AdapterVehicle adapterVehicle;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelVehicle> listVehicle;
    RecyclerView tv_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddActivity.class));
            }
        });

        tv_view = findViewById(R.id.tv_view);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        tv_view.setLayoutManager(mLayout);
        tv_view.setItemAnimator(new DefaultItemAnimator());

        viewData();
    }

    private void viewData() {
        database.child("Vehicle").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listVehicle = new ArrayList<>();
                for(DataSnapshot item : snapshot.getChildren()){
                    ModelVehicle vhcl = item.getValue(ModelVehicle.class);
                    vhcl.setKey(item.getKey());
                    listVehicle.add(vhcl);
                }
                adapterVehicle = new AdapterVehicle(listVehicle,MainActivity.this);
                tv_view.setAdapter(adapterVehicle);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}