package id.go.bpkp.dashboardstrategis.activity.kegiatan.adapter;

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
import id.go.bpkp.dashboardstrategis.model.Kegiatan;
import id.go.bpkp.dashboardstrategis.model.TemaDeputi;

public class KegiatanAdapter extends RecyclerView.Adapter<KegiatanAdapter.KegiatanViewHolder> {

    private static RecyclerViewClickListener itemListener;
    private Context context;
    private List<Kegiatan> kegiatanList;

    public KegiatanAdapter(
            Context context,
            List<Kegiatan> kegiatanList,
            RecyclerViewClickListener itemListener) {
        this.context = context;
        this.kegiatanList = kegiatanList;
        this.itemListener = itemListener;
    }

    @Override
    public KegiatanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_kegiatan, parent, false);
        KegiatanViewHolder kegiatanViewHolder = new KegiatanViewHolder(view);
        return kegiatanViewHolder;
    }

    @Override
    public void onBindViewHolder(KegiatanViewHolder holder, int position) {
        Kegiatan kegiatan = kegiatanList.get(position);

        int id = kegiatan.getId();
        String label = kegiatan.getKegiatan();
        int budget = kegiatan.getBudget();

        holder.idView.setText(""+id);
        holder.labelView.setText(label);
        holder.budgetView.setText(""+budget);

    }

    @Override
    public int getItemCount() {
        return kegiatanList.size();
    }

    class KegiatanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout rootView;
        TextView idView, labelView, budgetView;

        public KegiatanViewHolder(View itemView) {
            super(itemView);

            rootView = itemView.findViewById(R.id.root);

            idView = itemView.findViewById(R.id.id);
            labelView = itemView.findViewById(R.id.label);
            budgetView = itemView.findViewById(R.id.budget);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view, this.getLayoutPosition());

            Toast.makeText(context, "nice", Toast.LENGTH_SHORT).show();
        }
    }
}