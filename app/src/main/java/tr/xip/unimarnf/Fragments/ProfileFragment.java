package tr.xip.unimarnf.Fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import tr.xip.unimarnf.Models.Prof;
import tr.xip.unimarnf.R;


public class ProfileFragment extends Fragment {


    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FirebaseDatabase firebaseDatabase;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        // Inflate the layout for this fragment

        final TextView ncomple = view.findViewById(R.id.nombrecom);
        final TextView cedcomple = view.findViewById(R.id.cedulauser);
        final TextView carcomple = view.findViewById(R.id.carrrera);
        final TextView credcomple = view.findViewById(R.id.creditos);
        final TextView tricomple = view.findViewById(R.id.triap);
        ImageView profilepp = view.findViewById(R.id.fotoprofile);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

       DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Prof prof = dataSnapshot.getValue(Prof.class);

                String str = prof.getNombres();
                int index = str.indexOf(' ');
                index = str.indexOf(' ', index + 0);
                String result22 = str.substring(0, index);


                String str2 = prof.getApellidos();
                int index2 = str2.indexOf(' ');
                index2 = str2.indexOf(' ', index2 + 0);
                String result33 = str2.substring(0, index2);

                ncomple.setText("Alumno: " +result22+ " "+ result33);
                cedcomple.setText("Numero de Cedula: "+currentUser.getDisplayName());
                carcomple.setText("Carrera: ");
                credcomple.setText("Créditos Aprobados: "  );
                tricomple.setText("Trimestres Aprobados: ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Glide.with(this).load(currentUser.getPhotoUrl()).into(profilepp);


        return view;
    }



}
