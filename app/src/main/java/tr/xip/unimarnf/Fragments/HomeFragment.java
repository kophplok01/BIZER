package tr.xip.unimarnf.Fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import tr.xip.unimarnf.Adapters.PostAdapter;
import tr.xip.unimarnf.Models.Post;
import tr.xip.unimarnf.R;

public class HomeFragment extends Fragment {


    RecyclerView postRecyclerView;
    PostAdapter postAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Post> postList;

    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment

        LinearLayoutManager nManager = new LinearLayoutManager(getContext());
        nManager.setReverseLayout(true);
        nManager.setStackFromEnd(true);

        View framentView= inflater.inflate(R.layout.fragment_home,container, false);
        postRecyclerView = framentView.findViewById(R.id.postRv);
        postRecyclerView.setHasFixedSize(true);
        postRecyclerView.setLayoutManager(nManager);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Post");



        return framentView;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReference.orderByChild("timeStamp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                postList = new ArrayList<>();
                for(DataSnapshot postSnap : dataSnapshot.getChildren()){

                    Post post = postSnap.getValue(Post.class);
                    postList.add(post);

                }

                postAdapter = new PostAdapter(getActivity(),postList);
                postRecyclerView.setAdapter(postAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
