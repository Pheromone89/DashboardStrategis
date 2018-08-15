package id.go.bpkp.dashboardstrategis.activity.dashboard.summary.page;


import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;

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
import id.go.bpkp.dashboardstrategis.konfigurasi.graphs.Graphs;
import id.go.bpkp.dashboardstrategis.model.JumlahPP;
import id.go.bpkp.dashboardstrategis.model.RealisasiPKPT;

/**
 * A simple {@link Fragment} subclass.
 */
public class JumlahPPFragment extends Fragment implements RecyclerViewClickListener{

    private View root;
    private LinearLayout rootLayout;
    private PieChart pieChart;
    private ArrayList<String> labelList, valueList;
    private ArrayList<JumlahPP> jumlahPPList;
    private String JSON_STRING;
    private LayoutInflater layoutInflater;

    public JumlahPPFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page_jumlah_pp, container, false);
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
        getJsonJumlahPP();
    }

    private void initiateView() {
        rootLayout = root.findViewById(R.id.root_linear);
        pieChart = root.findViewById(R.id.piechart);

        jumlahPPList = new ArrayList<>();
        labelList = new ArrayList<>();
        valueList = new ArrayList<>();
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

    }



    private void parseJsonJumlahPP() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(JSON_STRING);
            if (jsonObject.getString("success").equals("true")){
                JSONArray jsonArray = jsonObject.getJSONArray("fokuspengawasan");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jo = jsonArray.getJSONObject(i);
                    String label = jo.getString("label");
                    String fokus = jo.getString("fokus");
                    String jumlah = jo.getString("jumlah");
                    String persentase = jo.getString("persentase");

                    jumlahPPList.add(
                            new JumlahPP(
                                    fokus,
                                    label,
                                    jumlah,
                                    persentase
                            )
                    );
                }
                populateViewPieChartJumlahPP();
            } else {
                Toast.makeText(getActivity(), "gagal menarik data PP", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "gagal menarik data PP", Toast.LENGTH_SHORT).show();
        }
    }

    private void getJsonJumlahPP() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                JSON_STRING = s;
                parseJsonJumlahPP();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Konfigurasi.URL_GET_JUMLAHPP + SavedInstance.userToken);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void populateViewPieChartJumlahPP() {
        for (int i = 0; i < jumlahPPList.size(); i++){
            String label =
                    jumlahPPList.get(i).getFokus()
                            + System.lineSeparator()
                            + jumlahPPList.get(i).getPersentase();
            labelList.add(label);
            valueList.add(jumlahPPList.get(i).getJumlah());
        }

        Graphs.createPieChart(getActivity(), valueList, labelList, pieChart);
    }

}
