package com.example.glancesamples

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.view.Gravity
import android.graphics.Color

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ScrollView 생성
        val scrollView = ScrollView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }

        // 메인 컨테이너
        val container = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 48, 48, 48)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        // 제목
        container.addView(TextView(this).apply {
            text = "Glance Samples"
            textSize = 28f
            setTextColor(Color.parseColor("#1976D2"))
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 32)
        })

        // 안내 메시지
        container.addView(TextView(this).apply {
            text = "이 앱은 Glance 위젯 샘플 모음입니다.\n홈 화면에 위젯을 추가하여 테스트하세요."
            textSize = 16f
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 48)
        })

        // 위젯 목록
        val widgets = listOf(
            "Step 1: Basic Widget" to "기본 Glance 위젯 구조를 보여줍니다.\n- GlanceAppWidget 구현\n- GlanceAppWidgetReceiver 사용",
            "Step 2-1: Single Size" to "Single SizeMode를 사용하는 위젯입니다.\n- 모든 크기에서 동일한 레이아웃",
            "Step 2-2: Exact Size" to "Exact SizeMode를 사용하는 위젯입니다.\n- 정확한 픽셀 단위 크기 처리",
            "Step 2-3: Responsive" to "Responsive SizeMode를 사용하는 위젯입니다.\n- 크기별 다른 레이아웃 제공\n- Android 12+ 권장",
            "Step 3: State Management" to "DataStore를 활용한 상태 관리입니다.\n- 버튼 클릭으로 카운터 증가\n- 상태 유지 및 복원",
            "Step 4: Action Handling" to "다양한 액션 처리 방법을 보여줍니다.\n- actionRunCallback\n- actionStartActivity\n- actionSendBroadcast",
            "Step 5: Practical Todo" to "실용적인 Todo 위젯 예제입니다.\n- 할 일 추가/완료/삭제\n- 종합 구현 예제"
        )

        widgets.forEach { (title, description) ->
            // 위젯 카드
            val card = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(32, 24, 32, 24)
                setBackgroundColor(Color.parseColor("#F5F5F5"))
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 0, 24)
                }
            }

            // 위젯 제목
            card.addView(TextView(this).apply {
                text = title
                textSize = 18f
                setTextColor(Color.parseColor("#212121"))
                setPadding(0, 0, 0, 12)
            })

            // 위젯 설명
            card.addView(TextView(this).apply {
                text = description
                textSize = 14f
                setTextColor(Color.parseColor("#616161"))
            })

            container.addView(card)
        }

        // 위젯 추가 방법 안내
        container.addView(TextView(this).apply {
            text = "\n📱 위젯 추가 방법"
            textSize = 20f
            setTextColor(Color.parseColor("#1976D2"))
            setPadding(0, 24, 0, 16)
        })

        container.addView(TextView(this).apply {
            text = "1. 홈 화면 길게 누르기\n2. 위젯 선택\n3. Glance Samples 검색\n4. 원하는 위젯을 홈 화면에 드래그"
            textSize = 14f
            setTextColor(Color.parseColor("#424242"))
            lineHeight = (14 * 1.5).toInt()
        })

        scrollView.addView(container)
        setContentView(scrollView)
    }
}
