package tr.xip.unimarnf.Fragments;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import tr.xip.unimarnf.Adapters.MaterhList;
import tr.xip.unimarnf.Models.Materh;
import tr.xip.unimarnf.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lunes extends Fragment {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private static final String FILE_NAME = "examople.txt";
    private static final String FILE_NAME2 = "examople2.txt";
    private static  String azo;
    TextView tt;
    private List<Materh> materhList;
    DatabaseReference databaseArtist;
    TextView sda;
    public Lunes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();



        final ListView listViewArtist2 = (ListView) view.findViewById(R.id.listViewArtists2);

        databaseArtist = FirebaseDatabase.getInstance().getReference("HorariosPersonalizados").child(currentUser.getDisplayName()).child("Lunes");
                materhList = new ArrayList<>();

                databaseArtist.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        materhList.clear();

                        for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {


                            Materh materh = artistSnapshot.getValue(Materh.class);

                            materhList.add(materh);
                        }

                        ArrayAdapter adapter = new MaterhList(getActivity(), materhList);
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

        return view;

    }

    private void showUpdateDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.preciosdialog, null);

        dialogBuilder.setView(dialogView);


        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();




    }


}