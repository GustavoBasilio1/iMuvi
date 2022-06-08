package com.example.imuvi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Search extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private EditText editSearch;
    private TextView textTitulo;
    private TextView textYear;
    private ImageView imagePoster;
    private TextView textSource;
    private TextView textValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
        editSearch = findViewById(R.id.txt_search);
        textTitulo = findViewById(R.id.txt_titulo);
        textYear = findViewById(R.id.txt_ano);
        imagePoster = findViewById(R.id.imgPoster);
        textSource = findViewById(R.id.txt_source);
        textValue = findViewById(R.id.txt_value);

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    public void buscaLivros(View view) {
        // Recupera a string de busca.
        String queryString = editSearch.getText().toString();
        // esconde o teclado qdo o botão é clicado
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        // Verifica o status da conexão de rede
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        /* Se a rede estiver disponivel e o campo de busca não estiver vazio
         iniciar o Loader CarregaLivros */
        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
            textYear.setText(R.string.str_empty);
            textTitulo.setText(R.string.loading);
        }
        // atualiza a textview para informar que não há conexão ou termo de busca
        else {
            if (queryString.length() == 0) {
                textYear.setText(R.string.str_empty);
                textTitulo.setText(R.string.no_search_term);
            } else {
                textYear.setText(" ");
                textTitulo.setText(R.string.no_network);
            }
        }
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";
        if (args != null) {
            queryString = args.getString("queryString");
        }
        return new CarregaFilmes(this, queryString);
    }
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            // Converte a resposta em Json
            JSONObject jsonObject = new JSONObject(data);
            String titulo = jsonObject.getString("Title");
            String ano = jsonObject.getString("Year");
            String poster = jsonObject.getString("Poster");

            // Obtem o JSONArray dos itens de livros
            JSONArray itemsArray = jsonObject.getJSONArray("Ratings");
            // inicializa o contador
            int i = 0;
            String source = null;
            String value = null;
            // Procura pro resultados nos itens do array
            while (i < itemsArray.length() &&
                    (source == null && value == null)) {
                // Obtem a informação
                JSONObject movie= itemsArray.getJSONObject(i);

                try {
                    source = movie.getString("Source");
                    value = movie.getString("Value");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // move para a proxima linha
                i++;
            }
            //mostra o resultado qdo possivel.
            if (titulo != null && ano != null && source != null && value != null) {
                textTitulo.setText("Título: " + titulo);
                textYear.setText("Ano: " + ano);
                imagePoster.setImageURI(Uri.parse(poster));
                textSource.setText("Fonte: "+ source);
                textValue.setText("Nota: " + value);
                //nmLivro.setText(R.string.str_empty);
            } else {
                // If none are found, update the UI to show failed results.
                textTitulo.setText(R.string.no_results);
                textYear.setText(R.string.str_empty);
                textSource.setText(R.string.str_empty);
                textValue.setText(R.string.str_empty);
            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            textTitulo.setText(R.string.no_results);
            textYear.setText(R.string.str_empty);
            textSource.setText(R.string.str_empty);
            textValue.setText(R.string.str_empty);
            e.printStackTrace();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada
    }
}