# Glance Documentation Project - Claude Instructions

## Project Context

Korean-language technical documentation repository for Android Jetpack Glance widget development. This is a comprehensive educational resource covering Glance architecture, lifecycle, UI composition, and best practices.

## Project Structure

### Documentation Files (12 Core Guides)
1. `기본구조 및 개념.md` - Core concepts and architecture
2. `GlanceAppWidgetReceiver.md` - Widget receiver and system integration
3. `의존성 추가.md` - Dependencies and Gradle setup
4. `GlanceAppWidget.md` - Main widget class implementation
5. `Glance 생명주기 & 업데이트 트리거.md` - Lifecycle and update triggers
6. `UI 작성 규칙 및 제약사항.md` - UI composition rules and constraints
7. `SizeMode.md` - Widget sizing strategies (Single/Exact/Responsive)
8. `Action(이벤트) 처리 방식.md` - Event handling and user interactions
9. `렌더링 파이프 라인.md` - Rendering pipeline from DSL to RemoteViews
10. `State 관리.md` - State management with DataStore
11. `디버깅 & 테스팅.md` - Debugging and testing approaches
12. `성능 최적화 & 베스트 프랙티스.md` - Performance optimization

## Technical Foundation

### Glance Architecture
- **GlanceAppWidgetReceiver**: Entry point extending AppWidgetProvider
- **GlanceAppWidget**: Core class with `provideGlance()` and `provideContent {}`
- **Rendering Pipeline**: Glance DSL → RemoteViews → AppWidgetManager
- **State Management**: DataStore-based (Preferences/Proto)

### Key Dependencies
```kotlin
androidx.glance:glance-appwidget:1.0.0
androidx.glance:glance-material3:1.0.0
androidx.datastore:datastore-preferences:1.0.0
androidx.work:work-runtime-ktx:2.8.1
```

### Critical Constraints
- Glance DSL resembles Compose but converts to RemoteViews (not true Compose)
- Cannot use: `remember`, `LaunchedEffect`, animations, complex gestures
- Must use: `androidx.glance.unit.dp` (not standard Compose dp)
- State updates: Explicit `update()` or `updateAll()` required
- Widget registration: `initialLayout` resource mandatory

## Documentation Standards

### Language & Style
- **Primary Language**: Korean (maintain consistency)
- **Code Language**: Kotlin with Jetpack Glance/Compose DSL
- **Tone**: Educational, presentation-friendly (발표용)
- **Format**: Obsidian markdown with callouts and embeds

### Content Patterns

#### Callout Format
```markdown
> [!TIP] 🗣️ 핵심내용
> Key technical concept explanation

> [!TIP] 🗣️ 발표 대본
> Presentation script for teaching
```

#### Code Examples
Always provide both correct and incorrect patterns:
```kotlin
// ✅ Correct approach
GlanceModifier.padding(16.dp)

// ❌ Wrong approach
Modifier.padding(16.dp) // Wrong import!
```

#### Technical References
- Include test environment details (device specs, Android version)
- Reference actual behavior observations
- Provide debugging context and solutions

### File Handling
- **Korean filenames**: Always quote paths in bash commands
- **Embedded media**: Use Obsidian syntax `![[video.mp4]]` or `![[image.png]]`
- **Cross-references**: Link between documentation files for continuity

## Documentation Tasks

### When Creating New Content
1. **Maintain Korean language** throughout
2. **Follow TIP callout format** for key concepts
3. **Include ✅/❌ code examples** for clarity
4. **Add presentation scripts** (발표 대본) for teaching context
5. **Reference test environments** for practical grounding

### When Enhancing Existing Content
1. **Preserve existing structure** and numbering
2. **Maintain voice consistency** (educational, accessible)
3. **Expand with practical examples** from real scenarios
4. **Add cross-references** to related documentation
5. **Update technical accuracy** with latest Glance versions

### When Creating Summaries/Guides
1. **Extract core concepts** from detailed docs
2. **Create quick reference** patterns
3. **Build learning paths** (beginner → advanced)
4. **Generate index/TOC** with smart navigation
5. **Produce presentation materials** from existing content

## Integration with SuperClaude

### Applicable Modes
- **--brainstorm**: When planning new documentation structure
- **--task-manage**: Multi-file documentation updates
- **--token-efficient**: Summarization and compression tasks

### Tool Preferences
- **Read**: Content analysis and cross-reference extraction
- **Grep**: Pattern identification across documentation
- **Write**: New documentation creation
- **Edit**: Precise content updates maintaining format

### Not Applicable
- **Serena MCP**: No source code (documentation only)
- **Magic/Playwright MCP**: No UI/web testing needed
- **Morphllm MCP**: No code transformation required

## Quality Standards

### Content Quality
- **Technical Accuracy**: Verified against official Glance documentation
- **Practical Relevance**: Real-world examples and test scenarios
- **Educational Value**: Clear explanations suitable for presentations
- **Consistency**: Uniform terminology and formatting

### Documentation Completeness
- **Comprehensive Coverage**: All aspects of topic addressed
- **Example Diversity**: Multiple use cases and scenarios
- **Error Guidance**: Common mistakes and solutions
- **Reference Links**: Cross-links to related concepts

## Project Goals

1. **Educational Resource**: Enable developers to master Glance widgets
2. **Presentation Ready**: Content suitable for technical talks and training
3. **Practical Guide**: Real-world patterns and debugging solutions
4. **Korean Accessibility**: High-quality Korean technical content

## Working Guidelines

- **Always check**: Existing content before creating new files
- **Preserve format**: Korean filenames, TIP callouts, code patterns
- **Think teaching**: Content should work for presentations
- **Be practical**: Include test environments, real scenarios, debugging tips
- **Stay current**: Reference latest Glance versions and best practices
