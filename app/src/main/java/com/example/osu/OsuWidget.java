package com.example.osu;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.TextView;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link OsuWidgetConfigureActivity OsuWidgetConfigureActivity}
 */
public class OsuWidget extends AppWidgetProvider {

    private TextView txt1;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            RemoteViews view = new RemoteViews(context.getPackageName(), R.id.appwidget_text);
            //view.setTextViewText();
        }
    }

}