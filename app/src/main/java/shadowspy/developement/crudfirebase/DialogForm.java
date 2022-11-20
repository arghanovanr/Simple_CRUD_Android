package shadowspy.developement.crudfirebase;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogForm extends DialogFragment {
    String username,vehicleType,vehicleBrand,key,options;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogForm(String username, String vehicleType, String vehicleBrand, String key, String options) {
        this.username = username;
        this.vehicleType = vehicleType;
        this.vehicleBrand = vehicleBrand;
        this.key = key;
        this.options = options;
    }

    TextView t_username , t_vehicleType , t_vehicleBrand;
    Button btn_submit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_add,container,false);
        t_username = view.findViewById(R.id.et_name);
        t_vehicleType = view.findViewById((R.id.et_vehicleType));
        t_vehicleBrand = view.findViewById((R.id.et_vehicleBrand));
        btn_submit = view.findViewById(R.id.btn_submit);

        t_username.setText(username);
        t_vehicleType.setText(vehicleType);
        t_vehicleBrand.setText(vehicleBrand);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edit_username = t_username.getText().toString();
                String edit_vehicleType = t_vehicleType.getText().toString();
                String edit_vehicleBrand = t_vehicleBrand.getText().toString();
                if(options.equals("Update")){
                    database.child("Vehicle").child(key).setValue(new ModelVehicle(edit_username,edit_vehicleType,edit_vehicleBrand)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(view.getContext(),"Data has been updated ",Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(),"Failed to update data",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
