package br.senai.sp.info.ianespatrimonio.views.holder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.senai.sp.info.ianespatrimonio.R;
import br.senai.sp.info.ianespatrimonio.model.ItemPatrimonio;
import br.senai.sp.info.ianespatrimonio.views.MovimentacaoActivity;
import br.senai.sp.info.ianespatrimonio.views.adapter.ItemAdapter;


public class ItemViewHolder extends RecyclerView.ViewHolder {

    public final TextView tvPatrimonioItem;
    public final TextView dataUltimaMovimentacao;
    public final TextView tvItemId;
    private Long itemId;
    private ItemAdapter itemAdapter;

    public ItemViewHolder(final View itemView, ItemAdapter itemAdapter) {
        super(itemView);
        this.tvPatrimonioItem = itemView.findViewById(R.id.patrimonio);
        this.dataUltimaMovimentacao = itemView.findViewById(R.id.dataUltimaMov);
        this.tvItemId = itemView.findViewById(R.id.itemId);
        this.itemAdapter = itemAdapter;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MovimentacaoActivity.class);
                intent.putExtra("idItem", itemId);
                v.getContext().startActivity(intent);
            }
        });

    }

    public void preencher(ItemPatrimonio item){
        itemId = item.getId();
        tvPatrimonioItem.setText(item.getPatrimonio().toString());
        //item.setDataUltimaMovimentacao( (item.getDataUltimaMovimentacao() != null) ? dataUltimaMovimentacao.setText(item.getDataUltimaMovimentacao().toString()) : dataUltimaMovimentacao.setText("Item nunca foi movimentado");
        if(item.getDataUltimaMovimentacao() != null){
            dataUltimaMovimentacao.setText(item.getDataUltimaMovimentacao().toString());
        }else{
            dataUltimaMovimentacao.setText("Item nunca foi movimentado");
        }
        tvItemId.setText(item.getId().toString());
        itemId = item.getId();
    }
}
