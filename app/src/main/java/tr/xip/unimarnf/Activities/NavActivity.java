package tr.xip.unimarnf.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

import tr.xip.unimarnf.Adapters.carrerasAdapter;
import tr.xip.unimarnf.Adapters.tabpagerAdapter;
import tr.xip.unimarnf.Fragments.CalendarFragment;
import tr.xip.unimarnf.Fragments.HomeFragment;
import tr.xip.unimarnf.Fragments.LimpioFragment;
import tr.xip.unimarnf.Fragments.ProfileFragment;
import tr.xip.unimarnf.Fragments.SettingsFragment;
import tr.xip.unimarnf.Helpers.AlertReceiver;
import tr.xip.unimarnf.Models.Materh;
import tr.xip.unimarnf.Models.Post;
import tr.xip.unimarnf.Models.Prof;
import tr.xip.unimarnf.R;


public class NavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int PReqCode= 2 ;
    private static final int REQUESCODE= 2 ;
    FloatingActionButton fab;
    TextView nocv, sicv;
    FirebaseAuth mAuth, bzb;
    FirebaseUser currentUser, zbz;
    Dialog popAddPost;
    Button  popupaddBtn;
    ImageView popupUserImage, popupPostImage;
    EditText popupTitle, popupDescripcion, popupDescripcion2;
    ProgressBar popupClickProgressBar;
    private Uri pickImgUrl = null;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FirebaseDatabase firebaseDatabase;
    private ViewPager carrersview;
    public static final String CHANEL_ID = "simplified_coding";
    private static final String CHANEL_NAME = "simplified_coding";
    private static final String CHANEL_DESC = "simplified coding Notification";

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        verifprocess();


        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){

            NotificationChannel chanel = new NotificationChannel(CHANEL_ID, CHANEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            chanel.setDescription(CHANEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(chanel);


        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        carrersview = (ViewPager)findViewById(R.id.viewpagermaters);
        carrerasAdapter mcarrerasAdapter = new carrerasAdapter(getSupportFragmentManager());
        carrersview.setAdapter(mcarrerasAdapter);

        tabpagerAdapter tabpagerAdapterss = new tabpagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabpagerAdapterss);
        tabLayout.setupWithViewPager(viewPager);

        iniPopup();

        setupPOPupImageClick();

         fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImgUrl = null;
                popupPostImage.setImageDrawable(null);
                nocv.setText("Agregar imagen +");
                popupPostImage.setVisibility(View.VISIBLE);
                popupDescripcion.getText().clear();
                popupDescripcion2.getText().clear();
              popupTitle.getText().clear();
               popAddPost.show();


            }
        });

        fab.setVisibility(View.GONE);


        DatabaseReference hh = firebaseDatabase.getReference("Lectura");
        hh.child(mAuth.getUid()).setValue(mAuth.getUid()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {


                fab.setVisibility(View.VISIBLE);


            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        updateNavHeader();



        openfragwhitnot();



    }

    private void openfragwhitnot() {

        String deffrag = getIntent().getStringExtra("valor1");

        String momo = getIntent().getStringExtra("valora");

        if (momo != null) {
            getSupportActionBar().setTitle("Calendario académico ");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new CalendarFragment()).commit();


        } else {
            if (deffrag == null) {

                getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
            } else {

                Toast.makeText(this, deffrag, Toast.LENGTH_SHORT).show();
                if (deffrag.equals("1")) {
                    carrersview.setVisibility(View.GONE);
                    tabLayout.setVisibility(View.VISIBLE);
                    viewPager.setVisibility(View.VISIBLE);
                    getSupportActionBar().setTitle("Horarios");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new LimpioFragment()).commit();
                    viewPager.setCurrentItem(0);
                }
                if (deffrag.equals("2")) {
                    carrersview.setVisibility(View.GONE);
                    tabLayout.setVisibility(View.VISIBLE);
                    viewPager.setVisibility(View.VISIBLE);
                    getSupportActionBar().setTitle("Horarios");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new LimpioFragment()).commit();
                    viewPager.setCurrentItem(1);
                }

                if (deffrag.equals("3")) {
                    carrersview.setVisibility(View.GONE);
                    tabLayout.setVisibility(View.VISIBLE);
                    viewPager.setVisibility(View.VISIBLE);
                    getSupportActionBar().setTitle("Horarios");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new LimpioFragment()).commit();
                    viewPager.setCurrentItem(2);
                }
                if (deffrag.equals("4")) {
                    carrersview.setVisibility(View.GONE);
                    tabLayout.setVisibility(View.VISIBLE);
                    viewPager.setVisibility(View.VISIBLE);
                    getSupportActionBar().setTitle("Horarios");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new LimpioFragment()).commit();
                    viewPager.setCurrentItem(3);
                }
                if (deffrag.equals("5")) {
                    carrersview.setVisibility(View.GONE);
                    tabLayout.setVisibility(View.VISIBLE);
                    viewPager.setVisibility(View.VISIBLE);
                    getSupportActionBar().setTitle("Horarios");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new LimpioFragment()).commit();
                    viewPager.setCurrentItem(4);
                }

            }
        }

    }

    private void defdiaview(){
        Calendar not = Calendar.getInstance();
        int day = not.get(Calendar.DAY_OF_WEEK);

        if (day == 2){

            viewPager.setCurrentItem(0);

        }
        if (day == 3){

            viewPager.setCurrentItem(1);

        }
        if (day == 4){

            viewPager.setCurrentItem(2);

        }
        if (day == 5){

            viewPager.setCurrentItem(3);

        }
        if (day == 6){

            viewPager.setCurrentItem(4);

        }



    }

    private void setupPOPupImageClick() {

        popupPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAndRequesForPermission();

            }
        });

    }

    private void checkAndRequesForPermission() {

        if (ContextCompat.checkSelfPermission(NavActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(NavActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)){

                Toast.makeText(this, "Acepta los permisos requeridos", Toast.LENGTH_SHORT).show();

            }else{

                ActivityCompat.requestPermissions(NavActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);

            }

        }else {

            opengalery();
        }


    }

    private void opengalery() {

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESCODE);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUESCODE && data != null){

            pickImgUrl = data.getData();
            popupPostImage.setImageURI(pickImgUrl);
           nocv.setText("Imagen agregada.");
            popupPostImage.setVisibility(View.INVISIBLE);

        }

    }

    private void iniPopup() {

    popAddPost = new Dialog(this);
    popAddPost.setContentView(R.layout.popup_add_post);
    popAddPost.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    popAddPost.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
    popAddPost.getWindow().getAttributes().gravity = Gravity.TOP;

    popupUserImage = popAddPost.findViewById(R.id.popup_user_image);
    popupPostImage = popAddPost.findViewById(R.id.popup_img);
    popupTitle = popAddPost.findViewById(R.id.popup_title);
    popupTitle.setHintTextColor(Color.WHITE);
    popupDescripcion = popAddPost.findViewById(R.id.popup_descripcion);
    nocv = popAddPost.findViewById(R.id.introtextpop);



    popupDescripcion2 = popAddPost.findViewById(R.id.popup_descripcion2);
    popupDescripcion2.setHintTextColor(Color.WHITE);

    popupDescripcion.setHintTextColor(Color.WHITE);
    popupaddBtn = popAddPost.findViewById(R.id.popup_add);
    popupClickProgressBar = popAddPost.findViewById(R.id.popup_progressBar2);

    Glide.with(NavActivity.this).load(currentUser.getPhotoUrl()).into(popupUserImage);

    popupaddBtn.setOnClickListener(new View.OnClickListener() {
        @SuppressLint("RestrictedApi")
        @Override
        public void onClick(View v) {

            fab.setVisibility(View.INVISIBLE);
            popupaddBtn.setVisibility(View.INVISIBLE);
            popupClickProgressBar.setVisibility(View.VISIBLE);

            if(!popupTitle.getText().toString().isEmpty() && !popupDescripcion.getText().toString().isEmpty() && pickImgUrl != null){

                StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("blog_images");
                final StorageReference imageFilePath = storageReference.child(pickImgUrl.getLastPathSegment());
                imageFilePath.putFile(pickImgUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String imagedownladLink = uri.toString();

                                Post post = new Post(popupTitle.getText().toString(),popupDescripcion.getText().toString(), popupDescripcion2.getText().toString(),
                                        imagedownladLink, currentUser.getUid(), currentUser.getPhotoUrl().toString());

                                    addPost(post);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                showMessage(e.getMessage());
                                popupClickProgressBar.setVisibility(View.INVISIBLE);
                                popupaddBtn.setVisibility(View.VISIBLE);

                            }
                        });


                    }
                });



            }else{
                popupaddBtn.setVisibility(View.VISIBLE);
                popupClickProgressBar.setVisibility(View.INVISIBLE);
                showMessage("Introudzca todos los detalles");

            }


        }
    });


    }




    private void addPost(Post post) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Post").push();

        String key = myRef.getKey();
        post.setPostKey(key);

        myRef.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onSuccess(Void aVoid) {

                showMessage("NOTICIA PUBLICADA");
                popupClickProgressBar.setVisibility(View.INVISIBLE);
                popupaddBtn.setVisibility(View.VISIBLE);
                fab.setVisibility(View.VISIBLE);
                popAddPost.dismiss();

            }
        });

    }

    private void showMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            carrersview.setVisibility(View.GONE);
            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
            getSupportActionBar().setTitle("Inicio");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

            // Handle the camera action
        }else if (id == R.id.nav_calendario){
            carrersview.setVisibility(View.GONE);
            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
            getSupportActionBar().setTitle("Calendario académico ");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new CalendarFragment()).commit();



        } else if (id == R.id.nav_profile) {
            carrersview.setVisibility(View.GONE);
            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
            getSupportActionBar().setTitle("Mi Perfil");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();

        } else if (id == R.id.nav_settings) {
            carrersview.setVisibility(View.GONE);
            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
            getSupportActionBar().setTitle("Mi Carnet");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new SettingsFragment()).commit();


        } else if (id == R.id.nav_logout) {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef22 = database.getReference("ini").child(currentUser.getUid());
            myRef22.removeValue();
            FirebaseAuth.getInstance().signOut();
            Intent loginAct = new Intent(getApplicationContext(), HomeActivity.class);
           // loginAct.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(loginAct);
            finish();

        }else if(id == R.id.nav_horarios){
            carrersview.setVisibility(View.GONE);
            tabLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("Horarios");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new LimpioFragment()).commit();
            defdiaview();

        }else if(id == R.id.nav_carreras){
            getSupportActionBar().setTitle("Carreras");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new LimpioFragment()).commit();
            carrersview.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public  void updateNavHeader(){

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        final TextView navUserName = headerView.findViewById(R.id.nav_userName);
        TextView navUserMail = headerView.findViewById(R.id.nav_user_Email);
        final ImageView navUserPhoto = headerView.findViewById(R.id.nav_userPhoto);

        navUserMail.setText(currentUser.getEmail());
        //navUserName.setText(currentUser.getDisplayName());


        DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles");
        databaseReference.child(currentUser.getDisplayName()).addListenerForSingleValueEvent(new ValueEventListener() {
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





                navUserName.setText(result22+" "+ result33);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(NavActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



        Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhoto);

        DatabaseReference mRef2323 = FirebaseDatabase.getInstance().getReference("Currentus");
        mRef2323.child(currentUser.getDisplayName()).removeValue();




    }

    private void verifprocess(){




        DatabaseReference lunes = FirebaseDatabase.getInstance().getReference("HorariosPersonalizados")
                .child(currentUser.getDisplayName()).child("Lunes");



        DatabaseReference martes = FirebaseDatabase.getInstance().getReference("HorariosPersonalizados")
                .child(currentUser.getDisplayName()).child("Martes");


        DatabaseReference miercoles = FirebaseDatabase.getInstance().getReference("HorariosPersonalizados")
                .child(currentUser.getDisplayName()).child("Miercoles");

        DatabaseReference jueves = FirebaseDatabase.getInstance().getReference("HorariosPersonalizados")
                .child(currentUser.getDisplayName()).child("Jueves");

        miercoles.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {

                    Materh materh = artistSnapshot.getValue(Materh.class);

                    if (materh.getHorainicio().equals("7")) {


                        Calendar c = Calendar.getInstance();

                        c.set(Calendar.DAY_OF_WEEK, 4);

                        c.set(Calendar.HOUR_OF_DAY, 6);

                        c.set(Calendar.MINUTE, 30);

                        c.set(Calendar.SECOND, 0);

                        int bc = 30;


                        startAlarm(c, bc);

                    }

                    if (materh.getHorainicio().equals("8")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 4);

                        b.set(Calendar.HOUR_OF_DAY, 7);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 31;


                        startAlarm(b, bc);

                    }


                    if (materh.getHorainicio().equals("9")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 4);

                        b.set(Calendar.HOUR_OF_DAY, 8);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 32;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("10")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 4);

                        b.set(Calendar.HOUR_OF_DAY, 9);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 33;


                        startAlarm(b, bc);

                    }


                    if (materh.getHorainicio().equals("11")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 4);

                        b.set(Calendar.HOUR_OF_DAY, 10);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 34;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("12")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 4);

                        b.set(Calendar.HOUR_OF_DAY, 11);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 35;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("1")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 4);

                        b.set(Calendar.HOUR_OF_DAY, 12);

                        b.set(Calendar.MINUTE, 30);


                        b.set(Calendar.SECOND, 0);

                        int bc = 36;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("2")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 4);

                        b.set(Calendar.HOUR_OF_DAY, 13);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 37;


                        startAlarm(b, bc);

                    }


                    if (materh.getHorainicio().equals("3")) {

                        Calendar z = Calendar.getInstance();

                        z.set(Calendar.DAY_OF_WEEK, 4);

                        z.set(Calendar.HOUR_OF_DAY, 14);

                        z.set(Calendar.MINUTE, 30);

                        z.set(Calendar.SECOND, 0);

                        int bc = 38;


                        startAlarm(z, bc);

                    }


                    if (materh.getHorainicio().equals("4")) {

                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 4);

                        b.set(Calendar.HOUR_OF_DAY, 15);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 39;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("5")) {

                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 4);

                        b.set(Calendar.HOUR_OF_DAY, 16);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 40;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("6")) {

                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 4);

                        b.set(Calendar.HOUR_OF_DAY, 17);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 41;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("7")) {

                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 4);

                        b.set(Calendar.HOUR_OF_DAY, 18);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 42;


                        startAlarm(b, bc);

                    }



                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        martes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {

                    Materh materh = artistSnapshot.getValue(Materh.class);

                    if (materh.getHorainicio().equals("7")) {


                        Calendar c = Calendar.getInstance();

                        c.set(Calendar.DAY_OF_WEEK, 3);

                        c.set(Calendar.HOUR_OF_DAY, 6);

                        c.set(Calendar.MINUTE, 30);

                        c.set(Calendar.SECOND, 0);

                        int bc = 15;


                        startAlarm(c, bc);


                                           }

                    if (materh.getHorainicio().equals("8")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 3);

                        b.set(Calendar.HOUR_OF_DAY, 7);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 16;


                        startAlarm(b, bc);

                    }


                    if (materh.getHorainicio().equals("9")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 3);

                        b.set(Calendar.HOUR_OF_DAY, 8);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 17;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("10")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 3);

                        b.set(Calendar.HOUR_OF_DAY, 9);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 18;


                        startAlarm(b, bc);

                    }


                    if (materh.getHorainicio().equals("11")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 3);

                        b.set(Calendar.HOUR_OF_DAY, 10);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 19;


                        startAlarm(b, bc);

                        }

                    if (materh.getHorainicio().equals("12")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 3);

                        b.set(Calendar.HOUR_OF_DAY, 11);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 20;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("1")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 3);

                        b.set(Calendar.HOUR_OF_DAY, 12);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 21;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("2")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 3);

                        b.set(Calendar.HOUR_OF_DAY, 13);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 22;


                        startAlarm(b, bc);

                    }


                    if (materh.getHorainicio().equals("3")) {

                        Calendar z = Calendar.getInstance();

                        z.set(Calendar.DAY_OF_WEEK, 3);

                        z.set(Calendar.HOUR_OF_DAY, 14);

                        z.set(Calendar.MINUTE, 30);

                        z.set(Calendar.SECOND, 0);

                        int bc = 23;


                        startAlarm(z, bc);

                    }


                    if (materh.getHorainicio().equals("4")) {

                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 3);

                        b.set(Calendar.HOUR_OF_DAY, 15);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 24;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("5")) {

                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 3);

                        b.set(Calendar.HOUR_OF_DAY, 16);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 25;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("6")) {

                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 3);

                        b.set(Calendar.HOUR_OF_DAY, 17);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 26;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("7")) {

                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 3);

                        b.set(Calendar.HOUR_OF_DAY, 18);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 27;


                        startAlarm(b, bc);

                    }



                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        lunes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {

                    Materh materh = artistSnapshot.getValue(Materh.class);

                    if (materh.getHorainicio().equals("7")) {


                        Calendar c = Calendar.getInstance();

                        c.set(Calendar.DAY_OF_WEEK, 2);

                        c.set(Calendar.HOUR_OF_DAY, 6);

                        c.set(Calendar.MINUTE, 30);

                        c.set(Calendar.SECOND, 0);

                        int bc = 1;


                        startAlarm(c, bc);

                    }

                    if (materh.getHorainicio().equals("8")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 2);

                        b.set(Calendar.HOUR_OF_DAY, 7);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 2;


                        startAlarm(b, bc);

                    }


                    if (materh.getHorainicio().equals("9")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 2);

                        b.set(Calendar.HOUR_OF_DAY, 8);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 3;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("10")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 2);

                        b.set(Calendar.HOUR_OF_DAY, 9);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 4;


                        startAlarm(b, bc);

                    }


                    if (materh.getHorainicio().equals("11")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 2);

                        b.set(Calendar.HOUR_OF_DAY, 10);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 5;


                        startAlarm(b, bc);

                                            }

                    if (materh.getHorainicio().equals("12")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 2);

                        b.set(Calendar.HOUR_OF_DAY, 11);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 6;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("1")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 2);

                        b.set(Calendar.HOUR_OF_DAY, 12);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 7;


                        startAlarm(b, bc);

                                            }

                    if (materh.getHorainicio().equals("2")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 2);

                        b.set(Calendar.HOUR_OF_DAY, 13);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 8;


                        startAlarm(b, bc);

                    }


                    if (materh.getHorainicio().equals("3")) {

                        Calendar z = Calendar.getInstance();

                        z.set(Calendar.DAY_OF_WEEK, 2);

                        z.set(Calendar.HOUR_OF_DAY, 14);

                        z.set(Calendar.MINUTE, 30);

                        z.set(Calendar.SECOND, 0);

                        int bc = 9;


                        startAlarm(z, bc);

                    }


                    if (materh.getHorainicio().equals("4")) {

                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 2);

                        b.set(Calendar.HOUR_OF_DAY, 15);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 10;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("5")) {

                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 2);

                        b.set(Calendar.HOUR_OF_DAY, 16);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 11;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("6")) {

                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 2);

                        b.set(Calendar.HOUR_OF_DAY, 17);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 12;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("7")) {

                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 2);

                        b.set(Calendar.HOUR_OF_DAY, 18);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 13;


                        startAlarm(b, bc);

                    }



                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



        jueves.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {

                    Materh materh = artistSnapshot.getValue(Materh.class);

                    if (materh.getHorainicio().equals("7")) {


                        Calendar c = Calendar.getInstance();

                        c.set(Calendar.DAY_OF_WEEK, 5);

                        c.set(Calendar.HOUR_OF_DAY, 6);

                        c.set(Calendar.MINUTE, 30);

                        c.set(Calendar.SECOND, 0);

                        int bc = 45;


                        startAlarm(c, bc);

                    }

                    if (materh.getHorainicio().equals("8")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 5);

                        b.set(Calendar.HOUR_OF_DAY, 7);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 46;


                        startAlarm(b, bc);

                    }


                    if (materh.getHorainicio().equals("9")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 5);

                        b.set(Calendar.HOUR_OF_DAY, 8);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 47;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("10")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 5);

                        b.set(Calendar.HOUR_OF_DAY, 9);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 48;


                        startAlarm(b, bc);

                    }


                    if (materh.getHorainicio().equals("11")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 5);

                        b.set(Calendar.HOUR_OF_DAY, 10);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 49;


                        startAlarm(b, bc);

                    }

                    if (materh.getHorainicio().equals("12")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 5);

                        b.set(Calendar.HOUR_OF_DAY, 11);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 50;


                        startAlarm(b, bc);
                    }

                    if (materh.getHorainicio().equals("1")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 5);

                        b.set(Calendar.HOUR_OF_DAY, 12);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 51;


                        startAlarm(b, bc);
                    }

                    if (materh.getHorainicio().equals("2")) {


                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 5);

                        b.set(Calendar.HOUR_OF_DAY, 13);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 52;


                        startAlarm(b, bc);
                    }


                    if (materh.getHorainicio().equals("3")) {

                        Calendar z = Calendar.getInstance();

                        z.set(Calendar.DAY_OF_WEEK, 5);

                        z.set(Calendar.HOUR_OF_DAY, 14);

                        z.set(Calendar.MINUTE, 30);

                        z.set(Calendar.SECOND, 0);

                        int bc = 53;


                        startAlarm(z, bc);
                    }


                    if (materh.getHorainicio().equals("4")) {

                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 5);

                        b.set(Calendar.HOUR_OF_DAY, 15);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 54;


                        startAlarm(b, bc);
                    }

                    if (materh.getHorainicio().equals("5")) {

                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 5);

                        b.set(Calendar.HOUR_OF_DAY, 16);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 55;


                        startAlarm(b, bc);
                    }

                    if (materh.getHorainicio().equals("6")) {

                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 5);

                        b.set(Calendar.HOUR_OF_DAY, 17);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);


                        int bc = 56;


                        startAlarm(b, bc);
                    }

                    if (materh.getHorainicio().equals("7")) {

                        Calendar b = Calendar.getInstance();

                        b.set(Calendar.DAY_OF_WEEK, 5);

                        b.set(Calendar.HOUR_OF_DAY, 18);

                        b.set(Calendar.MINUTE, 30);

                        b.set(Calendar.SECOND, 0);

                        int bc = 57;


                        startAlarm(b, bc);
                    }



                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


    }

    private void startAlarm(Calendar c, int bb) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, bb, intent, 0);
        long ss = c.getTimeInMillis();
        if (c.before(Calendar.getInstance())) {
            ss+= AlarmManager.INTERVAL_DAY * 7;
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, ss, pendingIntent);
        }else {

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
    }

}















