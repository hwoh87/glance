package com.example.glancesamples.step2_sizemode

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
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
 * Step 2-1: SizeMode.Single
 *
 * 학습 목표:
 * - SizeMode.Single의 동작 방식 이해
 * - 크기에 관계없이 하나의 레이아웃만 사용
 * - 가장 단순하고 성능이 좋은 모드
 *
 * 특징:
 * ✅ 가장 단순하고 성능이 좋음
 * ✅ LocalSize.current가 변하지 않음
 * ❌ 다양한 크기에 유연하게 대응 불가
 */
class Step2SingleSizeWidget : GlanceAppWidget() {

    /**
     * SizeMode.Single: 모든 크기에서 동일한 레이아웃 사용
     */
    override val sizeMode: SizeMode = SizeMode.Single

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            SingleSizeContent()
        }
    }
}

@Composable
fun SingleSizeContent() {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "📏 SizeMode.Single",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = androidx.glance.unit.ColorProvider(Color(0xFF1976D2))
            )
        )

        Text(
            text = "크기에 관계없이",
            style = TextStyle(
                fontSize = 14.sp,
                color = androidx.glance.unit.ColorProvider(Color.DarkGray)
            ),
            modifier = GlanceModifier.padding(top = 8.dp)
        )

        Text(
            text = "동일한 레이아웃",
            style = TextStyle(
                fontSize = 14.sp,
                color = androidx.glance.unit.ColorProvider(Color.DarkGray)
            ),
            modifier = GlanceModifier.padding(top = 4.dp)
        )

        Text(
            text = "위젯 크기를 변경해보세요!",
            style = TextStyle(
                fontSize = 12.sp,
                color = androidx.glance.unit.ColorProvider(Color.Gray)
            ),
            modifier = GlanceModifier.padding(top = 12.dp)
        )
    }
}
