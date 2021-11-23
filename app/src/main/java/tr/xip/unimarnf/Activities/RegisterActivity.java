package tr.xip.unimarnf.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import tr.xip.unimarnf.BuildConfig;
import tr.xip.unimarnf.Models.Prof;
import tr.xip.unimarnf.Models.Reg;
import tr.xip.unimarnf.Models.Tokentodata;
import tr.xip.unimarnf.Models.UserProfile;
import tr.xip.unimarnf.R;
import tr.xip.unimarnf.animations.ProgressBarAnimatio;


public class RegisterActivity extends AppCompatActivity {

    ImageView userPhoto;
    static int PReqCode= 1 ;
    static int REQUESCODE= 1 ;
    Uri pickImgUrl;
    private EditText userEmail, userPassword, userPassword2, userName;
    private ProgressBar loadingProgress;
    private Button regBtn;
    private FirebaseAuth mAuth;
    private ProgressBar loadpb;
    ImageView submint;
    FirebaseUser currentUser;
    private LinearLayout mnmmn;
    private LinearLayout asdf;
    private TextView tg;
    ProgressBar progressBar21;
    TextView textView21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        progressBar21 = findViewById(R.id.progress_bar63);
        textView21 = findViewById(R.id.text_view63);

        progressBar21.setMax(100);
        progressBar21.setScaleY(3f);
        progressAnimation();

        tg = (TextView)findViewById(R.id.tguia);
        mnmmn = (LinearLayout)findViewById(R.id.mplan);
        userEmail = findViewById(R.id.regMail);
        userPassword = findViewById(R.id.regPassword);
        userPassword2 = findViewById(R.id.regPassword2);
        userName = findViewById(R.id.regName);
        loadingProgress = findViewById(R.id.progressBar);
        userPhoto = findViewById(R.id.regUserPhoto);
        regBtn = findViewById(R.id.regBtn);
        loadpb = (ProgressBar)findViewById(R.id.userpbar);
        loadpb.setVisibility(View.INVISIBLE);
        submint = (ImageView) findViewById(R.id.cechkedp);
        submint.setVisibility(View.INVISIBLE);
        asdf = (LinearLayout)findViewById(R.id.acpm73);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        final String phonenumber = getIntent().getStringExtra("verif");
        final String ide = getIntent().getStringExtra("zzz");


        polka();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regBtn.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPassword2.getText().toString();
                final String name = userName.getText().toString();


