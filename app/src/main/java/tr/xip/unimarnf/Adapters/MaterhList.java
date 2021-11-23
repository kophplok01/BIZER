package tr.xip.unimarnf.Adapters;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import tr.xip.unimarnf.Models.Materh;
import tr.xip.unimarnf.R;


public class MaterhList extends ArrayAdapter<Materh> {

    private Activity context;
    private List<Materh> materhList;

    public MaterhList(Activity context, List<Materh> materhList) {

        super(context, R.layout.materiaslistlay, materhList);
        this.context = context;
        this.materhList = materhList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem =  inflater.inflate(R.layout.materiaslistlay, null, true);
        TextView materia = (TextView) listViewItem.findViewById(R.id.tvmateria);
        TextView hi = (TextView) listViewItem.findViewById(R.id.horadeinicio);
        TextView hf = (TextView)listViewItem.findViewById(R.id.horadefinal);
        TextView profesor = (TextView) listViewItem.findViewById(R.id.profesorm);
        TextView aula = (TextView)listViewItem.findViewById(R.id.salon);
        View largo1= listViewItem.findViewById(R.id.pol);
        Materh materh = materhList.get(position);

        if(materh.getTamano().equals("A")) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(3, 125);
            largo1.setLayoutParams(params);
        }
        if(materh.getTamano().equals("B")) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(3, 200);
            largo1.setLayoutParams(params);
        }
        if(materh.getTamano().equals("C")) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(3, 300);
            largo1.setLayoutParams(params);
        }


        materia.setText(materh.getMateria());
        hi.setText(materh.getHorainicio() + ":" + materh.getMinutoinicio()+ " " + materh.getMi());
        hf.setText(materh.getHorafinal() + ":" +materh.getMinutofinal()+ " " +materh.getMf());
        profesor.setText(materh.getPrimernomp()+ " " + materh.getPrimerap());
        aula.setText(materh.getAula());

        return listViewItem;

    }

}
