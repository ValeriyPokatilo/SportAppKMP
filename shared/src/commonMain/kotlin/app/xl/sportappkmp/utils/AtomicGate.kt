package app.xl.sportappkmp.utils

expect fun <R> atomicGate(lock: Any, block: () -> R): R