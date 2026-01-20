package app.xl.sportappkmp.utils

actual fun <R> atomicGate(lock: Any, block: () -> R): R {
    return synchronized(lock) { block() }
}