package com.example.glancesamples.step4_action

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.core.net.toUri

/**
 * Step 4: Action 처리 샘플
 *
 * 학습 목표:
 * - actionRunCallback: 위젯 내부 로직 실행
 * - actionStartActivity: 액티비티 실행
 * - ActionParameters: 파라미터 전달
 * - 다양한 Action 패턴 학습
 */
class Step4ActionWidget : GlanceAppWidget() {
    override val stateDefinition = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Step4Content(context)
        }
    }
}

@Composable
fun Step4Content(context: Context) {
    val prefs = currentState<Preferences>()
    val clickCount = prefs[intPreferencesKey("click_count")] ?: 0

    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color(0xFFF3E5F5))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "⚡ Action Handling",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = androidx.glance.unit.ColorProvider(Color(0xFF6A1B9A))
            )
        )

        Spacer(GlanceModifier.height(12.dp))

        Text(
            text = "클릭 횟수: $clickCount",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = androidx.glance.unit.ColorProvider(Color(0xFF8E24AA))
            )
        )

        Spacer(GlanceModifier.height(16.dp))

        // 1. actionRunCallback: 위젯 내부 로직
        Button(
            text = "📝 내부 로직 실행",
            onClick = actionRunCallback<IncrementClickAction>()
        )

        Spacer(GlanceModifier.height(8.dp))

        // 2. actionRunCallback with Parameters
        Button(
            text = "➕ +5 증가",
            onClick = actionRunCallback<AddValueAction>(
                parameters = actionParametersOf(valueKey to 5)
            )
        )

        Spacer(GlanceModifier.height(8.dp))

        // 3. actionStartActivity: 웹 브라우저 열기
        Button(
            text = "🌐 웹사이트 열기",
            onClick = actionStartActivity(
                Intent(Intent.ACTION_VIEW,
                    "https://developer.android.com/jetpack/compose/glance".toUri())
            )
        )

        Spacer(GlanceModifier.height(12.dp))

        Text(
            text = "다양한 Action 패턴을\n테스트해보세요!",
            style = TextStyle(
                fontSize = 10.sp,
                color = androidx.glance.unit.ColorProvider(Color.DarkGray)
            )
        )
    }
}

// ActionParameters Key
val valueKey = ActionParameters.Key<Int>("value")

/**
 * 클릭 횟수 증가 액션
 */
class IncrementClickAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(context, glanceId) { prefs ->
            val current = prefs[intPreferencesKey("click_count")] ?: 0
            prefs[intPreferencesKey("click_count")] = current + 1
        }
        Step4ActionWidget().update(context, glanceId)
    }
}

/**
 * 특정 값만큼 증가하는 액션 (파라미터 사용)
 */
class AddValueAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        val value = parameters[valueKey] ?: 1

        updateAppWidgetState(context, glanceId) { prefs ->
            val current = prefs[intPreferencesKey("click_count")] ?: 0
            prefs[intPreferencesKey("click_count")] = current + value
        }
        Step4ActionWidget().update(context, glanceId)
    }
}
