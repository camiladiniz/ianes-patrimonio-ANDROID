package br.senai.sp.info.ianespatrimonio.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.senai.sp.info.ianespatrimonio.R;
import br.senai.sp.info.ianespatrimonio.config.RetrofitConfig;
import br.senai.sp.info.ianespatrimonio.model.ItemPatrimonio;
import br.senai.sp.info.ianespatrimonio.utils.AppUtils;
import br.senai.sp.info.ianespatrimonio.views.adapter.ItemAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class ItensActivity extends AppCompatActivity {

    private RecyclerView rvItens;
    private List<ItemPatrimonio> itemList = new ArrayList<>();
    private ItemAdapter itemAdapter;
    private String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens);


        //configs menu
        
        //fim configs menu


        rvItens = findViewById(R.id.rvItens);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(linearLayoutManager);
        itemAdapter = new ItemAdapter(itemList, getApplicationContext());
        rvItens.setAdapter(itemAdapter);

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.sharedPref, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        retrofit2.Call<List<ItemPatrimonio>> callItens = new RetrofitConfig(token).getItensPatrimonioRestInterface().listarItens();
        callItens.enqueue(new Callback<List<ItemPatrimonio>>() {
            @Override
            public void onResponse(retrofit2.Call<List<ItemPatrimonio>> call, Response<List<ItemPatrimonio>> response) {
                if(response.isSuccessful()){
                    itemList = response.body();

                    if(itemList != null){
                        Log.d("itens", itemList.toString());
                        itemAdapter.setItensDaLista(itemList);
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<ItemPatrimonio>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao carregar itens", Toast.LENGTH_LONG).show();
            }
        });

    }


}
