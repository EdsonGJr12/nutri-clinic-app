package br.com.nutriclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.nutriclinic.api.ApiClient;
import br.com.nutriclinic.api.ApiClientConfig;
import br.com.nutriclinic.api.LoginForm;
import br.com.nutriclinic.api.TokenModel;
import br.com.nutriclinic.config.SharedPreferencesConfig;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView loginTextView;
    private TextView senhaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.loginTextView = findViewById(R.id.login);
        this.senhaTextView = findViewById(R.id.senha);
    }

    public void logar(View view) {
        ApiClient apiClient = ApiClientConfig.createClient(ApiClient.class);

        LoginForm loginForm = new LoginForm();
        loginForm.setLogin(this.loginTextView.getText().toString());
        loginForm.setSenha(this.senhaTextView.getText().toString());

        Call<TokenModel> call = apiClient.logar(loginForm);

        Activity currentActivity = this;
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {

                if (response.isSuccessful()) {
                    TokenModel tokenModel = response.body();

                    if (tokenModel != null) {
                        SharedPreferences tokenPreference = getSharedPreferences(SharedPreferencesConfig.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = tokenPreference.edit();
                        editor.putString(SharedPreferencesConfig.TOKEN_KEY, "Bearer " + tokenModel.getToken());
                        editor.putString(SharedPreferencesConfig.NOME_USUARIO_KEY, tokenModel.getNomeUsuario());
                        editor.putLong(SharedPreferencesConfig.ID_PACIENTE_KEY, tokenModel.getIdPaciente());
                        editor.apply();

                        Intent intent = new Intent(currentActivity, HomeActivity.class);
                        startActivity(intent);

                    } else {

                        Toast.makeText(getApplicationContext(),"Resposta nula do servidor", Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 401) {

                    new AlertDialog.Builder(currentActivity)
                            .setTitle("Atenção")
                            .setMessage("Usuário ou senha inválidos")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .create()
                            .show();
                } else {

                    Toast.makeText(getApplicationContext(),"Não foi possível realizar login. Tente novamente em instantes", Toast.LENGTH_SHORT).show();
                    // segura os erros de requisição
                    ResponseBody errorBody = response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<TokenModel> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }


}