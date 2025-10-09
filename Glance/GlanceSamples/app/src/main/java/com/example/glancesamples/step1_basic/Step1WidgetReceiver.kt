package com.example.glancesamples.step1_basic

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

/**
 * Step 1: 위젯 리시버
 *
 * 역할:
 * - 시스템 브로드캐스트를 받는 진입점 (Entry Point)
 * - GlanceAppWidgetReceiver를 상속하고 glanceAppWidget 프로퍼티만 오버라이드
 * - AndroidManifest.xml에 등록해야 런처에 표시됨
 *
 * 시스템이 보내는 주요 브로드캐스트:
 * - APPWIDGET_UPDATE: 위젯 업데이트 요청
 * - APPWIDGET_ENABLED: 첫 번째 위젯 추가 시
 * - APPWIDGET_DISABLED: 마지막 위젯 제거 시
 * - APPWIDGET_DELETED: 특정 위젯 제거 시
 */
class Step1WidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = Step1SimpleWidget()
}
