package br.senai.sp.info.ianespatrimonio.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import br.senai.sp.info.ianespatrimonio.rest.AmbienteRestInterface;
import br.senai.sp.info.ianespatrimonio.rest.ItemPatrimonioRestInterface;
import br.senai.sp.info.ianespatrimonio.rest.MovimentacaoRestInterface;
import br.senai.sp.info.ianespatrimonio.rest.PatrimonioRestInterface;
import br.senai.sp.info.ianespatrimonio.rest.UsuarioRestInterface;
import br.senai.sp.info.ianespatrimonio.utils.AppUtils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

    //Configuração é feita no construtor
    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                //definindo a url base da app
                .baseUrl(AppUtils.baseURL)
                //transformar resposta que vem em JSON para String
                .addConverterFactory(GsonConverterFactory.create())
                //criando o objeto
                .build();
    }

    public RetrofitConfig(final String token) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder b = chain.request().newBuilder();
                b.addHeader("Accept", "application/json");
                b.addHeader("Authorization", token);
                return chain.proceed(b.build());
            }
        }).build();

        //Construindo objeto do tipo retrofit
        this.retrofit = new Retrofit.Builder()
            //definindo a url base da nossa aplicação
            .baseUrl(AppUtils.baseURL)
            //caso seja necessário colocar um interceptor
            .client(okHttpClient)
            //transformando a resposta que vem em Json para String
            .addConverterFactory(GsonConverterFactory.create(gson))
            //criando de fato
            .build();

    }

    public UsuarioRestInterface getRestInterface() {
        return this.retrofit.create(UsuarioRestInterface.class);
    }

    public AmbienteRestInterface getAmbienteRestInterface() {
        return this.retrofit.create(AmbienteRestInterface.class);
    }

    public ItemPatrimonioRestInterface getItensPatrimonioRestInterface(){
        return this.retrofit.create(ItemPatrimonioRestInterface.class);
    }

    public MovimentacaoRestInterface getMovimentacaoRestInterface(){
        return this.retrofit.create(MovimentacaoRestInterface.class);
    }

    public PatrimonioRestInterface getPatrimonioRestInterface(){
        return this.retrofit.create(PatrimonioRestInterface.class);
    }


}
