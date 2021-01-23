package dependencies

object AndroidTestDependencies {
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val junit_ext = "androidx.test.ext:junit:${Versions.junitExt}"
    const val workManager = "androidx.work:work-testing:${Versions.workManager_version}"
    const val instrumentation_runner = "androidx.test.runner.AndroidJUnitRunner"
}