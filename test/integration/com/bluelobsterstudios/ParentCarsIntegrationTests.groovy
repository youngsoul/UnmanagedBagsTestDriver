package com.bluelobsterstudios

import static org.junit.Assert.*
import org.junit.*

class ParentCarsIntegrationTests {

  @Before
  void setUp() {
    // Setup logic here
  }

  @After
  void tearDown() {
    // Tear down logic here
  }

  @Test
  void testFindParentFromCars() {
    Parent parent = new Parent()
    parent.name = "test parent"
    parent.save()
    println "After parent save"

    Parent parent2 = new Parent()
    parent2.name = "test parent 2"
    parent2.save()
    println "After parent 2 save"

    Car car1 = new Car()
    car1.name = "fast"

    println "Before addToCars with unsaved Car"
    parent.addToCars( car1 )
    println "After addToCars with unsaved Car"


    println "Before addToCars with unsaved Car"
    Car car2 = new Car()
    car2.name = "mini van"
    parent.addToCars(car2)
    println "After addToCars with unsaved Car"

    println "Before addToCars with unsaved Car"
    Car car3 = new Car()
    car3.name = "gig car"
    parent2.addToCars(car3)
    println "After addToCars with unsaved Car"

    Car car4 = new Car()
    car4.name = "mystery car"
    car4.save()
    println "After Car4 mystery car not associated to parent"

    println "Before count of Parent and Car objects"
    assertEquals 2, Parent.count
    assertEquals 4, Car.count
    println "After count of Parent and Car objects"

    List<Parent> parentList = Parent.findAllByCarInList([car1])
    assertNotNull parentList
    assertEquals 1, parentList.size()
    assertEquals parent.id, parentList[0].id

    parentList = Parent.findAllByCarInList([car1,car2])
    assertNotNull parentList
    assertEquals 1, parentList.size()
    assertEquals parent.id, parentList[0].id

    parentList = Parent.findAllByCarInList([car1,car2,car3] as List)
    assertNotNull parentList
    assertEquals 2, parentList.size()
    assertTrue parentList*.id.containsAll([parent.id, parent2.id])

    parentList = Parent.findAllByCarInList([car4])
    assertNotNull parentList
    assertEquals 0, parentList.size()

  }

  @Test
  void testParentCars() {
    println "Create Parent and save"
    Parent parent = new Parent()
    parent.name = "test parent"
    parent.save()
    println "After parent save"

    Car car1 = new Car()
    car1.name = "fast"

    println "Before addToCars with unsaved Car"
    parent.addToCars( car1 )
    println "After addToCars with unsaved Car"


    println "Before addToCars with unsaved Car"
    Car car2 = new Car()
    car2.name = "mini van"
    parent.addToCars(car2)
    println "After addToCars with unsaved Car"

    println "Before addToCars with unsaved Car"
    Car car3 = new Car()
    car3.name = "gig car"
    parent.addToCars(car3)
    println "After addToCars with unsaved Car"

    println "Before count of Parent and Car objects"
    assertEquals 1, Parent.count
    assertEquals 3, Car.count
    println "After count of Parent and Car objects"

    println "Before parent.getCars"
    List<Car> cars = parent.getCars()
    assertNotNull cars
    assertEquals 3, cars.size()
    println "Before parent.getCars"

    println cars*.name

    println "Before removeFromCars(car2)"
    parent.removeFromCars(car2)
    println "After removeFromCars(car2)"

    println "Before count of Parent and Car objects and the parents cars"
    assertEquals 1, Parent.count
    assertEquals 3, Car.count
    assertEquals 2, parent.getCars().size()
    println "After count of Parent and Car objects"

    println "Before removeAllCars()"
    parent.removeAllCars()
    println "After removeAllCars()"

    println "Before count of Parent and Car objects and the parents cars"
    assertEquals 1, Parent.count
    assertEquals 3, Car.count
    assertEquals 0, parent.getCars().size()
    println "After count of Parent and Car objects"

    // add a new list of cars
    List carList2 = []
    4.times { i ->
      carList2 << new Car(name: "Car $i")
    }

    println "Before setCars with a list of 4 new cars and zero existing cars"
    parent.setCars(carList2)
    println "After setCars with a list of 4 new cars"

    println "Before count of Parent and Car objects and the parents cars"
    assertEquals 1, Parent.count
    assertEquals 7, Car.count  // 7 = 3 cars from the previous test and 4 new cars from this one
    assertEquals 4, parent.getCars().size()
    println "After count of Parent and Car objects"

    List carList3 = []
    2.times { i ->
      carList3 << new Car(name: "Car $i")
    }

    println "Before setCars with a list of 2 new cars and 4 existing cars"
    parent.setCars(carList3)
    println "After setCars with a list of 2 new cars"

    println "Before count of Parent and Car objects and the parents cars"
    assertEquals 1, Parent.count
    assertEquals 9, Car.count  // 7 = 3 cars from the previous test + 4 new cars from this one + 2
    assertEquals 2, parent.getCars().size()
    println "After count of Parent and Car objects"

    println "Before deleteAll cars associated with parent"
    parent.deleteAllCars()
    println "After deleteAll cars associated with parent"


    println "Before count of Parent and Car objects and the parents cars"
    assertEquals 1, Parent.count
    assertEquals 7, Car.count  // 7 = 3 cars from the previous test and 4 new cars from this one
    assertEquals 0, parent.getCars().size()
    println "After count of Parent and Car objects"


  }
}
