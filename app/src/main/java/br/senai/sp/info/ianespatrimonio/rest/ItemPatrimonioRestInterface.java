package br.senai.sp.info.ianespatrimonio.rest;

import java.util.List;

import br.senai.sp.info.ianespatrimonio.model.ItemPatrimonio;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Endpoints realacionados aos itens de patrimonio
 */
public interface ItemPatrimonioRestInterface {

    @GET("rest/itens")
    Call<List<ItemPatrimonio>> listarItens();

}
