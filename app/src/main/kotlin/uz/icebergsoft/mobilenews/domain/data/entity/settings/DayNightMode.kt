package uz.icebergsoft.mobilenews.domain.data.entity.settings

enum class DayNightMode(val modeIndex: Int) {
    ONLY_LIGHT_MODE(1),
    ONLY_NIGHT_MODE(2),
    FOLLOW_SYSTEM_NIGHT_MODE(-1);

    companion object {
        val DEFAULT: DayNightMode = FOLLOW_SYSTEM_NIGHT_MODE
    }
}