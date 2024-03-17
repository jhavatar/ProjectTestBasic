package io.chthonic.projecttestbasic.data.ktx

import kotlin.enums.EnumEntries

inline infix fun <reified E : Enum<E>> EnumEntries<E>.findByName(value: String?): E? {
    return this.firstOrNull { it.name == value }
}