package tr.xip.unimarnf.Adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import tr.xip.unimarnf.Activities.PostDetailActivity;
import tr.xip.unimarnf.Models.Post;
import tr.xip.unimarnf.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.myViewHolder> {

        FirebaseAuth mAuth;
        FirebaseDatabase firebaseDatabase;
        Context mContext;
        List<Post> mData;

    public PostAdapter(Context mContext, List<Post> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.row_post_item, viewGroup,false);
        return  new myViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder2, int i) {

        myViewHolder2.tvTittle.setText(mData.get(i).getTittle());
        myViewHolder2.subtittle.setText(mData.get(i).getDescription());
        Glide.with(mContext).load(mData.get(i).getPicture()).into(myViewHolder2.imgPost);
        Glide.with(mContext).load(mData.get(i).getUserPhoto()).into(myViewHolder2.imgPostProfile);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView subtittle;
        TextView tvTittle;
        ImageView imgPost;
        ImageView imgPostProfile;

        public myViewHolder(@NonNull final View itemView) {
            super(itemView);
            subtittle = itemView.findViewById(R.id.row_subtitle);
            tvTittle = itemView.findViewById(R.id.row_post_title);
            imgPost = itemView.findViewById(R.id.row_post_img);
            imgPostProfile = itemView.findViewById(R.id.row_post_profile_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent postDetailAvtivity = new Intent(mContext, PostDetailActivity.class);

                    int position = getAdapterPosition();
                    postDetailAvtivity.putExtra("title", mData.get(position).getTittle());
                    postDetailAvtivity.putExtra("postImage", mData.get(position).getPicture());
                    postDetailAvtivity.putExtra("descripcion",mData.get(position).getDescription());
                    postDetailAvtivity.putExtra("postKey", mData.get(position).getPostKey());
                    postDetailAvtivity.putExtra("userPhoto", mData.get(position).getUserPhoto());
                    postDetailAvtivity.putExtra("userName", mData.get(position).getDescription2());


                    long timeStamp =  (long)  mData.get(position).getTimeStamp();
                    postDetailAvtivity.putExtra("postDate", timeStamp);

                    mContext.startActivity(postDetailAvtivity);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    mAuth = FirebaseAuth.getInstance();
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference hh = firebaseDatabase.getReference("Lectura");
                    hh.child(mAuth.getUid()).setValue(mAuth.getUid()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {


                            int position = getAdapterPosition();
                            comprobsDialog(position);


                        }
                    });


                    return false;
                }
            });

        }
        public Context getContext() {


            return itemView.getContext();
        }

        private void comprobsDialog(final int yy){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final View dialogView = inflater.inflate(R.layout.descarm, null);

            dialogBuilder.setView(dialogView);


            final AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();


            TextView textView = dialogView.findViewById(R.id.msj);

            textView.setText("¿Desea eliminar la publicación Seleccionada?");
            Button ce = dialogView.findViewById(R.id.bac);


            ce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    alertDialog.dismiss();

                }
            });

            Button de = dialogView.findViewById(R.id.desca);
            de.setText("Eliminar");
            de.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    firebaseDatabase = FirebaseDatabase.getInstance();
                    final DatabaseReference databaseReference = firebaseDatabase.getReference("Post");
                 databaseReference.child(mData.get(yy).getPostKey()).removeValue();
                    final DatabaseReference databaseReference1 = firebaseDatabase.getReference("Comment");
                    databaseReference1.child(mData.get(yy).getPostKey()).removeValue();

                    mAuth = FirebaseAuth.getInstance();
                    DatabaseReference hhm = firebaseDatabase.getReference("Lectura");
                    hhm.child(mAuth.getUid()).removeValue();

                    alertDialog.dismiss();


                }
            });






        }



    }





}
