package id.go.bpkp.dashboardstrategis.activity.kegiatan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import id.go.bpkp.dashboardstrategis.R;
import id.go.bpkp.dashboardstrategis.activity.kegiatan.adapter.KegiatanAdapter;
import id.go.bpkp.dashboardstrategis.activity.tema.TemaActivity;
import id.go.bpkp.dashboardstrategis.activity.tema.adapter.TemaDeputiAdapter;
import id.go.bpkp.dashboardstrategis.konfigurasi.RecyclerViewClickListener;
import id.go.bpkp.dashboardstrategis.model.Kegiatan;
import id.go.bpkp.dashboardstrategis.model.TemaDeputi;

public class KegiatanActivity extends AppCompatActivity implements RecyclerViewClickListener {

    private String userToken;
    private LinearLayout rootLayout;
    private RecyclerView kegiatanRecyclerView;
    private List<Kegiatan> kegiatanList;
    private KegiatanAdapter kegiatanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan);

        initiateView();
        populateView();
        setOnClick();

    }

    private void initiateView() {
        kegiatanList = new ArrayList<>();
        kegiatanList.add(new Kegiatan(
                1,
                "SEA Games",
                100000
        ));

        rootLayout = findViewById(R.id.root);
        rootLayout.setVisibility(View.GONE);

        kegiatanRecyclerView = findViewById(R.id.main_recyclerview);
        kegiatanRecyclerView.setHasFixedSize(true);
        kegiatanRecyclerView.setLayoutManager(new LinearLayoutManager(KegiatanActivity.this));
    }

    private void populateView() {
        rootLayout.setVisibility(View.VISIBLE);

        kegiatanAdapter = new KegiatanAdapter(KegiatanActivity.this, kegiatanList, this);
        kegiatanRecyclerView.setAdapter(kegiatanAdapter);
    }

    private void setOnClick() {

    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

    }
}