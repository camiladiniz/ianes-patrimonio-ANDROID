package br.senai.sp.info.ianespatrimonio.rest;

import br.senai.sp.info.ianespatrimonio.model.Usuario;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Endpoints que queremos acessar da API
 */
public interface UsuarioRestInterface {

    @POST("rest/auth/jwt")
    Call<ResponseBody> realizarLogin(@Body Usuario usuario);

}
