package com.example.imuvi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    //movie
    public static final String MOVIE_TABLE_NAME = "tbl_movie";

    public static final String COLUMN_ID_IMDB = "id_IMDB";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_RATED = "rated";
    public static final String COLUMN_RELEASED = "released";
    public static final String COLUMN_RUNTIME = "runtime";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_DIRECTOR = "director";
    public static final String COLUMN_POSTER = "poster";





    //ratings
    public static final String RATINGS_TABLE_NAME = "tbl_ratings";

    public static final String COLUMN_ID_IMDBR = "id_IMDB";
    public static final String COLUMN_SOURCE = "source";
    public static final String COLUMN_VALUE = "value";




    //bestFilm
    public static final String BESTFILM_TABLE_NAME = "tbl_bestFilm";

    public static final String COLUMN_ID_BEST = "id_best";
    public static final String COLUMN_ID_IMDBB = "id_IMDB";
    public static final String COLUMN_TITLEB = "title";
    public static final String COLUMN_IMDBRATINGB = "imdbRating";



    //nome e versão do db
    private static final String DB_NAME="DBIMuvi.db";
    private static final int DB_VERSAO = 3;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TBL_MOVIE = "CREATE TABLE " + MOVIE_TABLE_NAME + "( "
                + COLUMN_ID_IMDB + " text primary key, "
                + COLUMN_TITLE + " text not null,"
                + COLUMN_YEAR + " text not null,"
                + COLUMN_RATED + " text not null,"
                + COLUMN_RELEASED + " text not null,"
                + COLUMN_RUNTIME + " text not null,"
                + COLUMN_GENRE + "  text not null, "
                + COLUMN_DIRECTOR + " text not null,"
                + COLUMN_POSTER + " text not null);";

        String CREATE_TBL_BESTFILM = "CREATE TABLE " + BESTFILM_TABLE_NAME + "( "
                + COLUMN_ID_BEST + " text primary key, "
                + COLUMN_ID_IMDBB + "text not null, "
                + COLUMN_TITLEB + "text not null )";

        db.execSQL(CREATE_TBL_MOVIE);
        db.execSQL(CREATE_TBL_BESTFILM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String drop_table_movie = "DROP TABLE IF EXISTS " + MOVIE_TABLE_NAME + ";";
        String drop_table_bests = "DROP TABLE IF EXISTS " + BESTFILM_TABLE_NAME + ";";

        db.execSQL(drop_table_bests);
        db.execSQL(drop_table_movie);

        onCreate(db);
    }

    public String addMovie(Movie movie){

        //INSERINDO DADOS NA TABELA MOVIE

        long resultado;

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(COLUMN_ID_IMDB, movie.getImdbId());
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_YEAR, movie.getYear());
        values.put(COLUMN_RATED, movie.getRated());

        values.put(COLUMN_RELEASED, movie.getReleased());
        values.put(COLUMN_RUNTIME, movie.getRuntime());
        values.put(COLUMN_GENRE, movie.getGenre());
        values.put(COLUMN_DIRECTOR, movie.getDirector());
        values.put(COLUMN_POSTER, movie.getPoster());


        resultado = db.insert(MOVIE_TABLE_NAME, null, values);

        //FECHANDO O BANCO
        db.close();

        //RETORNANDO MENSAGENS
        if (resultado ==-1) {
            return "Erro ao inserir os dados de Filmes";
        }else{
            return "Dados de Filmes Inseridos com sucesso";
        }


    }

    public String addRatings(Ratings ratings){
        SQLiteDatabase db=this.getWritableDatabase();



        //INSERINDO DADOS NA TABELA RATINGS
        long resultadoR;
        ContentValues valuesR= new ContentValues();

        valuesR.put(COLUMN_ID_IMDBR, ratings.getIdimdbr());
        valuesR.put(COLUMN_SOURCE, ratings.getSource());
        valuesR.put(COLUMN_VALUE, ratings.getValue());

        resultadoR = db.insert(RATINGS_TABLE_NAME, null, valuesR);

        //FECHANDO O BANCO
        db.close();

        //RETORNANDO MENSAGENS
        if (resultadoR ==-1) {
            return "Erro ao inserir os dados de Avaliações";
        }else{
            return "Dados de Avaliações Inseridos com sucesso";
        }


    }

    Movie buscaMovie(String idimdb){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(MOVIE_TABLE_NAME,
                new String[]{COLUMN_ID_IMDB, COLUMN_TITLE, COLUMN_YEAR, COLUMN_RATED, COLUMN_RELEASED, COLUMN_RUNTIME,
                        COLUMN_GENRE, COLUMN_DIRECTOR,  COLUMN_POSTER},
                COLUMN_ID_IMDB+"=?",new String[]{String.valueOf(idimdb)},null, null, null,null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
        }

        else if(cursor.getCount() == 0){
            Movie filmeEspecifico = new Movie(
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists",
                    "notExists"
            );
            return filmeEspecifico;
        }


        Movie filmeEspecifico= new Movie(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getString(9),
                cursor.getString(10),
                cursor.getString(11),
                cursor.getString(12),
                cursor.getString(13),
                cursor.getString(14),
                cursor.getString(15),
                cursor.getString(16),
                cursor.getString(17),
                cursor.getString(18),
                cursor.getString(19),
                cursor.getString(20),
                cursor.getString(21),
                cursor.getString(22)
        );
        return filmeEspecifico;

    }

    Ratings buscaRatings(String idimdbr){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(RATINGS_TABLE_NAME,
                new String[]{COLUMN_ID_IMDBR, COLUMN_SOURCE, COLUMN_VALUE},
                COLUMN_ID_IMDBR+"=?",new String[]{String.valueOf(idimdbr)},null, null, null,null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
        }

        else if(cursor.getCount() == 0){
            Ratings ratingEspecifico = new Ratings(
                    "notExists",
                    "notExists",
                    "notExists");
            return ratingEspecifico;
        }


        Ratings ratingEspecifico= new Ratings(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2));
        return ratingEspecifico;

    }

}
