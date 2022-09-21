package com.example.imuvi;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class UpdateFilmes extends AsyncTaskLoader<String> {
    private String tFilmes;
    UpdateFilmes(Context context, String character) {
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

        return Conexao.updateFilmes(tFilmes);
    }
}
