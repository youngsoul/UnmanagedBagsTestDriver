package com.bluelobsterstudios

class Child {
    String name

    Long ownerId

    static constraints = {
        ownerId nullable: true
    }
}
