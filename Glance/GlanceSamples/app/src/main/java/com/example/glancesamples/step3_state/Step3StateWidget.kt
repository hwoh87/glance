package com.example.glancesamples.step3_state

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
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

/**
 * Step 3: State 관리 샘플
 *
 * 학습 목표:
 * - PreferencesGlanceStateDefinition 사용법
 * - currentState()로 상태 읽기
 * - updateAppWidgetState()로 상태 쓰기
 * - 인스턴스별 독립적인 상태 관리
 *
 * 핵심 개념:
 * - StateDefinition: 상태를 어떻게 저장할지 정의 (Preferences, Proto, Custom)
 * - GlanceId: 각 위젯 인스턴스를 구분하는 고유 ID
 * - DataStore: 비동기적이고 안전한 데이터 저장소
 */
class Step3StateWidget : GlanceAppWidget() {

    /**
     * StateDefinition 지정
     * PreferencesGlanceStateDefinition: 간단한 key-value 데이터에 적합
     */
    override val stateDefinition = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Step3Content()
        }
    }
}

@Composable
fun Step3Content() {
    // 현재 상태 읽기
    val prefs = currentState<Preferences>()
    val count = prefs[intPreferencesKey("count")] ?: 0
    val lastAction = prefs[stringPreferencesKey("last_action")] ?: "아직 없음"

    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🗂️ State Management",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = androidx.glance.unit.ColorProvider(Color(0xFFE65100))
            )
        )

        Spacer(GlanceModifier.height(16.dp))

        // 카운트 표시
        Text(
            text = "카운트: $count",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = androidx.glance.unit.ColorProvider(Color(0xFFFF6F00))
            )
        )

        Spacer(GlanceModifier.height(8.dp))

        // 마지막 액션 표시
        Text(
            text = "마지막 액션: $lastAction",
            style = TextStyle(
                fontSize = 12.sp,
                color = androidx.glance.unit.ColorProvider(Color.Gray)
            )
        )

        Spacer(GlanceModifier.height(20.dp))

        // 증가 버튼
        Button(
            text = "➕ 증가",
            onClick = actionRunCallback<IncrementAction>(),
            modifier = GlanceModifier.padding(vertical = 4.dp)
        )

        // 감소 버튼
        Button(
            text = "➖ 감소",
            onClick = actionRunCallback<DecrementAction>(),
            modifier = GlanceModifier.padding(vertical = 4.dp)
        )

        // 리셋 버튼
        Button(
            text = "🔄 리셋",
            onClick = actionRunCallback<ResetAction>(),
            modifier = GlanceModifier.padding(vertical = 4.dp)
        )

        Spacer(GlanceModifier.height(12.dp))

        Text(
            text = "같은 위젯을 여러 개 추가하면\n각각 다른 상태를 가집니다!",
            style = TextStyle(
                fontSize = 10.sp,
                color = androidx.glance.unit.ColorProvider(Color.DarkGray)
            )
        )
    }
}
