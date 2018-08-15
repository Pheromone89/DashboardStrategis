package id.go.bpkp.dashboardstrategis.activity.tema.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.go.bpkp.dashboardstrategis.R;
import id.go.bpkp.dashboardstrategis.activity.kegiatan.KegiatanActivity;
import id.go.bpkp.dashboardstrategis.activity.tema.TemaActivity;
import id.go.bpkp.dashboardstrategis.konfigurasi.RecyclerViewClickListener;
import id.go.bpkp.dashboardstrategis.model.Deputi;
import id.go.bpkp.dashboardstrategis.model.TemaDeputi;

public class TemaDeputiAdapter extends RecyclerView.Adapter<TemaDeputiAdapter.TemaDeputiViewHolder> {

    private static RecyclerViewClickListener itemListener;
    private Context context;
    private List<TemaDeputi> temaDeputiList;

    public TemaDeputiAdapter(
            Context context,
            List<TemaDeputi> temaDeputiList,
            RecyclerViewClickListener itemListener) {
        this.context = context;
        this.temaDeputiList = temaDeputiList;
        this.itemListener = itemListener;
    }

    @Override
    public TemaDeputiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_tema_deputi, parent, false);
        TemaDeputiViewHolder temaDeputiViewHolder = new TemaDeputiViewHolder(view);
        return temaDeputiViewHolder;
    }

    @Override
    public void onBindViewHolder(TemaDeputiViewHolder holder, int position) {
        TemaDeputi temaDeputi = temaDeputiList.get(position);

        int id = temaDeputi.getId();
        String label = temaDeputi.getJudulTema();
        boolean isActive = temaDeputi.isActive();
        String status;
        if(isActive){
            status = "aktif";
        } else {
            status = "tidak aktif";
        }

        holder.idView.setText(Integer.toString(id));
        holder.labelView.setText(label);
        holder.activeView.setText(status);

    }

    @Override
    public int getItemCount() {
        return temaDeputiList.size();
    }

    class TemaDeputiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout rootView;
        TextView idView, labelView, activeView;

        public TemaDeputiViewHolder(View itemView) {
            super(itemView);

            rootView = itemView.findViewById(R.id.root);

            idView = itemView.findViewById(R.id.id);
            labelView = itemView.findViewById(R.id.label);
            activeView = itemView.findViewById(R.id.is_active);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view, this.getLayoutPosition());

            Intent kegiatanIntent = new Intent(context, KegiatanActivity.class);
            context.startActivity(kegiatanIntent);
        }
    }
}