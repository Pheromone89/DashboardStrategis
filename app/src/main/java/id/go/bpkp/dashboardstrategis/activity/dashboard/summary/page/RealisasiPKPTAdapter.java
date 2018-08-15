package id.go.bpkp.dashboardstrategis.activity.dashboard.summary.page;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.go.bpkp.dashboardstrategis.R;
import id.go.bpkp.dashboardstrategis.konfigurasi.RecyclerViewClickListener;
import id.go.bpkp.dashboardstrategis.model.RealisasiPKPT;

/**
 * Created by ASUS on 11/02/2018.
 */

public class RealisasiPKPTAdapter extends RecyclerView.Adapter<RealisasiPKPTAdapter.RealisasiPKPTViewHolder> {

    private static RecyclerViewClickListener itemListener;
    private Context context;
    private List<RealisasiPKPT> realisasiPKPTList;

    public RealisasiPKPTAdapter(
            Context context,
            List<RealisasiPKPT> realisasiPKPTList,
            RecyclerViewClickListener itemListener) {
        this.context = context;
        this.realisasiPKPTList = realisasiPKPTList;
        this.itemListener = itemListener;
    }

    @Override
    public RealisasiPKPTViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_realisasi_pkpt, parent, false);
        RealisasiPKPTViewHolder realisasiPKPTViewHolder = new RealisasiPKPTViewHolder(view);
        return realisasiPKPTViewHolder;
    }

    @Override
    public void onBindViewHolder(RealisasiPKPTViewHolder holder, int position) {
        RealisasiPKPT realisasiPkpt = realisasiPKPTList.get(position);

        String label = realisasiPkpt.getLabel();
        String realisasi = realisasiPkpt.getRealisasi();
        String total = realisasiPkpt.getTotal();
        String persentase = realisasiPkpt.getPersentase();

        holder.labelView.setText(label);
        holder.realisasiView.setText(realisasi);
        holder.totalView.setText(total);
        holder.persentaseView.setText(persentase);
    }

    @Override
    public int getItemCount() {
        return realisasiPKPTList.size();
    }

    class RealisasiPKPTViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout root;
        TextView labelView, realisasiView, totalView, persentaseView;

        public RealisasiPKPTViewHolder(View itemView) {
            super(itemView);

            root = itemView.findViewById(R.id.root);
            labelView = itemView.findViewById(R.id.label);
            realisasiView = itemView.findViewById(R.id.realisasi);
            totalView = itemView.findViewById(R.id.total);
            persentaseView = itemView.findViewById(R.id.persentase);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view, this.getLayoutPosition());
        }
    }
}