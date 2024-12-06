package com.pro.electronic.model

class Admin {
    @JvmField
    var id: Long = 0
    @JvmField
    var email: String? = null

    constructor()

    constructor(id: Long, email: String?) {
        this.id = id
        this.email = email
    }
}
