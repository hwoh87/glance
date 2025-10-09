package com.example.glancesamples.step5_practical

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

/**
 * Step 5: 실전 Todo 위젯
 *
 * 학습 목표:
 * - State, Action, UI를 통합한 실전 예제
 * - 리스트 UI 구현
 * - ActionParameters로 항목 구분
 * - 완전한 CRUD 기능 구현
 *
 * 기능:
 * - Todo 항목 추가
 * - Todo 항목 완료 (삭제)
 * - 인스턴스별 독립적인 Todo 리스트
 */
class Step5TodoWidget : GlanceAppWidget() {
    override val stateDefinition = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            TodoContent()
        }
    }
}

@Composable
fun TodoContent() {
    val prefs = currentState<Preferences>()
    val todosString = prefs[stringPreferencesKey("todos")] ?: ""
    val todos = if (todosString.isBlank()) emptyList() else todosString.split("|")

    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // 헤더
        Text(
            text = "📝 Todo List",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = androidx.glance.unit.ColorProvider(Color(0xFF1976D2))
            )
        )

        Text(
            text = "${todos.size}개의 할 일",
            style = TextStyle(
                fontSize = 12.sp,
                color = androidx.glance.unit.ColorProvider(Color.Gray)
            )
        )

        Spacer(GlanceModifier.height(12.dp))

        // Todo 리스트
        if (todos.isEmpty()) {
            Text(
                text = "할 일이 없습니다!\n'추가' 버튼을 눌러보세요.",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = androidx.glance.unit.ColorProvider(Color.Gray)
                ),
                modifier = GlanceModifier.padding(vertical = 20.dp)
            )
        } else {
            todos.forEach { todo ->
                TodoItem(todo)
                Spacer(GlanceModifier.height(4.dp))
            }
        }

        Spacer(GlanceModifier.height(12.dp))

        // 추가 버튼들
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                text = "➕ 추가",
                onClick = actionRunCallback<AddTodoAction>(
                    parameters = actionParametersOf(todoTextKey to "새 할 일 ${todos.size + 1}")
                )
            )
        }
    }
}

@Composable
fun TodoItem(todo: String) {
    Row(
        modifier = GlanceModifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "• $todo",
            style = TextStyle(
                fontSize = 14.sp,
                color = androidx.glance.unit.ColorProvider(Color.Black)
            ),
            modifier = GlanceModifier.defaultWeight()
        )

        Button(
            text = "✓",
            onClick = actionRunCallback<CompleteTodoAction>(
                parameters = actionParametersOf(todoTextKey to todo)
            )
        )
    }
}

val todoTextKey = ActionParameters.Key<String>("todo_text")

class AddTodoAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        val newTodo = parameters[todoTextKey] ?: "새 할 일"

        updateAppWidgetState(context, glanceId) { prefs ->
            val current = prefs[stringPreferencesKey("todos")] ?: ""
            val todos = if (current.isBlank()) mutableListOf() else current.split("|").toMutableList()
            todos.add(newTodo)
            prefs[stringPreferencesKey("todos")] = todos.joinToString("|")
        }

        Step5TodoWidget().update(context, glanceId)
    }
}

class CompleteTodoAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        val todoToComplete = parameters[todoTextKey] ?: return

        updateAppWidgetState(context, glanceId) { prefs ->
            val current = prefs[stringPreferencesKey("todos")] ?: ""
            val todos = if (current.isBlank()) mutableListOf() else current.split("|").toMutableList()
            todos.remove(todoToComplete)
            prefs[stringPreferencesKey("todos")] = if (todos.isEmpty()) "" else todos.joinToString("|")
        }

        Step5TodoWidget().update(context, glanceId)
    }
}
