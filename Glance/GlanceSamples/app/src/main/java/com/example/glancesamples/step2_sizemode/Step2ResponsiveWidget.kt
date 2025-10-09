package com.example.glancesamples.step2_sizemode

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
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
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

/**
 * Step 2-3: SizeMode.Responsive (권장)
 *
 * 학습 목표:
 * - SizeMode.Responsive의 동작 방식 이해
 * - 브레이크포인트(버킷) 개념 학습
 * - 성능과 유연성의 균형
 *
 * 특징:
 * ✅ 성능과 유연성의 균형
 * ✅ 정의된 브레이크포인트에서만 재생성
 * ✅ 대부분의 위젯에 권장되는 방식
 * ⚠️ Android 12+ 권장 (API 31+)
 *
 * 동작 원리:
 * 1. 미리 정의한 크기들(버킷)에 대해 RemoteViews를 각각 생성
 * 2. 런타임에 시스템이 현재 크기와 가장 가까운 버킷 선택
 * 3. 버킷 경계를 넘을 때만 다른 레이아웃으로 전환
 */
class Step2ResponsiveWidget : GlanceAppWidget() {

    /**
     * SizeMode.Responsive: 미리 정의한 크기 버킷 사용
     *
     * 버킷 정의:
     * - 180dp × 80dp: 작은 위젯 (2x1 칸 정도)
     * - 250dp × 120dp: 중간 위젯 (3x2 칸 정도)
     * - 320dp × 180dp: 큰 위젯 (4x3 칸 정도)
     */
    override val sizeMode: SizeMode = SizeMode.Responsive(
        setOf(
            DpSize(180.dp, 80.dp),   // 작은 버킷
            DpSize(250.dp, 120.dp),  // 중간 버킷
            DpSize(320.dp, 180.dp)   // 큰 버킷
        )
    )

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            ResponsiveSizeContent()
        }
    }
}

@Composable
fun ResponsiveSizeContent() {
    val size = LocalSize.current

    // 현재 크기에 따라 다른 UI 표시
    when {
        size.width < 220.dp -> CompactUI()
        size.width < 280.dp -> MediumUI()
        else -> LargeUI()
    }
}

/**
 * 작은 크기 UI (180dp 버킷)
 */
@Composable
fun CompactUI() {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color(0xFFFFEBEE))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "📱 Compact",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = androidx.glance.unit.ColorProvider(Color(0xFFC62828))
            )
        )

        Text(
            text = "작은 위젯",
            style = TextStyle(
                fontSize = 12.sp,
                color = androidx.glance.unit.ColorProvider(Color.DarkGray)
            ),
            modifier = GlanceModifier.padding(top = 4.dp)
        )

        val size = LocalSize.current
        Text(
            text = "${size.width.value.toInt()}dp",
            style = TextStyle(
                fontSize = 10.sp,
                color = androidx.glance.unit.ColorProvider(Color.Gray)
            ),
            modifier = GlanceModifier.padding(top = 4.dp)
        )
    }
}

/**
 * 중간 크기 UI (250dp 버킷)
 */
@Composable
fun MediumUI() {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "📐 Medium",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = androidx.glance.unit.ColorProvider(Color(0xFF1565C0))
            )
        )

        Text(
            text = "중간 위젯",
            style = TextStyle(
                fontSize = 14.sp,
                color = androidx.glance.unit.ColorProvider(Color.DarkGray)
            ),
            modifier = GlanceModifier.padding(top = 6.dp)
        )

        val size = LocalSize.current
        Text(
            text = "크기: ${size.width.value.toInt()}dp × ${size.height.value.toInt()}dp",
            style = TextStyle(
                fontSize = 12.sp,
                color = androidx.glance.unit.ColorProvider(Color.Gray)
            ),
            modifier = GlanceModifier.padding(top = 4.dp)
        )

        Text(
            text = "중간 크기에 적합한 정보량",
            style = TextStyle(
                fontSize = 11.sp,
                color = androidx.glance.unit.ColorProvider(Color.Gray)
            ),
            modifier = GlanceModifier.padding(top = 8.dp)
        )
    }
}

/**
 * 큰 크기 UI (320dp 버킷)
 */
@Composable
fun LargeUI() {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color(0xFFE8F5E9))
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🖥️ Large",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = androidx.glance.unit.ColorProvider(Color(0xFF2E7D32))
            )
        )

        Text(
            text = "큰 위젯",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = androidx.glance.unit.ColorProvider(Color.DarkGray)
            ),
            modifier = GlanceModifier.padding(top = 8.dp)
        )

        val size = LocalSize.current
        Text(
            text = "크기: ${size.width.value.toInt()}dp × ${size.height.value.toInt()}dp",
            style = TextStyle(
                fontSize = 14.sp,
                color = androidx.glance.unit.ColorProvider(Color.Gray)
            ),
            modifier = GlanceModifier.padding(top = 6.dp)
        )

        Text(
            text = "큰 화면에 많은 정보를 표시할 수 있습니다",
            style = TextStyle(
                fontSize = 12.sp,
                color = androidx.glance.unit.ColorProvider(Color.Gray)
            ),
            modifier = GlanceModifier.padding(top = 8.dp)
        )

        Text(
            text = "✨ Responsive 모드 권장!",
            style = TextStyle(
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = androidx.glance.unit.ColorProvider(Color(0xFF388E3C))
            ),
            modifier = GlanceModifier.padding(top = 12.dp)
        )
    }
}