                if (email.isEmpty()|| name.isEmpty()|| password.isEmpty()||password2.isEmpty() ){

                    showMessage("Por favor introduzca todos los detalles");
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);



                }else{
                    if(password.equals(password2)){
                        if(pickImgUrl != null){

                            mnmmn.setVisibility(View.GONE);
                            asdf.setVisibility(View.VISIBLE);
                            tg.setText("Verificando Numero de Cedula");
                            DatabaseReference volvo = FirebaseDatabase.getInstance().getReference("Currentus");

                            UserProfile userProfile= new UserProfile("a","a",name,"a","a","a","a");
                            volvo.child(name).setValue(userProfile).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    tg.setText("Verificando que el número de teléfono no este asociado a otra cuenta");
                                    DatabaseReference mReft = FirebaseDatabase.getInstance().getReference("Ntu");


                                    mReft.child(ide).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.exists()){
                                                final String searchk = userName.getText().toString();
                                                if(dataSnapshot.child(searchk).exists()){

                                                   // Toast.makeText(RegisterActivity.this, "Procede", Toast.LENGTH_SHORT).show();

                                                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Currentus");
                                                    mRef.child(userName.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                            if (dataSnapshot.exists()){

                                                               tg.setText("Creando cuenta personal");
                                                                //sendEmailVerification();
                                                                CreateUserAccount(email,name,password,phonenumber);

                                                                //asigntoken(searchk);




                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                            mnmmn.setVisibility(View.VISIBLE);
                                                            asdf.setVisibility(View.GONE);
                                                            regBtn.setVisibility(View.VISIBLE);
                                                            loadingProgress.setVisibility(View.INVISIBLE);
                                                            Toast.makeText(RegisterActivity.this, "Currentus te ha denegado el Acceso", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });



                                                }else{
                                                    mnmmn.setVisibility(View.VISIBLE);
                                                    asdf.setVisibility(View.GONE);
                                                    regBtn.setVisibility(View.VISIBLE);
                                                    loadingProgress.setVisibility(View.INVISIBLE);
                                                    Toast.makeText(RegisterActivity.this, "El número de teléfono con el que estas intentando registrarte no coincide con el número de cedula asignado", Toast.LENGTH_LONG).show();

                                                }





                                            }else{


                                                DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Currentus");
                                                mRef.child(userName.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        if (dataSnapshot.exists()){



                                                            tg.setText("Creando cuenta personal");
                                                            //sendEmailVerification();
                                                            CreateUserAccount(email,name,password,phonenumber);

                                                            //asigntoken(searchk);




                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                                        mnmmn.setVisibility(View.VISIBLE);
                                                        asdf.setVisibility(View.GONE);
                                                        regBtn.setVisibility(View.VISIBLE);
                                                        loadingProgress.setVisibility(View.INVISIBLE);
                                                        Toast.makeText(RegisterActivity.this, "Currentus te ha denegado el acceso en el paso 2", Toast.LENGTH_SHORT).show();
                                                    }
                                                });




                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            mnmmn.setVisibility(View.VISIBLE);
                                            asdf.setVisibility(View.GONE);
                                            regBtn.setVisibility(View.VISIBLE);
                                            loadingProgress.setVisibility(View.INVISIBLE);
                                            Toast.makeText(RegisterActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            mnmmn.setVisibility(View.VISIBLE);
                                            asdf.setVisibility(View.GONE);
                                            Toast.makeText(RegisterActivity.this, "El número de cedula que has introducido no corresponde al de ningún estudiante",
                                                    Toast.LENGTH_LONG).show();
                                            regBtn.setVisibility(View.VISIBLE);
                                            loadingProgress.setVisibility(View.INVISIBLE);
                                        }
                                    });






                        }else{

                            regBtn.setVisibility(View.VISIBLE);
                            loadingProgress.setVisibility(View.INVISIBLE);
                           Toast toast = Toast.makeText(getApplicationContext(), "Debes elegir una foto de Identificación", Toast.LENGTH_SHORT);
                           toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, 350);
                           toast.show();

                        }




                    }else {



                        regBtn.setVisibility(View.VISIBLE);
                        loadingProgress.setVisibility(View.INVISIBLE);
                        Toast.makeText(RegisterActivity.this, "Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show();
                    }
                }




            }
        });
        loadingProgress.setVisibility(View.INVISIBLE);

        userPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showUpdateDialog();


            }
        });



    }


    private void progressAnimation (){

        ProgressBarAnimatio anim = new ProgressBarAnimatio(this, progressBar21, textView21, 0, 100f);
        anim.setDuration(200000);
        progressBar21.setAnimation(anim);



    }

    private void CreateUserAccount(final String email, final String name, final String password, final String vv) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Perfiles").child(name);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Prof prof = dataSnapshot.getValue(Prof.class);

                String str = prof.getNombres();
                int index = str.indexOf(' ');
                index = str.indexOf(' ', index + 0);
                final String result22 = str.substring(0, index);


                String str2 = prof.getApellidos();
                int index2 = str2.indexOf(' ');
                index2 = str2.indexOf(' ', index2 + 0);
                final String result33 = str2.substring(0, index2);

                FirebaseAuth.getInstance().signOut();

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            showMessage("Cuenta Creada");
                            updateUserInfo( name, pickImgUrl,mAuth.getCurrentUser(), vv, result22,result33);
                            asigntoken(name);



                        }else {userEmail.setText("");
                            regBtn.setVisibility(View.VISIBLE);
                            loadingProgress.setVisibility(View.INVISIBLE);
                            mnmmn.setVisibility(View.VISIBLE);
                            asdf.setVisibility(View.GONE);
                            showMessage(task.getException().getMessage());
                        }

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void updateUserInfo(final String name, Uri pickImgUrl, final FirebaseUser currentUser, final String bbb, final String r1, final String r2){
tg.setText("Subiendo foto de identificación...\n (Esta operación podría tardar unos minutos dependiendo de la velocidad de tu conexión a internet");
        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("Users_photos");
        final StorageReference imageFilePath = mStorage.child(mAuth.getUid());
        imageFilePath.putFile(pickImgUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();

                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){
                                            tg.setText("Foto de identificacion subida exitosamente!");
                                            sendUserData(bbb,r1,r2);

                                        }else { mnmmn.setVisibility(View.VISIBLE);
                                            asdf.setVisibility(View.GONE);}

                                    }
                                });

                    }
                });

            }
        });



    }


    private void updateUI(String gg){
        regBtn.setVisibility(View.VISIBLE);
        loadingProgress.setVisibility(View.INVISIBLE);
        showMessage("Registro Completo");
        getcount(gg);
       // Toast.makeText(this, gg, Toast.LENGTH_SHORT).show();
        Intent homeActivity = new Intent(getApplicationContext(), NavActivity.class);
        startActivity(homeActivity);
        finish();

    }

    private void getcount (String bgh){
        FirebaseUser njh = mAuth.getCurrentUser();
        DatabaseReference datas = FirebaseDatabase.getInstance().getReference("ini").child(njh.getUid());
        Reg reg = new Reg(njh.getUid(), njh.getDisplayName(),njh.getEmail(),bgh);

        datas.setValue(reg);


    }

    private void sendUserData(final String numer, final String result223, final String result332){
        final FirebaseUser asd= mAuth.getCurrentUser();
        final String name1 = asd.getDisplayName();
        final String email1 = asd.getEmail();
        final String mnmb = asd.getPhotoUrl().toString();
        final String password = asd.getUid();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference("UsuariosRegistrados").child(mAuth.getUid());


                UserProfile userProfile = new UserProfile(password, email1,name1,mnmb,numer, result223, result332);
                myRef.setValue(userProfile);
                final String ide = getIntent().getStringExtra("zzz");
                DatabaseReference myReft = firebaseDatabase.getReference("Ntu").child(ide).child(asd.getDisplayName());
                myReft.child(asd.getDisplayName()).setValue(numer);

        updateUI(numer);




    }

    private void showMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }


    private void opengalery() {


        Intent galleryIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(), "picture.jpg");
        pickImgUrl = FileProvider.getUriForFile(RegisterActivity.this,
                BuildConfig.APPLICATION_ID + ".provider", photo);
        galleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, pickImgUrl);
       startActivityForResult(galleryIntent, REQUESCODE);




    }

    private void checkAndRequesForPermission() {

        if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.CAMERA )
            != PackageManager.PERMISSION_GRANTED ){
                 if (ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this, Manifest.permission.CAMERA)){

                    Toast.makeText(this, "Porfavor Acepta los Permisos requeridos", Toast.LENGTH_SHORT).show();

                }else{

                    ActivityCompat.requestPermissions(RegisterActivity.this,
                            new String[]{Manifest.permission.CAMERA}, PReqCode);

                }

            }else {

            opengalery();
        }


    }

    private void polka() {

        if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE )
                != PackageManager.PERMISSION_GRANTED ){
            if (ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){

                Toast.makeText(this, "Porfavor Acepta los Permisos requeridos", Toast.LENGTH_SHORT).show();

            }else{

                ActivityCompat.requestPermissions(RegisterActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PReqCode);

            }

        }else {
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {
            userPhoto.setVisibility(View.VISIBLE);
            loadpb.setVisibility(View.INVISIBLE);
            userPhoto.setImageResource(R.drawable.subm);
            userPhoto.setVisibility(View.VISIBLE);
            loadpb.setVisibility(View.INVISIBLE);
        }
        if(pickImgUrl !=null){
            userPhoto.setImageResource(R.drawable.subm);
            userPhoto.setVisibility(View.VISIBLE);
            loadpb.setVisibility(View.INVISIBLE);

        }


    }



    public void asigntoken(final String abus){

        DatabaseReference ero = FirebaseDatabase.getInstance().getReference("asignaciontokens").child("1er Año");

        ero.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(abus).exists()){

                    Toast.makeText(RegisterActivity.this, abus + "ajaja", Toast.LENGTH_SHORT).show();

                    DatabaseReference mRef1 = FirebaseDatabase.getInstance().getReference("asignaciontokens").child("1er Año").child(abus);
                    mRef1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String scz=  dataSnapshot.child("Seccion").getValue().toString();
                            Toast.makeText(RegisterActivity.this, scz, Toast.LENGTH_SHORT).show();

                            if(scz.equals("Seccion A")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"1er","Seccion A" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion B")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"1er","Seccion B" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion C")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"1er","Seccion C" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion D")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"1er","Seccion D" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion E")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"1er","Seccion E" );

                                                }else{




                                                }
                                            }
                                        });


                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }else{


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

               // Toast.makeText(RegisterActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        DatabaseReference undo = FirebaseDatabase.getInstance().getReference("asignaciontokens").child("2do Año");

        undo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(abus).exists()){

                    Toast.makeText(RegisterActivity.this, abus + "ajaja", Toast.LENGTH_SHORT).show();

                    DatabaseReference mRef1 = FirebaseDatabase.getInstance().getReference("asignaciontokens").child("2do Año").child(abus);
                    mRef1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String scz=  dataSnapshot.child("Seccion").getValue().toString();
                            Toast.makeText(RegisterActivity.this, scz, Toast.LENGTH_SHORT).show();

                            if(scz.equals("Seccion A")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"2do","Seccion A" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion B")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"2do","Seccion B" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion C")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"2do","Seccion C" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion D")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"2do","Seccion D" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion E")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"2do","Seccion E" );

                                                }else{




                                                }
                                            }
                                        });


                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }else{


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                //Toast.makeText(RegisterActivity.this, "Error en la base de datos", Toast.LENGTH_SHORT).show();
            }
        });

        DatabaseReference cero = FirebaseDatabase.getInstance().getReference("asignaciontokens").child("3er Año");

        cero.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(abus).exists()){

                    DatabaseReference mRef3 = FirebaseDatabase.getInstance().getReference("asignaciontokens").child("3er Año").child(abus);
                    mRef3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String scz=  dataSnapshot.child("Seccion").getValue().toString();
                            Toast.makeText(RegisterActivity.this, scz, Toast.LENGTH_SHORT).show();


                            if(scz.equals("Seccion A")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"3ro","Seccion A" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion B")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"3ro","Seccion B" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion C")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"3ro","Seccion C" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion D")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();

                                                    Toast.makeText(RegisterActivity.this, token, Toast.LENGTH_SHORT).show();
                                                    saveToken(token,"3ro","Seccion D" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion E")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"3ro","Seccion E" );

                                                }else{




                                                }
                                            }
                                        });


                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }else{


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

              //  Toast.makeText(RegisterActivity.this, "Error en la base de datos", Toast.LENGTH_SHORT).show();
            }
        });

        DatabaseReference uarto = FirebaseDatabase.getInstance().getReference("asignaciontokens").child("4to Año");

        uarto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(abus).exists()){

                    DatabaseReference mRef1 = FirebaseDatabase.getInstance().getReference("asignaciontokens").child("4to Año").child(abus);
                    mRef1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String scz=  dataSnapshot.child("Seccion").getValue().toString();
                            Toast.makeText(RegisterActivity.this, scz, Toast.LENGTH_SHORT).show();

                            if(scz.equals("Seccion A")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"4to","Seccion A" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion B")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"4to","Seccion B" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion C")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"4to","Seccion C" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion D")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"4to","Seccion D" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion E")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"4to","Seccion E" );

                                                }else{




                                                }
                                            }
                                        });


                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }else{


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                //Toast.makeText(RegisterActivity.this, "Error en la base de datos", Toast.LENGTH_SHORT).show();
            }
        });

        DatabaseReference uinto = FirebaseDatabase.getInstance().getReference("asignaciontokens").child("5to Año");

        uinto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(abus).exists()){

                    DatabaseReference mRef1 = FirebaseDatabase.getInstance().getReference("asignaciontokens").child("5to Año").child(abus);
                    mRef1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String scz=  dataSnapshot.child("Seccion").getValue().toString();
                            Toast.makeText(RegisterActivity.this, scz, Toast.LENGTH_SHORT).show();

                            if(scz.equals("Seccion A")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"5to","Seccion A" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion B")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"5to","Seccion B" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion C")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"5to","Seccion C" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion D")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"5to","Seccion D" );

                                                }else{




                                                }
                                            }
                                        });


                            }
                            if(scz.equals("Seccion E")){

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()){

                                                    String token = task.getResult().getToken();


                                                    saveToken(token,"5to","Seccion E" );

                                                }else{




                                                }
                                            }
                                        });


                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }else{


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

               // Toast.makeText(RegisterActivity.this, "Error en la base de datos", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void saveToken(String token, String ano, String secc) {

        String email = mAuth.getCurrentUser().getEmail();
        Tokentodata tokentodata= new Tokentodata(email, token);

        Toast.makeText(this, email + token, Toast.LENGTH_SHORT).show();
        DatabaseReference dbUsers = FirebaseDatabase.getInstance().getReference("Notificaciones").child("Notificacionesgenerales");
        dbUsers.child(mAuth.getCurrentUser().getUid()).setValue(tokentodata).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Token guardada", Toast.LENGTH_LONG).show();

                }else {


                }
            }
        });

        DatabaseReference dbnotconc = FirebaseDatabase.getInstance().getReference("Notificaciones").child("Notificacionesconano").child(ano);
        dbnotconc.child(mAuth.getCurrentUser().getUid()).setValue(tokentodata).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Token guardada", Toast.LENGTH_LONG).show();

                }else {


                }
            }
        });

        DatabaseReference dbnotconsecc = FirebaseDatabase.getInstance().getReference("Notificaciones").child("Notificacionesconañoyseccion").child(ano).child(secc);
        dbnotconsecc.child(mAuth.getCurrentUser().getUid()).setValue(tokentodata).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Token guardada", Toast.LENGTH_LONG).show();

                }else {


                }
            }
        });

    }



    private void showUpdateDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.introphoto_dialog, null);

        dialogBuilder.setView(dialogView);


        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        final Button reset = (Button) dialogView.findViewById(R.id.etcer);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT>= 22){

                    checkAndRequesForPermission();
                    alertDialog.dismiss();


                }else{
                    alertDialog.dismiss();
                    opengalery();


                }


            }
        });

    }

}
