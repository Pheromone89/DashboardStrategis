package id.go.bpkp.dashboardstrategis.activity.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import id.go.bpkp.dashboardstrategis.R;
import id.go.bpkp.dashboardstrategis.konfigurasi.RecyclerViewClickListener;
import id.go.bpkp.dashboardstrategis.model.Deputi;
import id.go.bpkp.dashboardstrategis.activity.main.adapter.DeputiAdapter;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener{

    private String userToken;
    private LinearLayout rootLayout;
    private RecyclerView deputiRecyclerView;
    private List<Deputi> deputiList;
    private DeputiAdapter deputiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get variable from prev
        Intent loginIntent = getIntent();
        userToken = loginIntent.getStringExtra("api_token");

        initiateView();
        populateView();
        setOnClick();

    }

    private void initiateView(){
        deputiList = new ArrayList<>();
        deputiList.add(new Deputi(
                1,
                "Deputi Satu",
                "Septian"

        ));
        deputiList.add(new Deputi(
                2,
                "Deputi Dua",
                "Debrian"

        ));
        deputiList.add(new Deputi(
                3,
                "Deputi Tiga",
                "Septrian"

        ));

        rootLayout = findViewById(R.id.root);
        rootLayout.setVisibility(View.GONE);

        deputiRecyclerView = findViewById(R.id.main_deputi_recyclerview);
        deputiRecyclerView.setHasFixedSize(true);
        deputiRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void populateView(){
        rootLayout.setVisibility(View.VISIBLE);

        deputiAdapter = new DeputiAdapter(MainActivity.this, deputiList, this);
        deputiRecyclerView.setAdapter(deputiAdapter);
    }

    private void setOnClick(){

    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

    }
}
