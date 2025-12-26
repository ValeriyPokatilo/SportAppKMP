package app.xl.sportappkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform