package com.gomez.herlin.logindemo.dto

import java.io.Serializable

class DonutsDto : Serializable {
    @JvmField
    var id: String? = null
    @JvmField
    var type: String? = null
    @JvmField
    var name: String? = null
    var ppu: Double = 0.0
    var batters: BattersDto? = null
    var topping: List<ToppingDto>? = null

    constructor()

    constructor(id: String?, type: String?, name: String?) {
        this.id = id
        this.type = type
        this.name = name
    }

    constructor(
        id: String?,
        type: String?,
        name: String?,
        ppu: Double,
        batters: BattersDto?,
        topping: List<ToppingDto>?
    ) {
        this.id = id
        this.type = type
        this.name = name
        this.ppu = ppu
        this.batters = batters
        this.topping = topping
    }

    val donuts: List<DonutsDto>?
        get() = null
}
