package tr.xip.unimarnf.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import tr.xip.unimarnf.Activities.CurrentEvents;
import tr.xip.unimarnf.Models.Events;
import tr.xip.unimarnf.R;

public class CalendarFragment extends Fragment {


    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference eventualidad;
    private List<Events>  eventsList;
    LinearLayout mostrado;
    LinearLayout ocultado;
    Button bbac;

    public CalendarFragment() {
        // Required empty public constructor
    }
    CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM", Locale.getDefault());
    private TextView mjh;
    static Event v1, v2, v3;
    public static String v11, v22, v33;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        // Inflate the layout for this fragment

        mostrado = view.findViewById(R.id.calm);




        final Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, 2019);
        c.set(Calendar.MONTH,6);
        c.set(Calendar.DAY_OF_MONTH, 15);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND, 0);
        mjh = (TextView)view.findViewById(R.id.mkn);
        mjh.setText(new SimpleDateFormat("MMMM").format(c.getTime()));
        compactCalendarView = (CompactCalendarView)view.findViewById(R.id.compactcalendar_view);
        compactCalendarView.setUseThreeLetterAbbreviation(true);


        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getContext();

                final String año = new SimpleDateFormat("yyyy").format(dateClicked.getTime());


                final String mes = new SimpleDateFormat("MMMM").format(dateClicked.getTime());


                final String dia = new SimpleDateFormat("dd").format(dateClicked.getTime());

               DatabaseReference mongdb = FirebaseDatabase.getInstance().getReference("ContEvents").child(año)
                       .child(mes).child(dia);

               ValueEventListener listenData = new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                      if(dataSnapshot.exists()) {


                          Intent jjk = new Intent(getContext(), CurrentEvents.class);
                          jjk.putExtra("ano", año);
                          jjk.putExtra("mes", mes);
                          jjk.putExtra("dia", dia);
                          startActivity(jjk);
                          Toast.makeText(getContext(), "encontrado", Toast.LENGTH_SHORT).show();



                      }



                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               };

               mongdb.addListenerForSingleValueEvent(listenData);



            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

                mjh.setText(dateFormatMonth.format(firstDayOfNewMonth));
               compactCalendarView.removeAllEvents();

                eventualidad = FirebaseDatabase.getInstance().getReference("Events").child("inscrip").child(String.valueOf(c.get(Calendar.YEAR)))
                        .child(dateFormatMonth.format(firstDayOfNewMonth));

                eventsList = new ArrayList<>();
                eventsList.clear();
                ValueEventListener listen = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        int cont = 0;
                        for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {


                            Events events = artistSnapshot.getValue(Events.class);

                            eventsList.add(events);


                            cont++;


                            Calendar dd = Calendar.getInstance();
                            dd.set(Calendar.YEAR, events.getTimeA());
                            dd.set(Calendar.MONTH, events.getTimeMe());
                            dd.set(Calendar.DAY_OF_MONTH, events.getTimeD());
                            dd.set(Calendar.HOUR_OF_DAY, 0);
                            dd.set(Calendar.MINUTE, 0);
                            dd.set(Calendar.SECOND, 0);
                            dd.set(Calendar.MILLISECOND, 0);


                            if (cont == 1 ) {


                                //Toast.makeText(getContext(), String.valueOf(cont) + events.getContenido(), Toast.LENGTH_SHORT).show();
                                 v1 = new Event(getResources().getColor(R.color.verde), dd.getTimeInMillis(), events.getContenido());
                                compactCalendarView.addEvent(v1);

                            }
                            if (cont == 2 ) {


                               // Toast.makeText(getContext(), String.valueOf(cont) + events.getContenido(), Toast.LENGTH_SHORT).show();
                                 v2 = new Event(getResources().getColor(R.color.verde), dd.getTimeInMillis(), events.getContenido());
                                compactCalendarView.addEvent(v2);

                            }
                            if (cont == 3 ) {


                                 v3 = new Event(getResources().getColor(R.color.verde), dd.getTimeInMillis(), events.getContenido());
                                compactCalendarView.addEvent(v3);

                            }
                            if (cont == 4) {

                                final Event va = new Event(getResources().getColor(R.color.verde), dd.getTimeInMillis(), events.getContenido());
                                compactCalendarView.addEvent(va);

                            }
                            if (cont == 5) {


                                final Event va = new Event(getResources().getColor(R.color.verde), dd.getTimeInMillis(), events.getContenido());
                                compactCalendarView.addEvent(va);

                            }
                            if (cont == 6) {

                                final Event va = new Event(getResources().getColor(R.color.verde), dd.getTimeInMillis(), events.getContenido());
                                compactCalendarView.addEvent(va);

                            }
                            if (cont == 7) {


                                final Event va = new Event(getResources().getColor(R.color.verde), dd.getTimeInMillis(), events.getContenido());
                                compactCalendarView.addEvent(va);

                            }
                            if (cont == 8) {

                                final Event va = new Event(getResources().getColor(R.color.verde), dd.getTimeInMillis(), events.getContenido());
                                compactCalendarView.addEvent(va);

                            }




                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                };


                eventualidad.addListenerForSingleValueEvent(listen);




            }
        });



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Calendar ll = Calendar.getInstance();

        eventualidad = FirebaseDatabase.getInstance().getReference("Events").child("inscrip").child(String.valueOf(ll.get(Calendar.YEAR)))
                .child(new SimpleDateFormat("MMMM").format(ll.getTime()));

        eventsList = new ArrayList<>();
        eventsList.clear();


        ValueEventListener listen = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                int cont = 0;
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {


                    Events events = artistSnapshot.getValue(Events.class);

                    eventsList.add(events);


                    cont++;


                    Calendar dd = Calendar.getInstance();
                    dd.set(Calendar.YEAR, events.getTimeA());
                    dd.set(Calendar.MONTH, events.getTimeMe());
                    dd.set(Calendar.DAY_OF_MONTH, events.getTimeD());
                    dd.set(Calendar.HOUR_OF_DAY, 0);
                    dd.set(Calendar.MINUTE, 0);
                    dd.set(Calendar.SECOND, 0);
                    dd.set(Calendar.MILLISECOND, 0);


                    if (cont == 1) {


                        final Event va = new Event(getResources().getColor(R.color.verde), dd.getTimeInMillis(), events.getContenido());
                        compactCalendarView.addEvent(va);


                    }
                    if (cont == 2) {

                        final Event va = new Event(getResources().getColor(R.color.verde), dd.getTimeInMillis(), events.getContenido());
                        compactCalendarView.addEvent(va);

                    }
                    if (cont == 3) {


                        final Event va = new Event(getResources().getColor(R.color.verde), dd.getTimeInMillis(), events.getContenido());
                        compactCalendarView.addEvent(va);

                    }
                    if (cont == 4) {

                        final Event va = new Event(getResources().getColor(R.color.verde), dd.getTimeInMillis(), events.getContenido());
                        compactCalendarView.addEvent(va);

                    }
                    if (cont == 5) {


                        final Event va = new Event(getResources().getColor(R.color.verde), dd.getTimeInMillis(), events.getContenido());
                        compactCalendarView.addEvent(va);

                    }
                    if (cont == 6) {

                        final Event va = new Event(getResources().getColor(R.color.verde), dd.getTimeInMillis(), events.getContenido());
                        compactCalendarView.addEvent(va);

                    }
                    if (cont == 7) {


                        final Event va = new Event(getResources().getColor(R.color.verde), dd.getTimeInMillis(), events.getContenido());
                        compactCalendarView.addEvent(va);

                    }
                    if (cont == 8) {

                        final Event va = new Event(getResources().getColor(R.color.verde), dd.getTimeInMillis(), events.getContenido());
                        compactCalendarView.addEvent(va);

                    }


                }


                //ArrayAdapter adapter = new MaterhList(getActivity(), materhList);
                //listViewArtist2.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        eventualidad.addListenerForSingleValueEvent(listen);

    }
}
