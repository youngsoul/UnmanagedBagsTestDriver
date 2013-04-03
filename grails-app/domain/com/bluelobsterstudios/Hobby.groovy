package com.bluelobsterstudios

class Hobby {

    String name
    Long ownerId

    static constraints = {
        ownerId nullable: true
    }
}
