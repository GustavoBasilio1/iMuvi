package com.example.imuvi;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class LauncherWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
       for(int appWidgetId : appWidgetIds){
           Intent intent = new Intent(context, Splash.class);
           PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
           RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.launcher_widget);
           views.setOnClickPendingIntent(R.id.button_launcher, pendingIntent);
           appWidgetManager.updateAppWidget(appWidgetId, views);
       }
    }
}
