package com.example.glancesamples.step2_sizemode

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.LocalSize
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

/**
 * Step 2-2: SizeMode.Exact
 *
 * 학습 목표:
 * - SizeMode.Exact의 동작 방식 이해
 * - LocalSize.current로 정확한 위젯 크기 읽기
 * - 픽셀 단위로 변화하는 반응형 UI
 *
 * 특징:
 * ✅ 모든 크기에 세밀하게 대응 가능
 * ✅ LocalSize.current로 정확한 크기 확인
 * ❌ 크기 변경 시마다 재생성 발생 (성능 저하)
 * ❌ 모든 크기에 대응하는 로직을 직접 작성해야 함
 */
class Step2ExactSizeWidget : GlanceAppWidget() {

    /**
     * SizeMode.Exact: 정확한 위젯 크기 값을 그대로 제공
     * 크기가 1픽셀만 바뀌어도 provideContent가 다시 호출됨!
     */
    override val sizeMode: SizeMode = SizeMode.Exact

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            ExactSizeContent()
        }
    }
}

@Composable
fun ExactSizeContent() {
    // LocalSize.current로 현재 위젯의 정확한 크기를 가져옴
    val size = LocalSize.current

    // 크기에 따라 배경색 변경
    val backgroundColor = when {
        size.width < 150.dp -> Color(0xFFFFEBEE) // 빨강 계열
        size.width < 200.dp -> Color(0xFFE3F2FD) // 파랑 계열
        size.width < 250.dp -> Color(0xFFE8F5E9) // 초록 계열
        else -> Color(0xFFFFF9C4)                // 노랑 계열
    }

    val colorName = when {
        size.width < 150.dp -> "빨강"
        size.width < 200.dp -> "파랑"
        size.width < 250.dp -> "초록"
        else -> "노랑"
    }

    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = GlanceModifier.padding(16.dp)
        ) {
            Text(
                text = "🎯 SizeMode.Exact",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = androidx.glance.unit.ColorProvider(Color.DarkGray)
                )
            )

            Text(
                text = "정확한 크기: ${size.width.value.toInt()}dp × ${size.height.value.toInt()}dp",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = androidx.glance.unit.ColorProvider(Color.Black)
                ),
                modifier = GlanceModifier.padding(top = 8.dp)
            )

            Text(
                text = "현재 색상: $colorName",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = androidx.glance.unit.ColorProvider(Color.DarkGray)
                ),
                modifier = GlanceModifier.padding(top = 4.dp)
            )

            Text(
                text = "위젯 크기를 조절하면\n배경색이 실시간으로 변합니다!",
                style = TextStyle(
                    fontSize = 11.sp,
                    color = androidx.glance.unit.ColorProvider(Color.Gray)
                ),
                modifier = GlanceModifier.padding(top = 12.dp)
            )
        }
    }
}
