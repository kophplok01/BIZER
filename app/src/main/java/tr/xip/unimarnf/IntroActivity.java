package tr.xip.unimarnf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


import tr.xip.unimarnf.Activities.HomeActivity;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position= 0 ;
    Button btnGetStarted;
    Animation btnAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        if(restorePrefData()){

            Intent mainActivity = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(mainActivity);
            finish();
        }
        setContentView(R.layout.activity_intro);





        final List<ScreenItem> mLiist = new ArrayList<>();
        mLiist.add(new ScreenItem("Bienvenido!" , "¡Con el objetivo de poner a tu alcance todos los servicios ofrecidos por la Universidad de Margarita ahora tienes a tu disposición su App oficial!",  R.drawable.bembenute));
        mLiist.add(new ScreenItem("Calendario Académico!", "Además de poder mantenerte al tanto de los acontecimientos, debates y seminarios realizados ahora también podrás publicar tus propios eventos y darlos a conocer en toda la Universidad.",  R.drawable.calendarioaca));
        mLiist.add(new ScreenItem("Horarios y Notas", "Con la App oficial crea y accede de forma fácil y rápida a tu horario personalizado, ¡recibiendo notificaciones sobre cambios en el mismo o acerca de la publicación de tus notas definitivas!",  R.drawable.horarios));
        mLiist.add(new ScreenItem("Carnet Electrónico!", "¡Obtén acceso a todos los servicios ofrecidos por la universidad de una forma más rápida y segura con tu carnet electrónico!",  R.drawable.carnet));
        mLiist.add(new ScreenItem("Notificaciones sobre eventos", "Recibe notificaciones personalizadas sobre cambios en el horario, publicación de notas, actividades recreativas, clases, tareas etc.",  R.drawable.alert));




        screenPager = findViewById(R.id.sc_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mLiist);
        screenPager.setAdapter(introViewPagerAdapter);
        btnGetStarted = findViewById(R.id.btn_getStarted);
        btnAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        tabIndicator = findViewById(R.id.tab_indicator);
        tabIndicator.setupWithViewPager(screenPager);

        btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if (position<mLiist.size()){

                    position++;
                    screenPager.setCurrentItem(position);

                }

                if(position ==mLiist.size()-1){

                    loadLastScreen();

                }
            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()== mLiist.size()-1){

                    loadLastScreen();

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(mainActivity);
                savePrefsData();
                finish();
            }
        });

    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened",false);
        return isIntroActivityOpenedBefore;

    }

    private void savePrefsData(){

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened",true);
        editor.commit();

    }

    private void loadLastScreen(){

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        btnGetStarted.setAnimation(btnAnimation);


    }
}
