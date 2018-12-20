package br.senai.sp.info.ianespatrimonio.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.senai.sp.info.ianespatrimonio.R;
import br.senai.sp.info.ianespatrimonio.config.RetrofitConfig;
import br.senai.sp.info.ianespatrimonio.model.Movimentacao;
import br.senai.sp.info.ianespatrimonio.utils.AppUtils;
import br.senai.sp.info.ianespatrimonio.views.adapter.MovimentacaoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovimentacaoActivity extends AppCompatActivity {

    private RecyclerView rvMovimentacao;
    private List<Movimentacao> movimentacaoList = new ArrayList<>();
    private MovimentacaoAdapter movAdapter;
    private String token = null;
    private Long idItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimentacao);

        rvMovimentacao = findViewById(R.id.rvMovimentacoes);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvMovimentacao.setLayoutManager(linearLayoutManager);
        movAdapter = new MovimentacaoAdapter(movimentacaoList, getApplicationContext());
        rvMovimentacao.setAdapter(movAdapter);

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.sharedPref, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        idItem = getIntent().getExtras().getLong("idItem");
        Log.d("idddd", idItem.toString());


        Call<List<Movimentacao>> callMovimentacoes = new RetrofitConfig(token).getMovimentacaoRestInterface().buscarMovimentacoesItem(idItem);
        callMovimentacoes.enqueue(new Callback<List<Movimentacao>>() {
            @Override
            public void onResponse(Call<List<Movimentacao>> call, Response<List<Movimentacao>> response) {

                if(response.isSuccessful()){
                    movimentacaoList = response.body();
                    Log.d("status", "não funcionou");
                    if(response.code() == 404){
                        Toast.makeText(getApplicationContext(), "O item já se encontra neste ambiente!", Toast.LENGTH_LONG).show();
                    }

                    if(movimentacaoList != null){
                        movAdapter.setItensDaLista(movimentacaoList);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "O item já se encontra neste ambiente!", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Movimentacao>> call, Throwable t) {
                Log.d("erro", t.toString());
                Log.d("erro", t.getMessage());
                Toast.makeText(getApplicationContext(), "Erro ao carregar as movimentacoes", Toast.LENGTH_LONG).show();
            }
        });
    }

}
