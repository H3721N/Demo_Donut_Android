package com.gomez.herlin.logindemo.dto

import java.io.Serializable

class BattersDto : Serializable {
    var batter: List<BatterDto>? = null

    constructor()

    constructor(batter: List<BatterDto>?) {
        this.batter = batter
    }
}
