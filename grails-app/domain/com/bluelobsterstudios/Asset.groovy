package com.bluelobsterstudios

class Asset {

  String label
  String assetId = java.util.UUID.randomUUID().toString()

  static hasUnmanagedBags = [ tags: [childClass: Tag, parentFKPropertyName: 'belongsToId', parentKeyPropertyName: 'assetId'] ]

  static constraints = {
  }

}
