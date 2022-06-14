package com.example.imuvi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Search extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private EditText editSearch;
    private TextView textTitulo, textYear, textSource, textValue, textRated, textReleased, textRuntime, textGenre,
        textDirector, textWriters, textActors, textPlot, textLanguage, textCountry, textAwards, textMetascore,
            textImdbId, textImdbRating, textImdbVotes, textType, textDvd, textboxOffice, textproduction, textwebsite,
            textTotalseasons;

    //private ImageView imagePoster;
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    int mov = 0;
    Vibrator vibrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();

        //adicionando o sensor
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor == null)
            finish();
        vibrar = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        sensorEventListener = new SensorEventListener(){
            @Override
            public void onSensorChanged(SensorEvent sensorevent) {
                float x = sensorevent.values[0];
                float y = sensorevent.values[1];
                float z = sensorevent.values[2];
                System.out.println("Valor GiroX" + x);
                if(x<-5 && mov == 0) {
                    vibrar.vibrate(100);
                    Toast.makeText(Search.this, "This app does not support landscape mode", Toast.LENGTH_SHORT).show();
                    mov++;
                } else if(x>-5 && mov == 1) {
                    vibrar.vibrate(100);
                    Toast.makeText(Search.this, "This app does not support landscape mode", Toast.LENGTH_SHORT).show();
                    mov++;

                }

                if(mov == 2) {
                    vibrar.vibrate(100);
                    Toast.makeText(Search.this, "This app does not support landscape mode", Toast.LENGTH_SHORT).show();
                    mov = 0;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }


        };
        Start();

        FindComponents();

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    //MÉTODOS DO ACELEROMETRO
    private void Start() {
        sensorManager.registerListener(sensorEventListener,sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void Stop() { sensorManager.unregisterListener(sensorEventListener); }

    @Override
    protected void onPause() {
        super.onPause();
        Stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Start();
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
            String rated = jsonObject.getString("Rated");
            String released = jsonObject.getString("Released");
            String runtime = jsonObject.getString("Runtime");
            String genre = jsonObject.getString("Genre");
            String director = jsonObject.getString("Director");
            String writers = jsonObject.getString("Writer");
            String actors = jsonObject.getString("Actors");
            String plot = jsonObject.getString("Plot");
            String language = jsonObject.getString("Language");
            String country = jsonObject.getString("Country");
            String awards = jsonObject.getString("Awards");
            String metascore = jsonObject.getString("Metascore") ;
            String imdbId = jsonObject.getString("imdbID") ;
            String imdbRating= jsonObject.getString("imdbRating") ;
            String imdbVotes= jsonObject.getString("imdbVotes") ;
            String type= jsonObject.getString("Type") ;
            String dvd= jsonObject.getString("DVD") ;
            String boxOffice= jsonObject.getString("BoxOffice") ;
            String production= jsonObject.getString("Production") ;
            String website= jsonObject.getString("Website") ;
            String totalseasons = jsonObject.getString("totalSeasons");

            // Obtem o JSONArray das notas
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
            if (titulo != null
            ) {
                textTitulo.setText("Title: " + titulo);
                textYear.setText("Year: " + ano);
                //imagePoster.setImageURI(Uri.parse(poster));
                textSource.setText("Source: " + source);
                textValue.setText("Value: " + value);
                textRated.setText("Rated: " + rated);
                textReleased.setText("Released: " + released);
                textRuntime.setText("Runtime: " + runtime);
                textGenre.setText("Genre: " + genre);
                textDirector.setText("Director: " + director);
                textWriters.setText("Writers: " + writers);
                textActors.setText("Actors: " + actors);
                textPlot.setText("Plot: " + plot);
                textLanguage.setText("Language: " + language);
                textCountry.setText("Country: " + country);
                textAwards.setText("Awards: " + awards);

                textMetascore.setText("Metascore: " + metascore);
                textImdbId.setText("Imdb ID: " + imdbId);
                textImdbRating.setText("Imdb Rating: " + imdbRating);
                textImdbVotes.setText("Imdb Votes: " + imdbVotes);
                textType.setText("Type: " + type);
                textDvd.setText("DVD: " + dvd);
                textboxOffice.setText("BoxOffice: " + boxOffice);
                textproduction.setText("Production: " + production);
                textwebsite.setText("Website: " + website);
                textTotalseasons.setText("Total Seasons: " + totalseasons);

            } else {
                // If none are found, update the UI to show failed results.
                textTitulo.setText(R.string.no_results);
                textYear.setText(R.string.str_empty);
                textSource.setText(R.string.str_empty);
                textValue.setText(R.string.str_empty);
                textRated.setText(R.string.str_empty);
                textReleased.setText(R.string.str_empty);
                textRuntime.setText(R.string.str_empty);
                textGenre.setText(R.string.str_empty);
                textDirector.setText(R.string.str_empty);
                textWriters.setText(R.string.str_empty);
                textActors.setText(R.string.str_empty);
                textPlot.setText(R.string.str_empty);
                textLanguage.setText(R.string.str_empty);
                textCountry.setText(R.string.str_empty);
                textAwards.setText(R.string.str_empty);
                textMetascore.setText(R.string.str_empty) ;
                textImdbId.setText(R.string.str_empty) ;
                textImdbRating.setText(R.string.str_empty) ;
                textImdbVotes.setText(R.string.str_empty) ;
                textType.setText(R.string.str_empty) ;
                textDvd.setText(R.string.str_empty) ;
                textboxOffice.setText(R.string.str_empty) ;
                textproduction.setText(R.string.str_empty) ;
                textwebsite.setText(R.string.str_empty) ;
                textTotalseasons.setText(R.string.str_empty);
            }
        } catch (Exception e) {
            // Se não receber um JSOn válido, informa ao usuário
            textTitulo.setText(R.string.no_results);
            textYear.setText(R.string.str_empty);
            textSource.setText(R.string.str_empty);
            textValue.setText(R.string.str_empty);
            textRated.setText(R.string.str_empty);
            textReleased.setText(R.string.str_empty);
            textRuntime.setText(R.string.str_empty);
            textGenre.setText(R.string.str_empty);
            textDirector.setText(R.string.str_empty);
            textWriters.setText(R.string.str_empty);
            textActors.setText(R.string.str_empty);
            textPlot.setText(R.string.str_empty);
            textLanguage.setText(R.string.str_empty);
            textCountry.setText(R.string.str_empty);
            textAwards.setText(R.string.str_empty);
            textMetascore.setText(R.string.str_empty) ;
            textImdbId.setText(R.string.str_empty) ;
            textImdbRating.setText(R.string.str_empty) ;
            textImdbVotes.setText(R.string.str_empty) ;
            textType.setText(R.string.str_empty) ;
            textDvd.setText(R.string.str_empty) ;
            textboxOffice.setText(R.string.str_empty) ;
            textproduction.setText(R.string.str_empty) ;
            textwebsite.setText(R.string.str_empty) ;
            textTotalseasons.setText(R.string.str_empty);
            e.printStackTrace();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada
    }

    private void FindComponents(){
        editSearch = findViewById(R.id.txt_search);
        textTitulo = findViewById(R.id.txt_titulo);
        textYear = findViewById(R.id.txt_ano);
        //imagePoster = findViewById(R.id.imgPoster);
        textSource = findViewById(R.id.txt_source);
        textValue = findViewById(R.id.txt_value);
        textRated = findViewById(R.id.txt_rated);
        textReleased = findViewById(R.id.txt_released);
        textRuntime = findViewById(R.id.txt_runtime);
        textGenre = findViewById(R.id.txt_genre);
        textDirector = findViewById(R.id.txt_director);
        textWriters = findViewById(R.id.txt_writer);
        textActors = findViewById(R.id.txt_actors);
        textPlot = findViewById(R.id.txt_plot);
        textLanguage = findViewById(R.id.txt_language);
        textCountry = findViewById(R.id.txt_country);
        textAwards = findViewById(R.id.txt_awards);
        textMetascore = findViewById(R.id.txt_metascore);
        textImdbId = findViewById(R.id.txt_imdbid);
        textImdbRating = findViewById(R.id.txt_imdbrating);
        textImdbVotes = findViewById(R.id.txt_imdbvotes);
        textType = findViewById(R.id.txt_type);
        textDvd = findViewById(R.id.txt_dvd);
        textboxOffice = findViewById(R.id.txt_boxoffice);
        textproduction = findViewById(R.id.txt_production);
        textwebsite = findViewById(R.id.txt_website);
        textTotalseasons = findViewById(R.id.txt_totalseasons);

    }
}