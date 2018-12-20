package br.senai.sp.info.ianespatrimonio.views.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.senai.sp.info.ianespatrimonio.R;
import br.senai.sp.info.ianespatrimonio.model.Patrimonio;
import br.senai.sp.info.ianespatrimonio.views.adapter.PatrimonioAdapter;

public class PatrimonioViewHolder extends RecyclerView.ViewHolder {

    public final TextView tvPatrimonio;
    private Long patId;
    private PatrimonioAdapter patAdapter;

    public PatrimonioViewHolder(View itemView, PatrimonioAdapter adapter) {
        super(itemView);
        this.tvPatrimonio = itemView.findViewById(R.id.patrimonio);
        this.patAdapter = adapter;
    }

    public void preencher(Patrimonio patrimonio){
        patId = patrimonio.getId();
        tvPatrimonio.setText(patrimonio.getNome().toString());
    }
}
