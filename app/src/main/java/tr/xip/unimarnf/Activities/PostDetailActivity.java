package tr.xip.unimarnf.Activities;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import tr.xip.unimarnf.Adapters.CommentAdapter;
import tr.xip.unimarnf.Models.Comment;
import tr.xip.unimarnf.Models.Prof;
import tr.xip.unimarnf.R;

public class PostDetailActivity extends AppCompatActivity {

    ImageView imgPost, imgUserPost, imgcurrentUser;
    TextView txtPostDesc, txtPostDateName, txtPostTitle;
    EditText editTextComent;
    Button btnAddComment;
    String postKey;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    RecyclerView RvComment;
    CommentAdapter commentAdapter;
    List<Comment> listComment;
    public static  String uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        RvComment = findViewById(R.id.rv_comment);
        imgPost = findViewById(R.id.post_detail_img);
        imgUserPost = findViewById(R.id.post_detail_user_imgpp);

        imgcurrentUser = findViewById(R.id.post_detail_current_user);

        txtPostTitle= findViewById(R.id.postDetailTitle);
        txtPostDesc = findViewById(R.id.post_Detail_desc);
        txtPostDateName = findViewById(R.id.post_Detail_Date_Name);
        editTextComent = findViewById(R.id.post_deatil_comment);
        editTextComent.setHintTextColor(Color.WHITE);
        btnAddComment = findViewById(R.id.post_detail_add_comment_btn);


        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnAddComment.setVisibility(View.INVISIBLE);




                DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(firebaseUser.getDisplayName());
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
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


                        uname = result22 +" "+result33;

                        DatabaseReference comentReference = firebaseDatabase.getReference("Comment").child(postKey).push();
                        String comment_content = editTextComent.getText().toString();
                        String uid = firebaseUser.getUid();


                        String uimg = firebaseUser.getPhotoUrl().toString();
                        Comment comment = new Comment(comment_content,uid,uimg,uname);

                        comentReference.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(PostDetailActivity.this, "Hecho", Toast.LENGTH_SHORT).show();
                                editTextComent.setText("");
                                btnAddComment.setVisibility(View.VISIBLE);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(PostDetailActivity.this, "mal mal", Toast.LENGTH_SHORT).show();

                            }
                        });



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        });




        String postImage = getIntent().getExtras().getString("postImage");
        Glide.with(this).load(postImage).into(imgPost);

        String postTitle = getIntent().getExtras().getString("title");
        txtPostTitle.setText(postTitle);

        String userPostImage = getIntent().getExtras().getString("userPhoto");
        Glide.with(this).load(userPostImage).into(imgUserPost);

        String postDescription2 = getIntent().getExtras().getString("userName");
        txtPostDesc.setText(postDescription2);

        Glide.with(this).load(firebaseUser.getPhotoUrl()).into(imgcurrentUser);

        postKey = getIntent().getExtras().getString("postKey");

        String postDescription = getIntent().getExtras().getString("descripcion");
        String date = timestampToString(getIntent().getExtras().getLong("postDate"));
        txtPostDateName.setText(postDescription+ " " + date);


        iniRvComment();


    }

    private void iniRvComment() {
        LinearLayoutManager nManager = new LinearLayoutManager(getApplicationContext());
        nManager.setReverseLayout(true);
        nManager.setStackFromEnd(true);
        RvComment.setLayoutManager(nManager);

        DatabaseReference commentRef = firebaseDatabase.getReference("Comment").child(postKey);
        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listComment = new ArrayList<>();
                for (DataSnapshot snap:dataSnapshot.getChildren()){

                    Comment comment = snap.getValue(Comment.class);
                    listComment.add(comment);


                }

                commentAdapter = new CommentAdapter(getApplicationContext(), listComment);
                RvComment.setAdapter(commentAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private String timestampToString(long time)
    {

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy", calendar).toString();
        return date;

    }


}
