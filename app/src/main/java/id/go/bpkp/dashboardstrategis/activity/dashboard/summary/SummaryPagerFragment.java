package id.go.bpkp.dashboardstrategis.activity.dashboard.summary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import id.go.bpkp.dashboardstrategis.R;

/**
 * Created by ASUS on 11/02/2018.
 */

public class SummaryPagerFragment extends Fragment {

    private Bundle fragmentBundle;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentBundle = new Bundle();

        View result = inflater.inflate(R.layout.pager_summary, container, false);
        ViewPager profilPegawaiViewPager = (ViewPager) result.findViewById(R.id.pager_summary);
        profilPegawaiViewPager.setAdapter(buildAdapter());

        TabLayout profilPegawaiTabLayout = (TabLayout) result.findViewById(R.id.pager_tab_summary);
        profilPegawaiTabLayout.setupWithViewPager(profilPegawaiViewPager);

        return (result);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ngambil judul dan ngeset judul fragment
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_fragment_profil_pegawai);
        setHasOptionsMenu(true);
    }

    private PagerAdapter buildAdapter() {
        SummaryFragmentPagerAdapter summaryFragmentPagerAdapter = new SummaryFragmentPagerAdapter(
                getFragmentManager(),
                getActivity(),
                fragmentBundle
        );

        return summaryFragmentPagerAdapter;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_fragment_profil_pegawai);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
