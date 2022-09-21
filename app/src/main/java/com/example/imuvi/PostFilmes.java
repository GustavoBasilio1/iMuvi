package com.example.imuvi;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class PostFilmes extends AsyncTaskLoader<String> {
    private String tFilmes;
    PostFilmes(Context context, String character) {
        super(context);
        tFilmes = character;

    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    @Nullable
    @Override
    public String loadInBackground() {

        return Conexao.postFilmes(tFilmes);
    }
}
