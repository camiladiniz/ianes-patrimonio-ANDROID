package br.senai.sp.info.ianespatrimonio.rest;

import java.util.List;

import br.senai.sp.info.ianespatrimonio.model.Patrimonio;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PatrimonioRestInterface {

    @GET("rest/patrimonios")
    Call<List<Patrimonio>> listarPatrimonios();

}
