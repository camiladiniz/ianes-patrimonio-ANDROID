package br.senai.sp.info.ianespatrimonio.rest;

import java.util.List;

import br.senai.sp.info.ianespatrimonio.model.Ambiente;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Endpoints relacionados ao ambiente
 */
public interface AmbienteRestInterface {

    @GET("rest/ambientes")
    Call<List<Ambiente>> listarAmbientes();
}
