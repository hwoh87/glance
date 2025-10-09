# 🚀 빠른 시작 가이드

## ✅ 빌드 준비 완료

모든 코드 검증이 완료되었으며, Android Studio에서 바로 실행 가능한 상태입니다.

---

## 🔧 수정된 문제들

### 1. Step4ActionWidget.kt - 중복 import 제거
```kotlin
// ❌ 제거됨
import androidx.glance.appwidget.action.actionStartActivity

// ✅ 이미 있는 것으로 충분
import androidx.glance.action.actionStartActivity
```

### 2. AndroidManifest.xml - 아이콘 참조 수정
```xml
<!-- ❌ 제거됨 (아이콘 파일 없음) -->
android:icon="@mipmap/ic_launcher"

<!-- ✅ 시스템 기본 아이콘 사용 -->
<!-- Android Studio에서 추후 정식 아이콘 생성 가능 -->
```

---

## 📱 Android Studio에서 실행하기

### 1단계: 프로젝트 열기
```
1. Android Studio 실행
2. File > Open
3. GlanceSamples 폴더 선택
4. OK 클릭
```

### 2단계: Gradle Sync 대기
- Android Studio가 자동으로 의존성 다운로드
- 하단 상태 바에서 진행 상황 확인
- "Gradle build finished" 메시지 대기

### 3단계: 기기/에뮬레이터 연결
```
- 실제 기기: USB 디버깅 활성화 후 연결
- 에뮬레이터: Tools > Device Manager > Create Device
```

### 4단계: 앱 실행
```
Run > Run 'app' (⌃R 또는 Shift+F10)
```

### 5단계: 위젯 테스트
```
1. 앱이 설치되면 홈 화면으로 이동
2. 홈 화면 길게 누르기
3. "위젯" 또는 "Widgets" 선택
4. "Glance Samples" 앱 찾기
5. 원하는 Step 위젯 선택하여 홈 화면에 추가
```

---

## 🎯 위젯 테스트 순서

### Step 1: 기본 위젯 ✨
- **위치**: "Step1: Basic Widget"
- **확인사항**: "Hello, Glance!" 텍스트 표시
- **학습내용**: 기본 구조, Compose 스타일 UI

### Step 2: SizeMode 비교 📏
세 가지 위젯 모두 추가하고 크기 조절하며 비교:

**Step2: Single Size**
- 크기 변경해도 동일한 레이아웃 유지

**Step2: Exact Size**
- 픽셀 단위로 반응, 배경색 실시간 변화

**Step2: Responsive Size** ⭐ 권장
- 브레이크포인트에서만 레이아웃 전환
- 3가지 크기: Compact / Medium / Large

### Step 3: State 관리 🗂️
- **테스트**: 증가/감소/리셋 버튼 클릭
- **중요**: 같은 위젯을 2개 이상 추가!
- **확인**: 각 위젯이 독립적인 카운트 유지

### Step 4: Action 처리 ⚡
- **내부 로직 실행**: 클릭 횟수 증가
- **+5 증가**: 파라미터 전달 테스트
- **웹사이트 열기**: 브라우저 실행 확인

### Step 5: 실전 Todo 위젯 📝
- **추가**: "추가" 버튼으로 Todo 생성
- **완료**: 각 항목의 "✓" 버튼 클릭
- **독립성**: 여러 인스턴스 추가하여 확인

---

## 🛠️ 명령줄 빌드 (선택사항)

### Java 설치 필요
```bash
# macOS - Homebrew
brew install openjdk@17
export JAVA_HOME=/opt/homebrew/opt/openjdk@17

# Ubuntu/Debian
sudo apt install openjdk-17-jdk

# Windows
# https://adoptium.net/ 에서 다운로드
```

### APK 빌드
```bash
cd GlanceSamples

# Debug APK 생성
./gradlew assembleDebug

# 출력 위치
# app/build/outputs/apk/debug/app-debug.apk
```

---

## ❌ 문제 해결

### "Gradle Sync failed"
```bash
# 캐시 정리
./gradlew clean
rm -rf .gradle build

# 다시 시도
# Android Studio: File > Sync Project with Gradle Files
```

### "SDK not found"
```
1. Android Studio > Tools > SDK Manager
2. "Show Package Details" 체크
3. Android SDK 34 설치
4. Android SDK Build-Tools 설치
```

### "Widget not showing in launcher"
```
1. 앱을 완전히 삭제
2. 다시 설치
3. 기기 재부팅
4. 런처 앱 재시작
```

---

## 📚 코드 구조

```
GlanceSamples/
├── app/src/main/
│   ├── java/com/example/glancesamples/
│   │   ├── step1_basic/
│   │   │   ├── Step1SimpleWidget.kt      ← 위젯 UI
│   │   │   └── Step1WidgetReceiver.kt    ← 시스템 진입점
│   │   ├── step2_sizemode/
│   │   │   ├── Step2SingleSizeWidget.kt
│   │   │   ├── Step2ExactSizeWidget.kt
│   │   │   ├── Step2ResponsiveWidget.kt
│   │   │   └── Step2Receivers.kt
│   │   ├── step3_state/
│   │   │   ├── Step3StateWidget.kt
│   │   │   ├── Step3Actions.kt           ← ActionCallback
│   │   │   └── Step3WidgetReceiver.kt
│   │   ├── step4_action/
│   │   │   ├── Step4ActionWidget.kt
│   │   │   └── Step4WidgetReceiver.kt
│   │   └── step5_practical/
│   │       ├── Step5TodoWidget.kt        ← 실전 통합
│   │       └── Step5WidgetReceiver.kt
│   ├── res/
│   │   ├── xml/                          ← 위젯 메타데이터
│   │   ├── layout/                       ← 플레이스홀더
│   │   └── values/                       ← 문자열, 색상
│   └── AndroidManifest.xml               ← 위젯 등록
└── build.gradle.kts                      ← 의존성
```

---

## ✨ 핵심 개념 복습

### GlanceAppWidget
```kotlin
class MyWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            // Compose 스타일 UI
        }
    }
}
```

### State 관리
```kotlin
// 읽기
val prefs = currentState<Preferences>()
val value = prefs[intPreferencesKey("key")] ?: 0

// 쓰기 (2단계 필수!)
updateAppWidgetState(context, glanceId) { prefs ->
    prefs[intPreferencesKey("key")] = newValue
}
MyWidget().update(context, glanceId) // 반드시!
```

### ActionCallback
```kotlin
class MyAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        // 1. 상태 변경
        updateAppWidgetState(context, glanceId) { ... }
        // 2. UI 갱신
        MyWidget().update(context, glanceId)
    }
}
```

---

## 🎓 학습 팁

1. **순서대로**: Step 1 → 2 → 3 → 4 → 5
2. **독립 테스트**: 각 Step을 따로 추가하여 비교
3. **여러 인스턴스**: State 독립성 확인용
4. **크기 조절**: Responsive 동작 이해
5. **코드 읽기**: 주석을 꼼꼼히 읽으며 학습

---

## 📞 지원

프로젝트에 문제가 있거나 질문이 있다면:
1. `BUILD_ISSUES_FIXED.md` 확인
2. `README.md`의 문제 해결 섹션 참고
3. Android Studio의 Build Output 확인

---

**준비 완료! 🎉**

Android Studio에서 프로젝트를 열고 Run 버튼만 누르면 됩니다!
