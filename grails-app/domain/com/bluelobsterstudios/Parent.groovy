package com.bluelobsterstudios

import com.bluelobsterstudios.annotations.UnmanagedBag
import com.bluelobsterstudios.annotations.UnmanagedBags

@UnmanagedBags(all=[
@UnmanagedBag(parentKeyPropertyName="id", collectionPropertyName="myKids", parentFKPropertyName = "ownerId", childClass = com.bluelobsterstudios.Child),
@UnmanagedBag(collectionPropertyName="myHobbies", childClass = Hobby)
])
class Parent {

    // always a list of either a Class OR a Map
    static hasUnmanagedBags = [
            cars: com.bluelobsterstudios.Car, animals:[parentFKPropertyName: "ownerFkId", childClass: Pet]
    ]

    String name
    static constraints = {
    }
}
