package br.senai.sp.info.ianespatrimonio.views.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.senai.sp.info.ianespatrimonio.R;
import br.senai.sp.info.ianespatrimonio.model.Movimentacao;
import br.senai.sp.info.ianespatrimonio.views.adapter.MovimentacaoAdapter;

public class MovimentacaoViewHolder extends RecyclerView.ViewHolder {

    public final TextView ambienteOrigem;
    public final TextView ambienteDestino;
    public final TextView data;
    public final TextView usuario;
    private Long movimentacaoId;
    private MovimentacaoAdapter movAdapter;

    public MovimentacaoViewHolder(View itemView, MovimentacaoAdapter movimentacaoAdapter) {
        super(itemView);
        this.ambienteOrigem = itemView.findViewById(R.id.ambienteOrigem);
        this.ambienteDestino = itemView.findViewById(R.id.ambienteDestino);
        this.data = itemView.findViewById(R.id.dataUltimaMov);
        this.usuario = itemView.findViewById(R.id.usuario);
        this.movAdapter = movimentacaoAdapter;
    }

    public void preencher(Movimentacao movimentacao){

        ambienteOrigem.setText(movimentacao.getAmbienteOrigem().toString());
        ambienteDestino.setText(movimentacao.getAmbienteDestino().toString());
        //data.setText(movimentacao.getDataMovimentacao().toString());
        usuario.setText(movimentacao.getUsuario().toString());

        movimentacaoId = movimentacao.getId();
    }


}
