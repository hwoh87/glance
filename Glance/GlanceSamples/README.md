# 🎯 Glance 샘플 프로젝트

Android Jetpack Glance 위젯 개발을 단계별로 학습할 수 있는 샘플 프로젝트입니다.

## 📚 프로젝트 구조

```
GlanceSamples/
├── app/src/main/java/com/example/glancesamples/
│   ├── step1_basic/          # Step 1: 기본 위젯
│   ├── step2_sizemode/       # Step 2: SizeMode (Single/Exact/Responsive)
│   ├── step3_state/          # Step 3: State 관리 (DataStore)
│   ├── step4_action/         # Step 4: Action 처리
│   └── step5_practical/      # Step 5: 실전 Todo 위젯
└── app/src/main/res/
    ├── xml/                   # 위젯 메타데이터
    └── layout/               # 플레이스홀더 레이아웃
```

## 🎓 학습 단계

### Step 1: 기본 위젯 (step1_basic)
**학습 목표**
- GlanceAppWidget 클래스 기본 구조
- provideGlance와 provideContent 이해
- Compose 스타일 선언형 UI 작성
- 기본 레이아웃 컴포넌트 (Column, Text)

**핵심 파일**
- `Step1SimpleWidget.kt`: 가장 간단한 위젯 구현
- `Step1WidgetReceiver.kt`: 시스템 브로드캐스트 수신
- `step1_widget_info.xml`: 위젯 메타데이터

**테스트 방법**
1. 앱 설치 후 홈 화면에서 위젯 추가
2. "Step1: Basic Widget" 선택
3. "Hello, Glance!" 텍스트가 표시되는지 확인

---

### Step 2: SizeMode (step2_sizemode)
**학습 목표**
- SizeMode.Single: 고정 레이아웃
- SizeMode.Exact: 픽셀 단위 반응형 UI
- SizeMode.Responsive: 브레이크포인트 기반 레이아웃 (권장)

**핵심 파일**
- `Step2SingleSizeWidget.kt`: 모든 크기에서 동일한 UI
- `Step2ExactSizeWidget.kt`: LocalSize로 정확한 크기 확인
- `Step2ResponsiveWidget.kt`: 버킷 시스템으로 성능 최적화

**테스트 방법**
1. 각 위젯을 홈 화면에 추가
2. 위젯 크기를 조절하며 동작 차이 확인
3. Responsive 위젯에서 버킷 전환 확인

**차이점 비교**
- **Single**: 크기 변경 시에도 동일한 레이아웃
- **Exact**: 1픽셀 단위로 즉시 반응 (배경색 변화)
- **Responsive**: 정의된 브레이크포인트에서만 레이아웃 전환

---

### Step 3: State 관리 (step3_state)
**학습 목표**
- PreferencesGlanceStateDefinition 사용법
- currentState()로 상태 읽기
- updateAppWidgetState()로 상태 쓰기
- 인스턴스별 독립적인 상태 관리

**핵심 파일**
- `Step3StateWidget.kt`: StateDefinition 정의 및 UI
- `Step3Actions.kt`: ActionCallback을 통한 상태 변경

**테스트 방법**
1. 위젯을 홈 화면에 추가
2. "증가", "감소", "리셋" 버튼 테스트
3. **중요**: 같은 위젯을 2개 이상 추가하여 독립적인 상태 확인

**핵심 개념**
```kotlin
// 상태 읽기
val prefs = currentState<Preferences>()
val count = prefs[intPreferencesKey("count")] ?: 0

// 상태 쓰기 (2단계 필수!)
updateAppWidgetState(context, glanceId) { prefs ->
    prefs[intPreferencesKey("count")] = newValue
}
MyWidget().update(context, glanceId) // 반드시 호출!
```

---

### Step 4: Action 처리 (step4_action)
**학습 목표**
- actionRunCallback: 위젯 내부 로직 실행
- actionStartActivity: 외부 앱/브라우저 실행
- ActionParameters: 파라미터 전달

**핵심 파일**
- `Step4ActionWidget.kt`: 다양한 Action 패턴 구현

**테스트 방법**
1. "내부 로직 실행" 버튼 → 클릭 횟수 증가
2. "+5 증가" 버튼 → 파라미터로 5 전달
3. "웹사이트 열기" 버튼 → 브라우저 실행

**Action 흐름**
```
사용자 클릭 → PendingIntent 실행 → ActionCallback.onAction()
→ updateAppWidgetState() → widget.update() → UI 갱신
```

---

### Step 5: 실전 Todo 위젯 (step5_practical)
**학습 목표**
- State, Action, UI를 통합한 실전 예제
- 리스트 UI 구현
- ActionParameters로 항목별 구분
- 완전한 CRUD 기능 구현

