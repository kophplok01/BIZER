package tr.xip.unimarnf.Adapters;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import tr.xip.unimarnf.Models.Post;
import tr.xip.unimarnf.R;


public class EventsList extends ArrayAdapter<Post> {

    private Activity context;
    private List<Post> eventsList;

    public EventsList(Activity context, List<Post> eventsList) {

        super(context, R.layout.row_post_item22, eventsList);
        this.context = context;
        this.eventsList = eventsList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem =  inflater.inflate(R.layout.row_post_item22, null, true);

        TextView subtittle = listViewItem.findViewById(R.id.row_subtitle);
        TextView tvTittle = listViewItem.findViewById(R.id.row_post_title);
        ImageView imgPost = listViewItem.findViewById(R.id.row_post_img);
        ImageView imgPostProfile = listViewItem.findViewById(R.id.row_post_profile_img);


        Post post = eventsList.get(position);

        tvTittle.setText(post.getTittle());
        subtittle.setText(post.getDescription());
        Glide.with(getContext()).load(post.getPicture()).into(imgPost);
        Glide.with(getContext()).load(post.getUserPhoto()).into(imgPostProfile);

        return listViewItem;

    }

}
