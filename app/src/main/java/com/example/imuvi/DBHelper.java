package com.example.imuvi;

import android.content.Context;
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
    public static final String COLUMN_DVD = "dvd";
    public static final String COLUMN_BOXOFFICE = "boxOffice";
    public static final String COLUMN_PRODUCTION = "production";
    public static final String COLUMN_WEBSITE = "webSite";



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
    private static final int DB_VERSAO = 2;

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
                + COLUMN_WRITER + " text not null,"
                + COLUMN_ACTORS + " text not null,"
                + COLUMN_PLOT + " text not null,"
                + COLUMN_LANGUAGE + " text not null,"
                + COLUMN_COUNTRY + " text not null,"
                + COLUMN_AWARDS + " text not null,"
                + COLUMN_POSTER + " text not null,"
                + COLUMN_METASCORE + " text not null,"
                + COLUMN_IMDBRATING + " text not null,"
                + COLUMN_IMDBVOTES + " text primary key, "
                + COLUMN_TYPE + " text not null,"
                + COLUMN_DVD + " text,"
                + COLUMN_BOXOFFICE + " text,"
                + COLUMN_PRODUCTION + " text,"
                + COLUMN_WEBSITE + " text );";

        String CREATE_TBL_RATINGS = "CREATE TABLE " + RATINGS_TABLE_NAME + "( "
                + COLUMN_ID_IMDBR + " text primary key, "
                + COLUMN_SOURCE + " text not null, "
                + COLUMN_VALUE + " text not null );";

        String CREATE_TBL_BESTFILM = "CREATE TABLE " + BESTFILM_TABLE_NAME + "( "
                + COLUMN_ID_BEST + " text primary key, "
                + COLUMN_ID_IMDBB + "text not null, "
                + COLUMN_TITLEB + "text not null, "
                + COLUMN_IMDBRATINGB + "text not null );";

        db.execSQL(CREATE_TBL_MOVIE);
        db.execSQL(CREATE_TBL_RATINGS);
        db.execSQL(CREATE_TBL_BESTFILM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String drop_table_movie = "DROP TABLE IF EXISTS " + MOVIE_TABLE_NAME;
        String drop_table_ratings = "DROP TABLE IF EXISTS " + RATINGS_TABLE_NAME;
        String drop_table_bests = "DROP TABLE IF EXISTS " + BESTFILM_TABLE_NAME;

        db.execSQL(drop_table_bests);
        db.execSQL(drop_table_ratings);
        db.execSQL(drop_table_movie);

        onCreate(db);
    }


}
