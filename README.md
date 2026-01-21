Портирование iOS-проекта приложения [Дневник тренировок](https://github.com/ValeriyPokatilo/SportAppCrop) (MVVM-C / UIKit / RxSwift) на Kotlin Multiplatform

Проект разрабатывается с использованием следующего стека технологий:

- **Kotlin Multiplatform (KMP)**
- **Kotlin / Swift**
- **Jetpack Compose (Android) / SwiftUI (iOS)**
- **Navigation Compose**
- **moko-mvvm**
- **moko-resources**
- **kotlinx.serialization**
- **multiplatform.settings**

## Сборка проекта

Для корректной генерации общих ресурсов и сборки проекта выполните следующую команду из корня проекта:

```bash
./gradlew clean :shared:generateMRcommonMain build