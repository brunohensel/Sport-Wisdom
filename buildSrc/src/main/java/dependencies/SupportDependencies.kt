package dependencies

object SupportDependencies {
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    private const val material_design = "com.google.android.material:material:${Versions.materialDesign}"
    private const val activity_ktx = "androidx.activity:activity-ktx:${Versions.activity_ktx_version}"
    private const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx_version}"
    private const val legacySupportV4 = "androidx.legacy:legacy-support-v4:${Versions.legacySupportV4_version}"

    val supportLibs = listOf(
        appcompat,
        constraintlayout,
        material_design,
        activity_ktx,
        fragment_ktx,
        legacySupportV4
    )

}