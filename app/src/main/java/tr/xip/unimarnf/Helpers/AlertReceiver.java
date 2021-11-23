package tr.xip.unimarnf.Helpers;


import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import tr.xip.unimarnf.Activities.NavActivity;
import tr.xip.unimarnf.Models.Materh;
import tr.xip.unimarnf.Models.Prof;


public class AlertReceiver extends BroadcastReceiver {
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase firebaseDatabase;

    @Override
    public void onReceive(final Context context, Intent intent) {


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        Calendar not = Calendar.getInstance();
        int day = not.get(Calendar.DAY_OF_WEEK);
        int hor = not.get(Calendar.HOUR_OF_DAY);


        if(currentUser!=null) {

            if (day == 2) {

                Intent intent1 = new Intent(context, NavActivity.class);
                intent1.putExtra("valor1", "1");

                final PendingIntent pendingIntent = PendingIntent.getActivity(
                        context,
                        100, intent1, PendingIntent.FLAG_CANCEL_CURRENT
                );

                String dia = "Lunes";
                if (hor == 6) {
                    String hoa = "A";

                    DatabaseReference lunes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    lunes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Buenos dias!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 7) {
                    String hoa = "B";

                    DatabaseReference lunes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    lunes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 8) {
                    String hoa = "C";

                    DatabaseReference lunes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    lunes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 9) {
                    String hoa = "D";

                    DatabaseReference lunes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    lunes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 10) {
                    String hoa = "E";

                    DatabaseReference lunes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    lunes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 11) {
                    String hoa = "F";

                    DatabaseReference lunes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    lunes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 12) {
                    String hoa = "G";

                    DatabaseReference lunes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    lunes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 13) {
                    String hoa = "H";

                    DatabaseReference lunes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    lunes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Buenos dias!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 14) {
                    String hoa = "I";

                    DatabaseReference lunes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    lunes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Buenos dias!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 15) {
                    String hoa = "J";

                    DatabaseReference lunes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    lunes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 16) {
                    String hoa = "K";

                    DatabaseReference lunes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    lunes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    Prof prof = dataSnapshot.getValue(Prof.class);            String str = prof.getNombres();
                                    int index = str.indexOf(' ');
                                    index = str.indexOf(' ', index + 0);
                                    String result22 = str.substring(0, index);


                                    String str2 = prof.getApellidos();
                                    int index2 = str2.indexOf(' ');
                                    index2 = str2.indexOf(' ', index2 + 0);
                                    String result33 = str2.substring(0, index2);


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 17) {
                    String hoa = "L";

                    DatabaseReference lunes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    lunes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 18) {
                    String hoa = "M";

                    DatabaseReference lunes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    lunes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }

            if (day == 3) {
                Intent intent1 = new Intent(context, NavActivity.class);
                intent1.putExtra("valor1", "2");

                final PendingIntent pendingIntent = PendingIntent.getActivity(
                        context,
                        100, intent1, PendingIntent.FLAG_CANCEL_CURRENT
                );

                String dia = "Martes";
                if (hor == 6) {
                    String hoa = "A";

                    DatabaseReference martes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    martes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Buenos dias!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 7) {
                    String hoa = "B";

                    DatabaseReference martes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    martes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 8) {
                    String hoa = "C";

                    DatabaseReference martes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    martes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 9) {
                    String hoa = "D";

                    DatabaseReference martes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    martes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 10) {
                    String hoa = "E";

                    DatabaseReference martes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    martes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 11) {
                    String hoa = "F";

                    DatabaseReference martes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    martes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 12) {
                    String hoa = "G";

                    DatabaseReference martes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    martes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 13) {
                    String hoa = "H";

                    DatabaseReference martes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    martes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Buenas Tardes!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 14) {
                    String hoa = "I";

                    DatabaseReference martes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    martes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 15) {
                    String hoa = "J";

                    DatabaseReference martes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    martes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 16) {
                    String hoa = "K";

                    DatabaseReference martes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    martes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 17) {
                    String hoa = "L";

                    DatabaseReference martes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    martes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 18) {
                    String hoa = "M";

                    DatabaseReference martes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    martes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }

            if (day == 4) {
                Intent intent1 = new Intent(context, NavActivity.class);
                intent1.putExtra("valor1", "3");

                final PendingIntent pendingIntent = PendingIntent.getActivity(
                        context,
                        100, intent1, PendingIntent.FLAG_CANCEL_CURRENT
                );

                String dia = "Miercoles";
                if (hor == 6) {
                    String hoa = "A";

                    DatabaseReference miercoles = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    miercoles.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Buenos Dias!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 7) {
                    String hoa = "B";

                    DatabaseReference miercoles = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    miercoles.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 8) {
                    String hoa = "C";

                    DatabaseReference miercoles = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    miercoles.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 9) {
                    String hoa = "D";

                    DatabaseReference miercoles = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    miercoles.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 10) {
                    String hoa = "E";

                    DatabaseReference miercoles = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    miercoles.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 11) {
                    String hoa = "F";

                    DatabaseReference miercoles = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    miercoles.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 12) {
                    String hoa = "G";

                    DatabaseReference miercoles = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    miercoles.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 13) {
                    String hoa = "H";

                    DatabaseReference miercoles = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    miercoles.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Buenas Tardes!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 14) {
                    String hoa = "I";

                    DatabaseReference miercoles = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    miercoles.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 15) {
                    String hoa = "J";

                    DatabaseReference miercoles = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    miercoles.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 16) {
                    String hoa = "K";

                    DatabaseReference miercoles = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    miercoles.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 17) {
                    String hoa = "L";

                    DatabaseReference miercoles = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    miercoles.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 18) {
                    String hoa = "M";

                    DatabaseReference miercoles = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    miercoles.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }

            if (day == 5) {
                Intent intent1 = new Intent(context, NavActivity.class);
                intent1.putExtra("valor1", "4");

                final PendingIntent pendingIntent = PendingIntent.getActivity(
                        context,
                        100, intent1, PendingIntent.FLAG_CANCEL_CURRENT
                );

                String dia = "Jueves";
                if (hor == 6) {
                    String hoa = "A";

                    DatabaseReference jueves = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    jueves.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Buenos Dias!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                if (hor == 7) {
                    String hoa = "B";

                    DatabaseReference jueves = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    jueves.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                if (hor == 8) {
                    String hoa = "C";

                    DatabaseReference jueves = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    jueves.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                if (hor == 9) {
                    String hoa = "D";

                    DatabaseReference jueves = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    jueves.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                if (hor == 10) {
                    String hoa = "E";

                    DatabaseReference jueves = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    jueves.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                if (hor == 11) {
                    String hoa = "F";

                    DatabaseReference jueves = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    jueves.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                if (hor == 12) {
                    String hoa = "G";

                    DatabaseReference jueves = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    jueves.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Buenas Tardes!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                if (hor == 13) {
                    String hoa = "H";

                    DatabaseReference jueves = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    jueves.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                if (hor == 14) {
                    String hoa = "I";

                    DatabaseReference jueves = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    jueves.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                if (hor == 15) {
                    String hoa = "J";

                    DatabaseReference jueves = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    jueves.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                if (hor == 16) {
                    String hoa = "K";

                    DatabaseReference jueves = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    jueves.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                if (hor == 17) {
                    String hoa = "L";

                    DatabaseReference jueves = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    jueves.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                if (hor == 18) {
                    String hoa = "M";

                    DatabaseReference jueves = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    jueves.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }

            if (day == 6) {
                Intent intent1 = new Intent(context, NavActivity.class);
                intent1.putExtra("valor1", "5");

                final PendingIntent pendingIntent = PendingIntent.getActivity(
                        context,
                        100, intent1, PendingIntent.FLAG_CANCEL_CURRENT
                );

                String dia = "Viernes";
                if (hor == 6) {
                    String hoa = "A";

                    DatabaseReference viernes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    viernes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Buenos dias!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 7) {
                    String hoa = "B";

                    DatabaseReference viernes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    viernes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 8) {
                    String hoa = "C";

                    DatabaseReference viernes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    viernes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 9) {
                    String hoa = "D";

                    DatabaseReference viernes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    viernes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 10) {
                    String hoa = "E";

                    DatabaseReference viernes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    viernes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 11) {
                    String hoa = "F";

                    DatabaseReference viernes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    viernes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 12) {
                    String hoa = "G";

                    DatabaseReference viernes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    viernes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 13) {
                    String hoa = "H";

                    DatabaseReference viernes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    viernes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 14) {
                    String hoa = "I";

                    DatabaseReference viernes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    viernes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 15) {
                    String hoa = "J";

                    DatabaseReference viernes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    viernes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 16) {
                    String hoa = "K";

                    DatabaseReference viernes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    viernes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 17) {
                    String hoa = "L";

                    DatabaseReference viernes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    viernes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                if (hor == 18) {
                    String hoa = "M";

                    DatabaseReference viernes = firebaseDatabase.getReference("HorariosPersonalizados")
                            .child(currentUser.getDisplayName()).child(dia).child(hoa);

                    viernes.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Materh materh = dataSnapshot.getValue(Materh.class);
                            final String nm = materh.getMateria();

                            DatabaseReference databaseReference = firebaseDatabase.getReference("Perfiles").child(currentUser.getDisplayName());
                            databaseReference.addValueEventListener(new ValueEventListener() {
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


                                    AlertHelper notificationHelper = new AlertHelper(context);
                                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                                    nb.setContentTitle("Hola!, " + result22 + " " + result33)
                                            .setContentText(nm + " comenzará a las\n" + materh.getHorainicio() + ":" + materh.getMinutoinicio())
                                            .setVibrate(new long[]{500, 500, 500, 500})
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true)
                                            .setDefaults(NotificationCompat.DEFAULT_SOUND);
                                    notificationHelper.getManager().notify(1, nb.build());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }


        }else{


        }
    }
}