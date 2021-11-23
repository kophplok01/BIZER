package tr.xip.unimarnf.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tr.xip.unimarnf.Models.Reg;
import tr.xip.unimarnf.Models.UserProfile;
import tr.xip.unimarnf.R;
import tr.xip.unimarnf.zipy;

public class
HomeActivity extends AppCompatActivity {



    private EditText userMail, userPassword;
    private Button btnLogin;
    private ProgressBar loginProgress1;
    FirebaseAuth mAuth;
    private Intent Mainactivity;
    private Button toReg;
    private Button recpass;
    private Button butonprof;
    private Button butonvisi;
    private ProgressBar progressprof;
    private ProgressBar progressVisi;
    public static final String ARTIST_NAME = "artistname";
    public static final String ARTIST_ID = "artistid";

    FirebaseUser currentUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        butonprof = (Button)findViewById(R.id.comoprof);
        butonvisi = (Button)findViewById(R.id.comoinvitado);
        progressprof = (ProgressBar)findViewById(R.id.prof_progress);
        progressVisi = (ProgressBar)findViewById(R.id.invi_progress);
        progressVisi.setVisibility(View.INVISIBLE);
        progressprof.setVisibility(View.INVISIBLE);


        userMail = findViewById(R.id.login_mail);
        userPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.btnlogin);
        loginProgress1 = findViewById(R.id.login_progress);
        Mainactivity = new Intent(this, NavActivity.class);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        toReg = findViewById(R.id.tvRegister);
        recpass = (Button) findViewById(R.id.tvReccont);
        userMail.setHintTextColor(Color.WHITE);
        userPassword.setHintTextColor(Color.WHITE);
        loginProgress1.setVisibility(View.INVISIBLE);




        toReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, zipy.class));
            }});


        recpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateDialog();
                //        startActivity(new Intent(MainActivity.this, PasswordActivity.class));
            }
        });





        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mail = userMail.getText().toString();
                final String password = userPassword.getText().toString();

                if(mail.isEmpty() && password.isEmpty()){

                    userMail.setError("Introduzca su E-mail");
                    userPassword.setError("Introduzca su Contraseña");
                    //showMessage("Introduzca todos los detalles");


                }else{
                    if(mail.isEmpty()) {

                        userMail.setError("Introduzca su E-mail");

                    }else{

                        if(password.isEmpty()){

                            userPassword.setError("Introduzca su Contraseña");

                        }else{

                            loginProgress1.setVisibility(View.VISIBLE);
                            btnLogin.setVisibility(View.INVISIBLE);
                            signIn(mail, password);

                        }
                    }

                }

            }
        });


    }


    private void showUpdateDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.recpass_dialog, null);

        dialogBuilder.setView(dialogView);

        final EditText editrecov = (EditText) dialogView.findViewById(R.id.recover_mail);
        editrecov.setHintTextColor(Color.WHITE);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        final ProgressBar recProgress = dialogView.findViewById(R.id.rec_progress);
        recProgress.setVisibility(View.INVISIBLE);
        final Button resetPassword = (Button) dialogView.findViewById(R.id.etRecpass);


        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = editrecov.getText().toString().trim();

                if (useremail.equals("")){
                    Toast.makeText(HomeActivity.this, "Introduzca su correo electronico", Toast.LENGTH_SHORT).show();

                }else{
                    resetPassword.setVisibility(View.INVISIBLE);
                    recProgress.setVisibility(View.VISIBLE);
                    mAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(HomeActivity.this, "Correo de recuperacion enviado", Toast.LENGTH_SHORT).show();

                                alertDialog.dismiss();


                            }else{
                                Toast.makeText(HomeActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                resetPassword.setVisibility(View.VISIBLE);
                                recProgress.setVisibility(View.INVISIBLE);
                            }

                        }
                    });
                }
            }
        });





    }



    private void signIn(String v1, String v2){

        mAuth.signInWithEmailAndPassword(v1,v2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    loginProgress1.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.VISIBLE);
                    updateUI();

                }else {

                    showMessage(task.getException().getMessage());
                    loginProgress1.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void updateUI(){

        final FirebaseUser user = mAuth.getCurrentUser();

        DatabaseReference datas = FirebaseDatabase.getInstance().getReference("UsuariosRegistrados").child(mAuth.getUid());

        datas.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                Reg reg = new Reg(user.getUid(), user.getDisplayName(),user.getEmail(),userProfile.getUserPhone());

                DatabaseReference datas = FirebaseDatabase.getInstance().getReference("ini").child(mAuth.getUid());
                datas.setValue(reg);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HomeActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        startActivity(Mainactivity);
        finish();

    }




    private void showMessage(String texts){

        Toast.makeText(getApplicationContext(), texts, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();



        if (user !=null){
            getcount();
            updateUI();

        }



    }



    private void getcount (){

       DatabaseReference databaseArtist = FirebaseDatabase.getInstance().getReference("Perfiles").child(currentUser.getDisplayName());


        databaseArtist.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(dataSnapshot.exists()) {

                        UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                        FirebaseUser njh = mAuth.getCurrentUser();

                        DatabaseReference datas = FirebaseDatabase.getInstance().getReference("ini").child(njh.getDisplayName());
                        Reg reg = new Reg(njh.getUid(), njh.getDisplayName(),njh.getEmail(),userProfile.getUserPhone());

                        datas.setValue(reg);


                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });










    }

}

