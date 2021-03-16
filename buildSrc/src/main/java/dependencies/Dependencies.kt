package dependencies

object Dependencies {
  private const val kotlin_standard_library = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"
  private const val ktx = "androidx.core:core-ktx:${Versions.ktx_version}"
  private const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
  private const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
  private const val viewModel_scope = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ktx_scope_version}"
  private const val lifecycle_scope = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.ktx_scope_version}"
  private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
  private const val moshi_converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit_version}"
  private const val moshi_kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi_version}"
  private const val moshi_std = "com.squareup.moshi:moshi:${Versions.moshi_version}"
  private const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp_version}"
  private const val okhttp_log = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_version}"
  private const val dagger_hilt = "com.google.dagger:hilt-android:${Versions.dagger_hilt_version}"
  private const val dagger_hilt_viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.dagger_hilt_viewModel_version}"
  private const val room = "androidx.room:room-runtime:${Versions.room_version}"
  private const val room_ktx = "androidx.room:room-ktx:${Versions.room_version}"
  private const val timber = "com.jakewharton.timber:timber:${Versions.timber_version}"
  private const val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin_version}"
  private const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_version}"
  private const val navigation_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_version}"
  private const val coil = "io.coil-kt:coil:${Versions.coil_version}"
  private const val flexbox = "com.google.android:flexbox:${Versions.flexbox_version}"
  private const val workerManager = "androidx.work:work-runtime-ktx:${Versions.workManager_version}"
  private const val threetenabp = "com.jakewharton.threetenabp:threetenabp:${Versions.threeten_version}"

  val baseLibs = listOf(
    kotlin_standard_library,
    ktx,
    coroutines_core,
    coroutines_android,
    mockito_kotlin,
    threetenabp,
    flexbox,
    coil,
    navigation_ktx,
    navigation_fragment,
    timber,
    workerManager
  )

  val lifecycleLib = listOf(
    viewModel_scope,
    lifecycle_scope
  )

  val networkLib = listOf(
    retrofit,
    moshi_converter,
    okhttp,
    okhttp_log
  )

  val moshiLib = listOf(
    moshi_kotlin,
    moshi_std
  )

  val hilLib = listOf(
    dagger_hilt,
    dagger_hilt_viewModel
  )

  val roomLib = listOf(
    room,
    room_ktx
  )
}