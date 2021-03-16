object AnnotationProcessing {
  private const val moshi_cogen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi_version}"
  private const val dagger_hilt_android_compiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger_hilt_version}"
  private const val dagger_hilt_compiler = "androidx.hilt:hilt-compiler:${Versions.dagger_hilt_viewModel_version}"
  private const val room_compiler = "androidx.room:room-compiler:${Versions.room_version}"

  val kaptLib = listOf(
    moshi_cogen,
    dagger_hilt_android_compiler,
    dagger_hilt_compiler,
    room_compiler
  )
}