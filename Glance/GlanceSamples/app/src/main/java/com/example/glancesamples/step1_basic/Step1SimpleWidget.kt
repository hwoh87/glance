package com.example.glancesamples.step1_basic

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

/**
 * Step 1: 가장 기본적인 Glance 위젯
 *
 * 학습 목표:
 * - GlanceAppWidget 클래스 이해
 * - provideGlance와 provideContent의 역할
 * - Compose 스타일 선언형 UI 작성
 * - 기본 레이아웃 (Column, Text) 사용법
 */
class Step1SimpleWidget : GlanceAppWidget() {

    /**
     * 위젯 UI를 정의하는 핵심 메서드
     * provideContent 블록 안에 Compose 스타일로 UI를 선언합니다.
     */
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Step1Content()
        }
    }
}

/**
 * 위젯 UI 컴포저블
 *
 * Column: 수직 레이아웃 (세로로 쌓기)
 * GlanceModifier: Glance 전용 Modifier (androidx.compose.ui.Modifier와 다름!)
 * Text: 텍스트 표시
 */
@Composable
fun Step1Content() {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "✨ Step 1: Basic Widget",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = androidx.glance.unit.ColorProvider(Color(0xFF6200EE))
            )
        )

        Text(
            text = "Hello, Glance!",
            style = TextStyle(
                fontSize = 16.sp,
                color = androidx.glance.unit.ColorProvider(Color.DarkGray)
            ),
            modifier = GlanceModifier.padding(top = 8.dp)
        )

        Text(
            text = "가장 기본적인 위젯입니다",
            style = TextStyle(
                fontSize = 14.sp,
                color = androidx.glance.unit.ColorProvider(Color.Gray)
            ),
            modifier = GlanceModifier.padding(top = 4.dp)
        )
    }
}
