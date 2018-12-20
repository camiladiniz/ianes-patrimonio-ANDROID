package br.senai.sp.info.ianespatrimonio.views.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.sp.info.ianespatrimonio.R;
import br.senai.sp.info.ianespatrimonio.model.Movimentacao;
import br.senai.sp.info.ianespatrimonio.views.holder.MovimentacaoViewHolder;

public class MovimentacaoAdapter extends RecyclerView.Adapter {

    private List<Movimentacao> listaMovimentacao;
    private Context context;

    public MovimentacaoAdapter(List<Movimentacao> listaMovimentacao, Context context) {
        this.listaMovimentacao = listaMovimentacao;
        this.context = context;
    }

    public void setItensDaLista(List<Movimentacao> itensDaLista) {
        this.listaMovimentacao = itensDaLista;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movimentacao_lista, parent, false);
        MovimentacaoViewHolder movHolder = new MovimentacaoViewHolder(view, this);

        return movHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovimentacaoViewHolder movHolder = (MovimentacaoViewHolder) holder;
        Movimentacao movimentacao = listaMovimentacao.get(position);
        ((MovimentacaoViewHolder) holder).preencher(movimentacao);

    }

    @Override
    public int getItemCount() {
        return listaMovimentacao.size();
    }


}
