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

    Asset asset1 = new Asset(label: "Asset 1")
    assertNotNull asset1.save()

    Tag tag = null
    10.times { i ->
      tag = new Tag(name: "Tag $i")
      asset1.addToTags(tag)
    }

    println "Before count of Asset and Tag objects"
    assertEquals 1, Asset.count
    assertEquals 10, Tag.count  // 7 = 3 cars from the previous test + 4 new cars from this one + 2
    assertEquals 10, asset1.getTags().size()
    println "After count of Asset and Tag objects"

    def x = Asset.findAllByTagInList([tag])
    assertNotNull x
    assertEquals x[0].id, asset1.id
  }
}
