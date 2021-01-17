package dependencies

object TestDependencies {
    const val junit4 = "junit:junit:${Versions.junit}"
    const val room_testing = "androidx.room:room-testing:${Versions.room_version}"
    const val coroutines_testing = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version}"
    const val truth_testing = "com.google.truth:truth:${Versions.truth_version}"
    const val mockito_inline_testing = "org.mockito:mockito-inline:${Versions.mockito_inline_version}"
}