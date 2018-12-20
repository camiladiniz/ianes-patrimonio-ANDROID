package br.senai.sp.info.ianespatrimonio.rest;

import java.util.List;

import br.senai.sp.info.ianespatrimonio.model.Movimentacao;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Endpoints relacionados a movimentacao
 */
public interface MovimentacaoRestInterface {

    @POST("rest/itens/{id}/movimentacoes")
    Call<Movimentacao> salvarMovimentacao(@Body Movimentacao movimentacao, @Path("id") Long id);

    @GET("rest/itens/{id}/movimentacoes")
    Call<List<Movimentacao>> buscarMovimentacoesItem(@Path("id") Long id);
}
