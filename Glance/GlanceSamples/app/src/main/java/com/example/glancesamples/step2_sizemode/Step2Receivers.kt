package com.example.glancesamples.step2_sizemode

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

/**
 * Step 2-1: Single Size Widget Receiver
 */
class Step2SingleSizeReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = Step2SingleSizeWidget()
}

/**
 * Step 2-2: Exact Size Widget Receiver
 */
class Step2ExactSizeReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = Step2ExactSizeWidget()
}

/**
 * Step 2-3: Responsive Widget Receiver
 */
class Step2ResponsiveReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = Step2ResponsiveWidget()
}
