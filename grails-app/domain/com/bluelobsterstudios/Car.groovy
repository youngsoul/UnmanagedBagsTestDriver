package com.bluelobsterstudios

class Car {

    String name

    Long ownerId

    static constraints = {
        ownerId nullable: true
    }
}
