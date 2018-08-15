package id.go.bpkp.dashboardstrategis.activity.tema;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import id.go.bpkp.dashboardstrategis.R;
import id.go.bpkp.dashboardstrategis.activity.tema.adapter.TemaDeputiAdapter;
import id.go.bpkp.dashboardstrategis.konfigurasi.RecyclerViewClickListener;
import id.go.bpkp.dashboardstrategis.model.Deputi;
import id.go.bpkp.dashboardstrategis.model.TemaDeputi;

public class TemaActivity extends AppCompatActivity implements RecyclerViewClickListener{

    private String userToken;
    private LinearLayout rootLayout;
    private RecyclerView temaRecyclerView;
    private List<TemaDeputi> temaDeputiList;
    private TemaDeputiAdapter temaDeputiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);

        initiateView();
        populateView();
        setOnClick();

    }

    private void initiateView(){
        temaDeputiList = new ArrayList<>();
        temaDeputiList.add(new TemaDeputi(
                1,
                "POLITIK",
                true

        ));
        temaDeputiList.add(new TemaDeputi(
                1,
                "BUDAYA",
                false

        ));
        temaDeputiList.add(new TemaDeputi(
                1,
                "PENDIDIKAN",
                true

        ));

        rootLayout = findViewById(R.id.root);
        rootLayout.setVisibility(View.GONE);

        temaRecyclerView = findViewById(R.id.main_tema_recyclerview);
        temaRecyclerView.setHasFixedSize(true);
        temaRecyclerView.setLayoutManager(new LinearLayoutManager(TemaActivity.this));
    }

    private void populateView(){
        rootLayout.setVisibility(View.VISIBLE);

        temaDeputiAdapter = new TemaDeputiAdapter(TemaActivity.this, temaDeputiList, this);
        temaRecyclerView.setAdapter(temaDeputiAdapter);
    }

    private void setOnClick(){

    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

    }
}