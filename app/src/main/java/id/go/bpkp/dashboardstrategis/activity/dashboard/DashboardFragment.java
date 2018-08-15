package id.go.bpkp.dashboardstrategis.activity.dashboard;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import id.go.bpkp.dashboardstrategis.R;
import id.go.bpkp.dashboardstrategis.activity.dashboard.summary.SummaryPagerFragment;
import id.go.bpkp.dashboardstrategis.konfigurasi.SavedInstance;
import id.go.bpkp.dashboardstrategis.konfigurasi.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    private View root, summaryButton, profileButton;

    public DashboardFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        root = view;
        initiateView();
        populateView();
        setOnClick();

    }

    private void setOnClick() {

        summaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.setFragment(getActivity(), new SummaryPagerFragment(), true);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }

    private void populateView() {
    }

    private void initiateView() {
        summaryButton = root.findViewById(R.id.summary_button);
        profileButton = root.findViewById(R.id.profile_button);
    }
}
