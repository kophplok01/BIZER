package tr.xip.unimarnf.Adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import tr.xip.unimarnf.Fragments.Jueves;
import tr.xip.unimarnf.Fragments.Lunes;
import tr.xip.unimarnf.Fragments.Martes;
import tr.xip.unimarnf.Fragments.Miercoles;
import tr.xip.unimarnf.Fragments.Viernes;

public class tabpagerAdapter extends FragmentStatePagerAdapter {

    String[] tabArray = new String[]{"Lunes", "Martes", "Miércoles","Jueves", "Viernes"};

    public tabpagerAdapter(FragmentManager fm) {
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
                Lunes lunes = new Lunes();
                return lunes;
            case 1:
                Martes martes = new Martes();
                return martes;
            case 2:
                Miercoles miercoles = new Miercoles();
                return miercoles;
            case 3:
                Jueves jueves = new Jueves();
                return jueves;
            case 4:
                Viernes viernes = new Viernes();
                return viernes;


        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
