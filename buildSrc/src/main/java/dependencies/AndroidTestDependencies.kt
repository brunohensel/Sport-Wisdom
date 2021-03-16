package dependencies

object AndroidTestDependencies {
  private const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
  private const val junit_ext = "androidx.test.ext:junit:${Versions.junitExt}"
  private const val workManager = "androidx.work:work-testing:${Versions.workManager_version}"
  private const val arch_test = "android.arch.core:core-testing:${Versions.arch_version}"
  private const val coroutine_android_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version}"
  private const val room_android_test = "androidx.room:room-testing:${Versions.room_version}"
  const val instrumentation_runner = "androidx.test.runner.AndroidJUnitRunner"

  val androidTestImpLibList = listOf(
      junit_ext,
      espresso_core,
      workManager,
      arch_test,
      coroutine_android_test,
      room_android_test
  )
}