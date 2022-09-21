package com.example.imuvi;

import android.net.Uri;
import android.util.JsonReader;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String FILMES_URL = "https://localhost:44356/api/Filme/Get/";
    private static final String QUERY_PARAM = "t";
    private static final String TIPO_FILME = "type";



    //GET
    static String buscaInfosFilme(String queryString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;
        try {
            // Construção da URI de Busca
            Uri builtURI = Uri.parse(FILMES_URL).buildUpon()
                    //.appendQueryParameter(QUERY_PARAM, queryString)
                    //.appendQueryParameter(TIPO_FILME, "movie")
                    .build();
            // Converte a URI para a URL.
            URL requestURL = new URL(builtURI.toString());
            // Abre a conexão de rede
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Busca o InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Cria o buffer para o input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));
            // Usa o StringBuilder para receber a resposta.
            StringBuilder builder = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Adiciona a linha a string.
                builder.append(linha);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // se o stream estiver vazio não faz nada
                return null;
            }
            bookJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // fecha a conexão e o buffer.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // escreve o Json no log
        Log.d(LOG_TAG, bookJSONString);
        return bookJSONString;
    }



    //POST
    static void postFilme(Movie movie){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String characterJSONString = null;
        try {
            Uri builtURI;
            if(movie == null){
                builtURI = Uri.parse(FILMES_URL).buildUpon()
                        .build();
            }
            else {
                String url1 = FILMES_URL;
                //Construção da URI de Busca
                builtURI = Uri.parse(url1).buildUpon()
                        .build();
            }

            // Busca o InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Cria o buffer para o input stream
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, "UTF-8");

            JsonReader jsonReader = new JsonReader(inputStreamReader);
            // Começando o processo do objeto JSON
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String url = jsonReader.nextName();
                if (url.equals("URL DA API")) {
                    String movieAdd = gson.toJson(filmes);

                    movieAdd = jsonReader.nextString();
                    // Saindo do loop
                    break;
                } else {
                    // Ignorar valores de outras chaves
                    jsonReader.skipValue();
                }
            }
            // Converte a URI para a URL.
            URL requestURL = new URL(builtURI.toString());
            // Abre a conexão de rede
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // fecha a conexão e o buffer.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    //UPDATE
    static void updateMovies(Movie movie){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String movieJSONString = null;
        try {
            Uri builtURI;
            if(movie == null){
                builtURI = Uri.parse(FILMES_URL).buildUpon()
                        .build();
            }
            else {
                String url1 = FILMES_URL;
                //Construção da URI de Busca
                builtURI = Uri.parse(url1).buildUpon()
                        .build();
            }

            // Busca o InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Cria o buffer para o input stream
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, "UTF-8");
            JsonReader jsonReader = new JsonReader(inputStreamReader);
            jsonReader.beginObject(); // Começando o processo do objeto JSON
            while (jsonReader.hasNext()) {
                String url = jsonReader.nextName();
                if (url.equals("URL DA API")) {
                    Gson gson = new Gson();
                    String movieUpdate = gson.toJson(movie);
                    movieUpdate = jsonReader.nextString();

                    break; // Saindo do loop
                } else {
                    jsonReader.skipValue(); // Ignorar valores de outras chaves
                }
            }
            // Converte a URI para a URL.
            URL requestURL = new URL(builtURI.toString());
            // Abre a conexão de rede
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("SET");
            urlConnection.connect();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // fecha a conexão e o buffer.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    //DELETE
    static void deleteFilmes(String movie){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String movieJSONString = null;

        try {
            Uri builtURI;
            if(movie == null){
                builtURI = Uri.parse(FILMES_URL).buildUpon()
                        .build();
            }
            else {
                String url1 = FILMES_URL;
                //Construção da URI de Busca
                builtURI = Uri.parse(url1).buildUpon()
                        .build();
            }
            // Busca o InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Cria o buffer para o input stream
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, "UTF-8");

            JsonReader jsonReader = new JsonReader(inputStreamReader);
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String url = jsonReader.nextName();
                if (url.equals("URL DA API")) {
                    Gson gson = new Gson();
                    String movieUpdate = gson.toJson(movie);
                    movieUpdate = jsonReader.nextString();


                    break; // Saindo do loop
                } else {
                    jsonReader.skipValue();
                }
            }
            // Converte a URI para a URL.
            URL requestURL = new URL(builtURI.toString());
            // Abre a conexão de rede
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("DELETE");
            urlConnection.connect();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // fecha a conexão e o buffer.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}