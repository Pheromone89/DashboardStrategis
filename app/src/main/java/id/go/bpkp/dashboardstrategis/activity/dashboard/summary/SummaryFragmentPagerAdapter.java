package id.go.bpkp.dashboardstrategis.activity.dashboard.summary;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import id.go.bpkp.dashboardstrategis.R;
import id.go.bpkp.dashboardstrategis.activity.dashboard.summary.page.IndikatorFragment;
import id.go.bpkp.dashboardstrategis.activity.dashboard.summary.page.JumlahPPFragment;
import id.go.bpkp.dashboardstrategis.activity.dashboard.summary.page.RealisasiPKPTFragment;
import id.go.bpkp.dashboardstrategis.konfigurasi.Konfigurasi;

/**
 * Created by ASUS on 11/02/2018.
 */

public class SummaryFragmentPagerAdapter extends FragmentPagerAdapter {

    private final Bundle fragmentBundle;
    private Context mContext;

    public SummaryFragmentPagerAdapter(FragmentManager fm, Context context, Bundle data) {
        super(fm);
        mContext = context;
        fragmentBundle = data;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new RealisasiPKPTFragment();
                return fragment;
            case 1:
                fragment = new JumlahPPFragment();
                return fragment;
            case 2:
                fragment = new IndikatorFragment();
                fragmentBundle.putString("konten", Konfigurasi.URL_GET_INDIKATORPEMDA);
                fragment.setArguments(fragmentBundle);
                return fragment;
            case 3:
                fragment = new IndikatorFragment();
                fragmentBundle.putString("konten", Konfigurasi.URL_GET_INDIKATORKL);
                fragment.setArguments(fragmentBundle);
                return fragment;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.page_realisasi_pkpt);
            case 1:
                return mContext.getString(R.string.page_jumlah_pp);
            case 2:
                return mContext.getString(R.string.page_indikato_pemda);
            case 3:
                return mContext.getString(R.string.page_indikator_kl);
            default:
                return null;
        }
    }
}
