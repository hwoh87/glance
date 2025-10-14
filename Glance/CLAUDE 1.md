# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository Overview

This is a Korean-language documentation repository for Android Jetpack Glance widget development. It contains comprehensive guides covering Glance architecture, lifecycle, UI composition, and best practices for building Android home screen widgets using Compose-style declarative UI.

## Documentation Structure

The documentation is organized as numbered markdown files covering different aspects of Glance development:

1. **기본구조 및 개념.md** - Core concepts and architecture
2. **GlanceAppWidgetReceiver.md** - Widget receiver and system integration
3. **의존성 추가.md** - Dependencies and Gradle setup
4. **GlanceAppWidget.md** - Main widget class implementation
5. **Glance 생명주기 & 업데이트 트리거.md** - Lifecycle and update triggers
6. **UI 작성 규칙 및 제약사항.md** - UI composition rules and constraints
7. **SizeMode.md** - Widget sizing strategies (Single/Exact/Responsive)
8. **Action(이벤트) 처리 방식.md** - Event handling and user interactions
9. **렌더링 파이프 라인.md** - Rendering pipeline from DSL to RemoteViews
10. **State 관리.md** - State management with DataStore
11. **디버깅 & 테스팅.md** - Debugging and testing approaches
12. **성능 최적화 & 베스트 프랙티스.md** - Performance optimization

## Key Technical Concepts

### Architecture
- **GlanceAppWidgetReceiver**: Extends AppWidgetProvider, acts as entry point for system broadcasts
- **GlanceAppWidget**: Core class defining widget UI and behavior via `provideGlance()` and `provideContent {}`
- **Rendering Pipeline**: Glance DSL → RemoteViews transformation → AppWidgetManager display
- **State Management**: DataStore-based with PreferencesGlanceStateDefinition or ProtoGlanceStateDefinition

### Core Dependencies
```kotlin
androidx.glance:glance-appwidget:1.0.0
androidx.glance:glance-material3:1.0.0
androidx.datastore:datastore-preferences:1.0.0
androidx.work:work-runtime-ktx:2.8.1
```

### Important Constraints
- Glance DSL resembles Compose but converts to RemoteViews, not true Compose rendering
- Cannot use Compose runtime APIs: `remember`, `LaunchedEffect`, animations, complex gestures
- Must use `androidx.glance.unit.dp` instead of standard Compose dp
- State updates require explicit `update()` or `updateAll()` calls to trigger UI refresh
- `initialLayout` resource is mandatory for widget to appear in launcher selection

### Size Modes
- **Single**: One layout for all sizes
- **Exact**: Precise pixel-level size handling (high refresh rate)
- **Responsive**: Predefined size buckets for breakpoint-based layouts (Android 12+, recommended)

### Update Patterns
- Manual: `MyGlanceWidget().update(context, glanceId)` or `.updateAll(context)`
- State change: `updateAppWidgetState { ... }` followed by `update()`
- System triggers: Resize, configuration changes automatically invoke `provideGlance()`

## Content Guidelines

When working with this documentation:

- Maintain Korean language throughout for consistency with existing content
- Follow the TIP callout format for key concepts: `> [!TIP] 🗣️ 핵심내용` or `> [!TIP] 🗣️ 발표 대본`
- Include practical code examples with both ✅ correct and ❌ incorrect patterns
- Reference actual test environments and device specifications when discussing sizing/layout behavior
- Provide presentation-style explanations (발표용 대본) to make technical concepts accessible

## Editing Notes

- Files use Korean filenames with spaces - always quote paths when using bash commands
- Documentation includes embedded videos and images using Obsidian syntax
- Code examples use Kotlin with Jetpack Glance/Compose DSL
- Some files contain presentation scripts and teaching materials suitable for technical talks