**핵심 파일**
- `Step5TodoWidget.kt`: 완전한 Todo 위젯

**테스트 방법**
1. "추가" 버튼으로 Todo 항목 추가
2. 각 항목의 "✓" 버튼으로 완료 처리
3. 여러 인스턴스 추가하여 독립적인 리스트 확인

**구현 기능**
- ✅ Todo 추가 (ActionParameters 활용)
- ✅ Todo 완료/삭제
- ✅ 인스턴스별 독립적인 Todo 리스트
- ✅ 비어있을 때 안내 메시지

---

## 🛠️ 빌드 및 실행

### 요구사항
- Android Studio Hedgehog (2023.1.1) 이상
- Android SDK 34
- Kotlin 1.9.20+
- Gradle 8.2.0+

### 설치 방법
```bash
# 1. 프로젝트 클론
git clone <repository-url>
cd GlanceSamples

# 2. Android Studio에서 프로젝트 열기
# File > Open > GlanceSamples 폴더 선택

# 3. Gradle Sync
# Android Studio가 자동으로 Sync 진행

# 4. 빌드 및 실행
# Run > Run 'app'
```

### 위젯 테스트 방법
1. 앱을 기기/에뮬레이터에 설치
2. 홈 화면 길게 누르기
3. "위젯" 메뉴 선택
4. "Glance Samples" 앱 찾기
5. 원하는 Step 위젯을 홈 화면에 드래그

## 📦 주요 의존성

```kotlin
// Glance 위젯 라이브러리
implementation("androidx.glance:glance-appwidget:1.1.1")
implementation("androidx.glance:glance-material3:1.1.0")

// State 관리 (DataStore)
implementation("androidx.datastore:datastore-preferences:1.1.1")

// 백그라운드 작업 (WorkManager)
implementation("androidx.work:work-runtime-ktx:2.9.0")
```

## 🎯 학습 순서 권장

1. **Step 1 → Step 2** → SizeMode 비교
2. **Step 3 독립 학습** → State 관리 이해
3. **Step 4 독립 학습** → Action 처리 이해
4. **Step 5 통합** → 전체 흐름 이해

## 📖 주요 개념 정리

### GlanceAppWidget
위젯의 핵심 클래스. `provideGlance()`에서 UI를 정의합니다.

### StateDefinition
위젯 상태를 저장하는 방식을 정의합니다.
- PreferencesGlanceStateDefinition: 간단한 key-value
- ProtoGlanceStateDefinition: 복잡한 구조화 데이터

### ActionCallback
사용자 인터랙션을 처리하는 인터페이스.
`onAction()` 메서드에서 상태 변경 및 UI 갱신을 수행합니다.

### LocalSize
현재 위젯의 크기를 가져옵니다. SizeMode에 따라 동작이 다릅니다.

## ⚠️ 주의사항

### 1. update() 호출 필수
상태 변경 후 반드시 `widget.update()`를 호출해야 UI가 갱신됩니다.
```kotlin
updateAppWidgetState(context, glanceId) { prefs ->
    prefs[someKey] = newValue
}
MyWidget().update(context, glanceId) // 필수!
```

### 2. 인스턴스별 독립 상태
각 위젯 인스턴스는 GlanceId로 구분되며 독립적인 상태를 가집니다.

### 3. RemoteViews 제약
Glance는 내부적으로 RemoteViews로 변환되므로:
- `remember`, `LaunchedEffect` 사용 불가
- 복잡한 애니메이션 제한
- `androidx.glance.unit.dp` 사용 필수

## 🐛 문제 해결

### 위젯이 런처에 안 보여요
- `initialLayout` 리소스가 정의되어 있는지 확인
- AndroidManifest.xml에 Receiver 등록 확인
- Gradle Sync 후 재빌드

### 버튼 클릭이 안 돼요
- `update()` 호출을 빠뜨렸는지 확인
- ProGuard 규칙이 적용되어 있는지 확인 (Release 빌드)

### 상태가 저장 안 돼요
- `stateDefinition` 오버라이드 확인
- `updateAppWidgetState()` → `update()` 순서 확인

## 📚 추가 학습 자료

- [공식 Glance 문서](https://developer.android.com/jetpack/compose/glance)
- [Glance GitHub](https://github.com/androidx/androidx/tree/androidx-main/glance)
- [코드랩](https://developer.android.com/codelabs/jetpack-compose-for-widgets)

## 📝 라이선스

이 샘플 프로젝트는 학습 목적으로 자유롭게 사용 가능합니다.
