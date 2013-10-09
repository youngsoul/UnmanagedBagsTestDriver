package com.bluelobsterstudios

import static org.junit.Assert.*
import org.junit.*

class AssetIntegrationTests {

  @Before
  void setUp() {
    // Setup logic here
  }

  @After
  void tearDown() {
    // Tear down logic here
  }

  @Test
  void testAssetTags() {

    println "Before Asset save"
    Asset asset1 = new Asset(label: "Asset 1")
    assertNotNull asset1.save()
    println "After Asset save"

    println "Before create 10 Tag objects and add them to Asset"
    Tag tag = null
    10.times { i ->
      tag = new Tag(name: "Tag $i")
      asset1.addToTags(tag)
    }
    println "After create 10 Tag objects and add them to Asset"

    println "Before count of Asset and Tag objects"
    assertEquals 1, Asset.count
    assertEquals 10, Tag.count  // 7 = 3 cars from the previous test + 4 new cars from this one + 2
    assertEquals 10, asset1.getTags().size()
    println "After count of Asset and Tag objects"

    println "Before find Assets by Tag object in list"
    def x = Asset.findAllByTagInList([tag])
    println "After find Assets by Tag object in list"
    assertNotNull x
    assertEquals x[0].id, asset1.id
  }

  @Test
  void testNoteAssets() {
    NoteAsset note = new NoteAsset(label: "My Note Label", note: "this is my test note")
    assertNotNull note.save()

    Comment comment = null
    Tag tag = null
    5.times { i ->
      comment = new Comment(value: "this is comment $i")
      note.addToComments(comment)
      tag = new Tag(name: "Tag $i")
      note.addToTags(tag)
    }

    println "Before count of Note, Tag, comment objects"
    assertEquals 1, Asset.count
    assertEquals 5, Tag.count
    assertEquals 5, Comment.count
    assertEquals 5, note.getTags().size()
    assertEquals 5, note.getComments().size()
    println "After count of Note,Tag,Comment objects"



  }
}
