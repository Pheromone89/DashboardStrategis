package id.go.bpkp.dashboardstrategis.activity.dashboard.summary.page;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.go.bpkp.dashboardstrategis.R;
import id.go.bpkp.dashboardstrategis.konfigurasi.Konfigurasi;
import id.go.bpkp.dashboardstrategis.konfigurasi.RecyclerViewClickListener;
import id.go.bpkp.dashboardstrategis.konfigurasi.RequestHandler;
import id.go.bpkp.dashboardstrategis.konfigurasi.SavedInstance;
import id.go.bpkp.dashboardstrategis.model.RealisasiPKPT;

/**
 * A simple {@link Fragment} subclass.
 */
public class RealisasiPKPTFragment extends Fragment implements RecyclerViewClickListener{

    private View root;
    private RecyclerView recyclerView;
    private String JSON_STRING;
    private List<RealisasiPKPT> realisasiPKPTList;
    private RealisasiPKPTAdapter realisasiPKPTAdapter;

    public RealisasiPKPTFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page_realisasi_pkpt, container, false);
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
    }

    private void populateView() {
        getJsonRealisasiPkpt();
    }

    private void initiateView() {
        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        realisasiPKPTList = new ArrayList<>();
    }

    private void parseJsonRealisasiPkpt() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(JSON_STRING);
            if(jsonObject.getString("success").equals("true")){
                JSONArray jsonArray = jsonObject.getJSONArray("realisasipkpt");
                String judul = jsonObject.getString("judul");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jo = jsonArray.getJSONObject(i);
                    String label = jo.getString("label");
                    String realisasi = jo.getString("realisasi");
                    String total = jo.getString("pkpt");
                    String persentase = jo.getString("persentase");

                    realisasiPKPTList.add(
                            new RealisasiPKPT(
                                    label,
                                    realisasi,
                                    total,
                                    persentase
                            )
                    );
                }
                populatePkptList();
            } else {
                Toast.makeText(getActivity(), "kesalahan mengambil data PKPT", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "terjadi kesalahan, silakan masuk kembali", Toast.LENGTH_SHORT).show();
        }
    }

    private void getJsonRealisasiPkpt() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                JSON_STRING = s;
                parseJsonRealisasiPkpt();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Konfigurasi.URL_GET_REALISASIPKPT + SavedInstance.userToken);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void populatePkptList() {
        realisasiPKPTAdapter = new RealisasiPKPTAdapter(getActivity(), realisasiPKPTList, this);
        recyclerView.setAdapter(realisasiPKPTAdapter);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

    }
}
