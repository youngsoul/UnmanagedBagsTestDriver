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

    1000.times { i ->
      Tag tag = new Tag(name: "Tag $i")
      asset1.addToTags(tag)
    }

    println "Before count of Asset and Tag objects"
    assertEquals 1, Asset.count
    assertEquals 1000, Tag.count  // 7 = 3 cars from the previous test + 4 new cars from this one + 2
    assertEquals 1000, asset1.getTags().size()
    println "After count of Asset and Tag objects"

  }
}
