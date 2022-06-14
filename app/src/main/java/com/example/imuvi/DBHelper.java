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
    public static final String COLUMN_ID_INCREMENT = "id_INCREMENT";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_RATED = "rated";
    public static final String COLUMN_RELEASED = "released";
    public static final String COLUMN_RUNTIME = "runtime";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_DIRECTOR = "director";
    public static final String COLUMN_WRITER = "writer";
    public static final String COLUMN_ACTORS = "actors";
    public static final String COLUMN_PLOT = "plot";
    public static final String COLUMN_LANGUAGE = "language";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_AWARDS = "awards";
    public static final String COLUMN_POSTER = "poster";
    public static final String COLUMN_METASCORE = "metaScore";
    public static final String COLUMN_IMDBRATING = "imdbRating";
    public static final String COLUMN_IMDBVOTES = "imdbVotes";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_TOTALSEASONS = "totalSeasons";
    public static final String COLUMN_DVD = "dvd";
    public static final String COLUMN_BOXOFFICE = "boxOffice";
    public static final String COLUMN_PRODUCTION = "production";
    public static final String COLUMN_WEBSITE = "webSite";
    public static final String COLUMN_RESPONSE = "response";


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
    private static final int DB_VERSAO = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TBL_MOVIE = "CREATE TABLE " + MOVIE_TABLE_NAME + "( "
                + COLUMN_ID_IMDB + " integer primary key, "
                + COLUMN_ID_INCREMENT + " integer not null,"
                + COLUMN_TITLE + " text not null,"
                + COLUMN_YEAR + " Date not null,"
                + COLUMN_RATED + " text not null,"
                + COLUMN_RELEASED + " date not null,"
                + COLUMN_RUNTIME + " text not null,"
                + COLUMN_GENRE + "  text not null, "
                + COLUMN_DIRECTOR + " text not null,"
                + COLUMN_WRITER + " text not null,"
                + COLUMN_ACTORS + " text not null,"
                + COLUMN_PLOT + " text not null,"
                + COLUMN_LANGUAGE + " text not null,"
                + COLUMN_COUNTRY + " text not null,"
                + COLUMN_AWARDS + " text not null,"
                + COLUMN_POSTER + " text not null,"
                + COLUMN_METASCORE + " int not null,"
                + COLUMN_IMDBRATING + " decimal not null,"
                + COLUMN_IMDBVOTES + " int primary key, "
                + COLUMN_TYPE + " text not null,"
                + COLUMN_TOTALSEASONS + " int,"
                + COLUMN_DVD + " Date,"
                + COLUMN_BOXOFFICE + " text,"
                + COLUMN_PRODUCTION + " text not null,"
                + COLUMN_WEBSITE + " text );";

        String CREATE_TBL_RATINGS = "CREATE TABLE " + RATINGS_TABLE_NAME + "( "
                + COLUMN_ID_IMDBR + " integer primary key, "
                + COLUMN_SOURCE + " text not null, "
                + COLUMN_VALUE + " text not null );";

        String CREATE_TBL_BESTFILM = "CREATE TABLE " + BESTFILM_TABLE_NAME + "( "
                + COLUMN_ID_BEST + " integer primary key, "
                + COLUMN_ID_IMDBB + "integer not null, "
                + COLUMN_TITLEB + "text not null, "
                + COLUMN_IMDBRATINGB + "decimal not null );";

        db.execSQL(CREATE_TBL_MOVIE);
        db.execSQL(CREATE_TBL_RATINGS);
        db.execSQL(CREATE_TBL_BESTFILM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public String addLivro(Movie movie){
        long resultado;
        //estancia para escrita no banco
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(COLUMN_ID_IMDB, movie.getImdbId());
        values.put(COLUMN_TITLE, movie.getTitle());


        //inseri no banco
        resultado = db.insert(MOVIE_TABLE_NAME, null, values);
        db.close();

        if (resultado ==-1) {
            return "Erro ao inserir registro";
        }else{
            return "Registro Inserido com sucesso";
        }
    }

    Movie buscaLivro(String id){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(MOVIE_TABLE_NAME,
                new String[]{COLUMN_ID_IMDB, COLUMN_TITLE},
                COLUMN_ID_IMDB+"=?",new String[]{String.valueOf(id)},null, null, null,null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
        }

        else if(cursor.getCount() == 0){
            Movie filmeEspecifico = new Movie(
                    "naoExiste",
                    "naoExiste");
            return filmeEspecifico;
        }


        Movie filmeEspecifico= new Movie(
                cursor.getString(0),
                cursor.getString(1));
        return filmeEspecifico;

    }


}
