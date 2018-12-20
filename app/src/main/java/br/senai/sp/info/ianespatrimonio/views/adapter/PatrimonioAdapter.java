package br.senai.sp.info.ianespatrimonio.views.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.sp.info.ianespatrimonio.R;
import br.senai.sp.info.ianespatrimonio.model.Patrimonio;
import br.senai.sp.info.ianespatrimonio.views.holder.PatrimonioViewHolder;

public class PatrimonioAdapter extends RecyclerView.Adapter {

    private List<Patrimonio> patrimonioList;
    private Context context;

    public PatrimonioAdapter(List<Patrimonio> patrimonioList, Context context) {
        this.patrimonioList = patrimonioList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.patrimonio_lista, parent, false);
        PatrimonioViewHolder patViewHolder = new PatrimonioViewHolder(view, this);
        return patViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PatrimonioViewHolder patrimonioViewHolder = (PatrimonioViewHolder) holder;
        Patrimonio patrimonio = patrimonioList.get(position);
        ((PatrimonioViewHolder) holder).preencher(patrimonio);
    }

    @Override
    public int getItemCount() {
        return patrimonioList.size();
    }

    public void setItensDaLista(List<Patrimonio> patrimoniosDaLista) {
        this.patrimonioList = patrimoniosDaLista;
        notifyDataSetChanged();

    }
}
