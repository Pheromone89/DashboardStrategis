package id.go.bpkp.dashboardstrategis.activity.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import id.go.bpkp.dashboardstrategis.R;
import id.go.bpkp.dashboardstrategis.activity.tema.TemaActivity;
import id.go.bpkp.dashboardstrategis.konfigurasi.RecyclerViewClickListener;
import id.go.bpkp.dashboardstrategis.model.Deputi;

public class DeputiAdapter extends RecyclerView.Adapter<DeputiAdapter.DeputiViewHolder> {

    private static RecyclerViewClickListener itemListener;
    private Context context;
    private List<Deputi> deputiList;

    public DeputiAdapter(
            Context context,
            List<Deputi> deputiList,
            RecyclerViewClickListener itemListener) {
        this.context = context;
        this.deputiList = deputiList;
        this.itemListener = itemListener;
    }

    @Override
    public DeputiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_deputi, parent, false);
        DeputiViewHolder deputiViewHolder = new DeputiViewHolder(view);
        return deputiViewHolder;
    }

    @Override
    public void onBindViewHolder(DeputiViewHolder holder, int position) {
        Deputi deputi = deputiList.get(position);

        int id = deputi.getIndex();
        String label = deputi.getNama();
        String pimpinan = deputi.getPimpinan();

        holder.idView.setText(Integer.toString(id));
        holder.labelView.setText(label);

    }

    @Override
    public int getItemCount() {
        return deputiList.size();
    }

    class DeputiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout rootView;
        TextView idView, labelView;

        public DeputiViewHolder(View itemView) {
            super(itemView);

            rootView = itemView.findViewById(R.id.root);

            idView = itemView.findViewById(R.id.id);
            labelView = itemView.findViewById(R.id.label);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view, this.getLayoutPosition());

            Intent temaIntent = new Intent(context, TemaActivity.class);
            context.startActivity(temaIntent);
        }
    }
}