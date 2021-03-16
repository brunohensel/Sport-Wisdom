package dependencies

object TestDependencies {
    private const val junit4 = "junit:junit:${Versions.junit}"
    private const val room_testing = "androidx.room:room-testing:${Versions.room_version}"
    private const val coroutines_testing = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version}"
    private const val truth_testing = "com.google.truth:truth:${Versions.truth_version}"
    private const val mockito_inline_testing = "org.mockito:mockito-inline:${Versions.mockito_inline_version}"

    val testImpLibList = listOf(
        junit4,
        room_testing,
        coroutines_testing,
        truth_testing,
        mockito_inline_testing
    )
}