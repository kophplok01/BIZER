package tr.xip.unimarnf.Fragments;


import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import tr.xip.unimarnf.R;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * A simple {@link Fragment} subclass.
 */
public class Comunicacion extends Fragment {

    private TextView am;
    private StorageReference mStorage;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private Button prec;
    private Button pem;
    private Button malcu;
    private Button infacer;

    public Comunicacion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carreras, container, false);
        // Inflate the layout for this fragment
        prec = (Button)view.findViewById(R.id.precios);
        pem = (Button)view.findViewById(R.id.pemsum);
        malcu = (Button)view.findViewById(R.id.mallacu);
        infacer = (Button)view.findViewById(R.id.infcar);
        am = (TextView)view.findViewById(R.id.amostrar);
        am.setText("Comunicación Social");


        botones();




        // Reference to an image file in Cloud Storage
        final ImageView photoprof =view.findViewById(R.id.preim);
        mStorage = FirebaseStorage.getInstance().getReference();
        mStorage.child("FotospreCar/comunicacion.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(Comunicacion.this).load(uri).into(photoprof);
            }
        });




        return view;
    }

    private void  botones(){

        prec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Precio", LENGTH_SHORT).show();
                preciosDialog();
            }
        });

        pem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Pemsum", LENGTH_SHORT).show();
            }
        });

        malcu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Malla curricular", LENGTH_SHORT).show();
            }
        });

        infacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Inf. carrera", LENGTH_SHORT).show();
            }
        });

    }


    private void preciosDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.preciosdialog, null);

        dialogBuilder.setView(dialogView);


        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        final ProgressBar recProgress = dialogView.findViewById(R.id.rec_progress);
        recProgress.setVisibility(View.INVISIBLE);
        final Button cerrardi = (Button) dialogView.findViewById(R.id.cerrar);


        cerrardi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "clickeado", LENGTH_SHORT).show();
                alertDialog.dismiss();


            }
        });





    }


}
