package com.gomez.herlin.logindemo.dto

import java.io.Serializable

class ToppingDto : Serializable {
    var id: String? = null
    var type: String? = null

    constructor()

    constructor(id: String?, type: String?) {
        this.id = id
        this.type = type
    }
}
