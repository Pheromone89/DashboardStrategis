package id.go.bpkp.dashboardstrategis.activity.dashboard;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import id.go.bpkp.dashboardstrategis.R;
import id.go.bpkp.dashboardstrategis.konfigurasi.Utils;

public class DashboardActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private Fragment dashboardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_dashboard);

        initiateView();
        populateView();
        setOnClick();

        Utils.setFragment(DashboardActivity.this, dashboardFragment, false);
    }

    private void setOnClick() {
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.nav_home) {
                    Utils.setFragment(DashboardActivity.this, dashboardFragment, false);
                    return true;
                } else if (i == R.id.nav_message) {
                    return true;
                } else if (i == R.id.nav_notification) {
                    return true;
                }else if (i == R.id.nav_person) {
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private void populateView() {

    }

    private void initiateView() {
        mMainNav = findViewById(R.id.main_nav);
        mMainFrame = findViewById(R.id.main_frame);

        dashboardFragment = new DashboardFragment();
    }
}
