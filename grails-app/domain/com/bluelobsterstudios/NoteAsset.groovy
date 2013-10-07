package com.bluelobsterstudios

class NoteAsset extends Asset {

  String note

  static hasUnmanagedBags = [ comments: [childClass: Comment, parentFKPropertyName: 'belongsToId', parentKeyPropertyName: 'assetId'] ]

  static mapping = {
    tablePerHierarchy(false)
  }

  static constraints = {
  }
}
