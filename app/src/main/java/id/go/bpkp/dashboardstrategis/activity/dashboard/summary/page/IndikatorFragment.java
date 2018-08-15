package id.go.bpkp.dashboardstrategis.activity.dashboard.summary.page;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import id.go.bpkp.dashboardstrategis.model.IndikatorUmum;
import id.go.bpkp.dashboardstrategis.model.JumlahPP;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndikatorFragment extends Fragment implements RecyclerViewClickListener{

    private View root;
    private LinearLayout rootLayout;
    private PieChart pieChartSPIP, pieChartKAPIP, pieChartOpini;
    //
    private ArrayList<IndikatorUmum> indikatorAll;
    //
    private ArrayList<String> labelListSPIP, valueListSPIP, labelListKAPIP, valueListKAPIP, labelListOpini, valueListOpini;
    private ArrayList<PieChart> pieChartIndikator;
    private ArrayList<IndikatorUmum> spipList, kapipList, opiniList;
    private String JSON_STRING, mUrl;
    private LayoutInflater layoutInflater;

    public IndikatorFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page_indikator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUrl = this.getArguments().getString("konten");
        root = view;
        initiateView();
        populateView();
        setOnClick();

    }

    private void setOnClick() {

    }

    private void populateView() {
        getJsonIndikator();
    }

    private void initiateView() {
        indikatorAll = new ArrayList<>();
        pieChartIndikator = new ArrayList<>();
//        spipList = new ArrayList<>();
//        kapipList = new ArrayList<>();
//        opiniList = new ArrayList<>();
//        labelListSPIP = new ArrayList<>();
//        valueListSPIP = new ArrayList<>();
//        labelListKAPIP = new ArrayList<>();
//        valueListKAPIP = new ArrayList<>();
//        labelListOpini = new ArrayList<>();
//        valueListOpini = new ArrayList<>();

        rootLayout = root.findViewById(R.id.root_linear);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

    }

    private void parseJsonIndikator() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(JSON_STRING);
            if (jsonObject.getString("success").equals("true")){

                JSONObject joSpip = jsonObject.getJSONObject("spip");
                JSONArray jaSpip = joSpip.getJSONArray("data");
                JSONObject joKapip = jsonObject.getJSONObject("kapip");
                JSONArray jaKapip = joSpip.getJSONArray("data");
                JSONObject joOpini = jsonObject.getJSONObject("opini");
                JSONArray jaOpini = joSpip.getJSONArray("data");

                for ( int i = 0 ; i < jaSpip.length() ; i++ ){
                    indikatorAll.add(
                            new IndikatorUmum(
                                    "spip",
                                    i,
                                    joSpip.getString("label"),
                                    joSpip.getString("tahun"),
                                    jaSpip.getJSONObject(i).getString("label"),
                                    jaSpip.getJSONObject(i).getString("nilai")
                            )
                    );
                }

                for ( int i = 0 ; i < jaKapip.length() ; i++ ){
                    indikatorAll.add(
                            new IndikatorUmum(
                                    "kapip",
                                    i,
                                    joKapip.getString("label"),
                                    joKapip.getString("tahun"),
                                    jaKapip.getJSONObject(i).getString("label"),
                                    jaKapip.getJSONObject(i).getString("nilai")
                            )
                    );
                }

                for ( int i = 0 ; i < jaOpini.length() ; i++ ){
                    indikatorAll.add(
                            new IndikatorUmum(
                                    "opini",
                                    i,
                                    joOpini.getString("label"),
                                    joOpini.getString("tahun"),
                                    jaOpini.getJSONObject(i).getString("label"),
                                    jaOpini.getJSONObject(i).getString("nilai")
                            )
                    );
                }

//                indikatorAll.add(
//                        new IndikatorUmum(
//                                jsonObject.get
//                        )
//                );
//                putData(jsonObject, "spip", spipList, labelListSPIP, valueListSPIP);
//                putData(jsonObject, "kapip", kapipList, labelListKAPIP, valueListKAPIP);
//                putData(jsonObject, "opini", opiniList, labelListOpini, valueListOpini);

                populateViewPieChartIndikator();
            } else {
                Toast.makeText(getActivity(), "gagal menarik data indikator", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "json exception", Toast.LENGTH_SHORT).show();
        }
    }

    private void getJsonIndikator() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                JSON_STRING = s;
                parseJsonIndikator();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(mUrl + SavedInstance.userToken);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void populateViewPieChartIndikator() {
        pieChartIndikator.add((PieChart) root.findViewById(R.id.piechart_spip));
        pieChartIndikator.add((PieChart) root.findViewById(R.id.piechart_kapip));
        pieChartIndikator.add((PieChart) root.findViewById(R.id.piechart_opini));

        for ( int i = 0; i < indikatorAll.size(); i++ ){
            ArrayList<String> label = new ArrayList<>();
            ArrayList<String> value = new ArrayList<>();
            String tag = indikatorAll.get(i).getTag();
            while (tag.equals("spip")){
                label.add(indikatorAll.get(i).getLabel());
                value.add(indikatorAll.get(i).getNilai());
            }
            if (tag.equals("spip")){
                Graphs.createPieChart(getActivity(), value, label, pieChartIndikator.get(0));
                label.clear();
                value.clear();
            }
            while (tag.equals("kapip")){
                label.add(indikatorAll.get(i).getLabel());
                value.add(indikatorAll.get(i).getNilai());
            }
            if (tag.equals("kapip")){
                Graphs.createPieChart(getActivity(), value, label, pieChartIndikator.get(1));
                label.clear();
                value.clear();
            }
            while (tag.equals("opini")){
                label.add(indikatorAll.get(i).getLabel());
                value.add(indikatorAll.get(i).getNilai());
            }
            if (tag.equals("opini")){
                Graphs.createPieChart(getActivity(), value, label, pieChartIndikator.get(2));
                label.clear();
                value.clear();
            }
        }
//        for (int i = 0; i < pieChartIndikator.size(); i++ ){
//            PieChart pc = pieChartIndikator.get(i);
//
//            String tag = indikatorAll.get(i).getTag();
//            if (tag.equals("spip")){
//                label.add(indikatorAll.get(i).getLabel());
//                value.add(indikatorAll.get(i).getNilai());
//            }
//
//
//            switch (i){
//                case 0:
//                    Graphs.createPieChart(getActivity(), valueListSPIP, labelListSPIP, pc);
//                    break;
//                case 1:
//                    Graphs.createPieChart(getActivity(), valueListKAPIP, labelListKAPIP, pc);
//                    break;
//                case 2:
//                    Graphs.createPieChart(getActivity(), valueListOpini, labelListOpini, pc);
//                    break;
//            }
//        }
    }

//    private void putData (JSONObject jsonObject, String jsonTag, List<IndikatorUmum> commonList, ArrayList<String> labelList, ArrayList<String> nilaiList){
//        JSONArray array = null;
//        String judul, tahun;
//        try {
//            judul = jsonObject.getJSONObject(jsonTag).getString("label");
//            tahun = jsonObject.getJSONObject(jsonTag).getString("tahun");
//            String title = judul + " - " + tahun;
//            array = jsonObject.getJSONObject(jsonTag).getJSONArray("data");
//            for ( int i = 0; i < array.length(); i++ ) {
//                JSONObject jo = array.getJSONObject(i);
//                String label = jo.getString("label");
//                String nilai = jo.getString("status");
//                commonList.add(new IndikatorUmum(jsonTag, i, label, nilai));
//                labelList.add(label);
//                nilaiList.add(nilai);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Toast.makeText(getActivity(), "gagal menarik data indikator", Toast.LENGTH_SHORT).show();
//        }
//    }
}
