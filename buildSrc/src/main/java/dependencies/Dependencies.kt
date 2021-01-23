package dependencies

object Dependencies {
    const val kotlin_standard_library = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx_version}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    const val viewModel_scope = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ktx_scope_version}"
    const val lifecycle_scope = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.ktx_scope_version}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val moshi_converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit_version}"
    const val moshi_kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi_version}"
    const val moshi_std = "com.squareup.moshi:moshi:${Versions.moshi_version}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp_version}"
    const val okhttp_log = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_version}"
    const val dagger_hilt = "com.google.dagger:hilt-android:${Versions.dagger_hilt_version}"
    const val dagger_hilt_viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.dagger_hilt_viewModel_version}"
    const val room = "androidx.room:room-runtime:${Versions.room_version}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room_version}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber_version}"
    const val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin_version}"
    const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_version}"
    const val navigation_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_version}"
    const val coil = "io.coil-kt:coil:${Versions.coil_version}"
    const val flexbox = "com.google.android:flexbox:${Versions.flexbox_version}"
    const val workerManager = "androidx.work:work-runtime-ktx:${Versions.workManager_version}"
}