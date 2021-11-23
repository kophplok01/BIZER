package tr.xip.unimarnf.Fragments;


import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import tr.xip.unimarnf.R;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static android.widget.Toast.LENGTH_SHORT;

public class Diseño extends Fragment {

    private TextView am;
    FirebaseStorage firebaseStorage;
    StorageReference mStorage, moldo, moldo2;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private Button prec;
    private Button pem;
    private Button malcu;
    private Button infacer;

    public Diseño() {
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
        am.setText("Diseño Gráfico");


        botones();




        // Reference to an image file in Cloud Storage
        final ImageView photoprof =view.findViewById(R.id.preim);
        mStorage = FirebaseStorage.getInstance().getReference();
        mStorage.child("FotospreCar/diseño.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(Diseño.this).load(uri).into(photoprof);
            }
        });



        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

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
                comprobsDialog();

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


    public void download(){

        moldo = firebaseStorage.getInstance().getReference();
        moldo2 = moldo.child("Mallas/mDiseño.pdf");

        moldo2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(getContext(), "MallaCdiseño", ".pdf", DIRECTORY_DOWNLOADS, url);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

    public  void  downloadFiles(Context context, String fileName, String fileExtension, String destinationDirectory, String url){

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        Uri uri = Uri.parse(url);

        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(destinationDirectory, fileName+fileExtension);

        downloadManager.enqueue(request);


    }

    private void comprobsDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.descarm, null);

        dialogBuilder.setView(dialogView);


        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        TextView textView = dialogView.findViewById(R.id.msj);

        textView.setText("Desas descargar la malla curricular para la carrera de Diseño? ");
        Button ce = dialogView.findViewById(R.id.bac);

        ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();

            }
        });

        Button de = dialogView.findViewById(R.id.desca);
        de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                download();
                alertDialog.dismiss();


            }
        });






    }





}
