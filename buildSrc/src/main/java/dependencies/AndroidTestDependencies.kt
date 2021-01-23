package dependencies

object AndroidTestDependencies {
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val junit_ext = "androidx.test.ext:junit:${Versions.junitExt}"
    const val workManager = "androidx.work:work-testing:${Versions.workManager_version}"
    const val arch_test = "android.arch.core:core-testing:${Versions.arch_version}"
    const val coroutine_android_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version}"
    const val room_android_test = "androidx.room:room-testing:${Versions.room_version}"
    const val instrumentation_runner = "androidx.test.runner.AndroidJUnitRunner"
}