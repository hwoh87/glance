# Glance
-keep class androidx.glance.** { *; }
-dontwarn androidx.glance.**

# DataStore
-keep class androidx.datastore.** { *; }
-dontwarn androidx.datastore.**

# WorkManager
-keep class * extends androidx.work.Worker
-keep class * extends androidx.work.CoroutineWorker

# ActionCallback
-keep class * implements androidx.glance.action.ActionCallback {
    public <init>();
}

# GlanceAppWidget
-keep class * extends androidx.glance.appwidget.GlanceAppWidget {
    public <init>();
}

# GlanceAppWidgetReceiver
-keep class * extends androidx.glance.appwidget.GlanceAppWidgetReceiver {
    public <init>();
}
