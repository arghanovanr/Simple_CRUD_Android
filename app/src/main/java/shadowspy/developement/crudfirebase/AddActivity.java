package shadowspy.developement.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {
    EditText et_name,et_vehicleType,et_vehicleBrand;
    Button btn_submit;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_name = findViewById(R.id.et_name);
        et_vehicleType = findViewById(R.id.et_vehicleType);
        et_vehicleBrand = findViewById(R.id.et_vehicleBrand);
        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getName = et_name.getText().toString();
                String getVehicleType = et_vehicleType.getText().toString();
                String getVehicleBrand = et_vehicleBrand.getText().toString();

                if(getName.isEmpty()){
                    et_name.setError("Please input name");
                }else if(getVehicleType.isEmpty()){
                    et_vehicleType.setError("Please input vehicle type");
                }else if(getVehicleBrand.isEmpty()){
                    et_vehicleBrand.setError("Please input vehicle brand");
                }else{
                    database.child("Vehicle").push().setValue(new ModelVehicle(getName,getVehicleType,getVehicleBrand)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(AddActivity.this,"Data has been successfully added to the database",Toast.LENGTH_LONG);
                            startActivity(new Intent(AddActivity.this,MainActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddActivity.this,"Data failed to add in database",Toast.LENGTH_LONG);
                        }
                    });
                }
            }
        });

    }
}