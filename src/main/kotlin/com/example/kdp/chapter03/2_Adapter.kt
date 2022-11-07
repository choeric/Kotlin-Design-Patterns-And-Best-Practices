package com.example.kdp.chapter03

import java.util.stream.Stream

interface USPlug {
    val hasPower: Int
}

interface EUPlug {
    val hasPower: String
}

interface UsbMini {
    val hasPower: Power
}

interface UsbTypeC {
    val hasPower: Boolean
}

enum class Power {
    TRUE, FALSE
}

fun cellPhone(chargeCable: UsbTypeC) {
    if (chargeCable.hasPower) println("I've Got The Power!")
    else println("No Power")
}

fun usPowerOutlet(): USPlug {
    return object : USPlug {
        override val hasPower: Int
            get() = 1
    }
}

fun charger(plug: EUPlug): UsbMini {
    return object : UsbMini{
        override val hasPower: Power
            get() = Power.valueOf(plug.hasPower)
    }
}

fun USPlug.toEUPlug(): EUPlug {
    val hasPower = if (this.hasPower == 1) "TRUE" else "FALSE"
    return object : EUPlug {
        override val hasPower: String
            get() = hasPower
    }
}

fun UsbMini.toUsbTypeC(): UsbTypeC {
    val hasPower = this.hasPower == Power.TRUE
    return object : UsbTypeC {
        override val hasPower: Boolean
            get() = hasPower
    }
}

fun main() {
    cellPhone(
        charger(
            usPowerOutlet().toEUPlug()
        ).toUsbTypeC()
    )
}

