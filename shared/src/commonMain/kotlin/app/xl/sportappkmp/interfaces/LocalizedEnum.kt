package app.xl.sportappkmp.interfaces

import app.xl.sportappkmp.utils.Localizer

interface LocalizedEnum {
    fun localizedTitle(localizer: Localizer): String
}