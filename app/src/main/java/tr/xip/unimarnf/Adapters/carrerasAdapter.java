package tr.xip.unimarnf.Adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import tr.xip.unimarnf.Fragments.Administracion;
import tr.xip.unimarnf.Fragments.Comunicacion;
import tr.xip.unimarnf.Fragments.Contaduria;
import tr.xip.unimarnf.Fragments.Derecho;
import tr.xip.unimarnf.Fragments.Diseño;
import tr.xip.unimarnf.Fragments.Idiomas;
import tr.xip.unimarnf.Fragments.Ingenieria;

public class carrerasAdapter extends FragmentStatePagerAdapter {

    String[] tabArray = new String[]{"Derecho", "Idiomas", "Administración","Ingeniería", "Contaduría","Comunicación","Diseño"};

    public carrerasAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabArray[position];
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){

            case 0:
                Derecho derecho = new Derecho();
                return derecho;
            case 1:
                Idiomas idiomas = new Idiomas();
                return idiomas;
            case 2:
                Contaduria contaduria = new Contaduria();
                return contaduria;
            case 3:
                Ingenieria ingenieria = new Ingenieria();
                return ingenieria;
            case 4:
                Administracion administracion = new Administracion();
                return administracion;
            case 5:
                Comunicacion comunicacion = new Comunicacion();
                return comunicacion;
            case 6:
                Diseño diseño = new Diseño();
                return diseño;



        }
        return null;
    }

    @Override
    public int getCount() {
        return 7;
    }
}
