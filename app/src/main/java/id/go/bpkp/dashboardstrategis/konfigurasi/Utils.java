package id.go.bpkp.dashboardstrategis.konfigurasi;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import id.go.bpkp.dashboardstrategis.R;

public class Utils {
    public static void setFragment(Context context, Fragment fragment, boolean isAdd) {
        FragmentTransaction fragmentTransaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
        if (isAdd){
            fragmentTransaction.add(R.id.main_frame,fragment);
        } else {
            fragmentTransaction.replace(R.id.main_frame,fragment);
        }
        fragmentTransaction.commit();
    }
}
