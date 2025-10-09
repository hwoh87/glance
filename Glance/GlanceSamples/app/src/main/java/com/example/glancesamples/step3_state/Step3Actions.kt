package com.example.glancesamples.step3_state

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.state.updateAppWidgetState

/**
 * Step 3: ActionCallback을 통한 상태 변경
 *
 * 학습 목표:
 * - ActionCallback 인터페이스 구현
 * - updateAppWidgetState()로 상태 변경
 * - update()로 UI 갱신
 *
 * 상태 변경 흐름:
 * 1. 사용자가 버튼 클릭
 * 2. ActionCallback.onAction() 호출
 * 3. updateAppWidgetState()로 상태 변경
 * 4. widget.update()로 UI 갱신
 * 5. provideContent가 다시 실행되어 새 상태로 UI 렌더링
 */

/**
 * 증가 액션
 * 카운트를 1 증가시킵니다
 */
class IncrementAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        // 1. 상태 변경
        updateAppWidgetState(context, glanceId) { prefs ->
            val current = prefs[intPreferencesKey("count")] ?: 0
            prefs[intPreferencesKey("count")] = current + 1
            prefs[stringPreferencesKey("last_action")] = "증가 (+1)"
        }

        // 2. UI 갱신 (이 단계를 빼먹으면 화면이 안 바뀝니다!)
        Step3StateWidget().update(context, glanceId)
    }
}

/**
 * 감소 액션
 * 카운트를 1 감소시킵니다
 */
class DecrementAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(context, glanceId) { prefs ->
            val current = prefs[intPreferencesKey("count")] ?: 0
            prefs[intPreferencesKey("count")] = current - 1
            prefs[stringPreferencesKey("last_action")] = "감소 (-1)"
        }

        Step3StateWidget().update(context, glanceId)
    }
}

/**
 * 리셋 액션
 * 카운트를 0으로 초기화합니다
 */
class ResetAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(context, glanceId) { prefs ->
            // clear()로 모든 상태 제거 후 기본값 설정
            prefs.clear()
            prefs[intPreferencesKey("count")] = 0
            prefs[stringPreferencesKey("last_action")] = "리셋 (0으로 초기화)"
        }

        Step3StateWidget().update(context, glanceId)
    }
}
