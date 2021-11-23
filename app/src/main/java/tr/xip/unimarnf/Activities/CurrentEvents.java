package tr.xip.unimarnf.Activities;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import tr.xip.unimarnf.Adapters.EventsList;
import tr.xip.unimarnf.Adapters.PostAdapter;
import tr.xip.unimarnf.Models.Post;
import tr.xip.unimarnf.R;

public class CurrentEvents extends AppCompatActivity {

    RecyclerView postRecyclerView;
    PostAdapter postAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Post> eventsList;
    TextView titulo;
    ImageView regre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_events);

        firebaseDatabase = FirebaseDatabase.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar22);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Calendario Académico ");

        regre = findViewById(R.id.golback);
        titulo = findViewById(R.id.cfe);

        regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent zpmkk = new Intent(CurrentEvents.this, NavActivity.class);
                zpmkk.putExtra("valora", "567");
                startActivity(zpmkk);
                finish();

            }
        });
        String aan = getIntent().getExtras().getString("ano");
        String mes = getIntent().getExtras().getString("mes");
        String dia = getIntent().getExtras().getString("dia");
        databaseReference = firebaseDatabase.getReference("ContEvents").child(aan).child(mes).child(dia);

        titulo.setText("Eventos a realizarse el "+ dia+ " de "+mes+" del año "+aan);
        final ListView listViewArtist2 = (ListView) findViewById(R.id.listViewArtists2);


        eventsList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                eventsList.clear();

                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {


                    Post post = artistSnapshot.getValue(Post.class);

                    eventsList.add(post);
                }

                ArrayAdapter adapter = new EventsList(CurrentEvents.this, eventsList);
                listViewArtist2.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        listViewArtist2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //showUpdateDialog();

            }
        });



    }

}
