package br.senai.sp.info.ianespatrimonio.views.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.sp.info.ianespatrimonio.R;
import br.senai.sp.info.ianespatrimonio.model.ItemPatrimonio;
import br.senai.sp.info.ianespatrimonio.views.holder.ItemViewHolder;

public class ItemAdapter extends RecyclerView.Adapter{

    private List<ItemPatrimonio> itemList;
    private Context context;

    public ItemAdapter(List<ItemPatrimonio> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lista, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view, this);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        ItemPatrimonio item = itemList.get(position);
        ((ItemViewHolder) holder).preencher(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    /**
     * Seta o adapter com uma lista vazia e esse método quando carregar a lista vai mandando para não dar erro
     * Evita o nullpointer
     * @param itensDaLista
     */
    public void setItensDaLista(List<ItemPatrimonio> itensDaLista) {
        this.itemList = itensDaLista;
        notifyDataSetChanged();

    }
}
