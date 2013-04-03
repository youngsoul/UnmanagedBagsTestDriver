package com.bluelobsterstudios

import org.junit.After
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*

class AnnotatedCollectionsIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testSimpleAddToAnimals() {

        Parent parent = new Parent()
        parent.name = "test parent"
        parent.save()

        Pet pet = new Pet()
        pet.name = "damn dog"
        parent.addToAnimals( pet )

        Pet pet2 = new Pet()
        pet2.name = "stupid cat"
        parent.addToAnimals( pet2 )

        assertEquals 1, Parent.count
        assertEquals 2, Pet.count

        Parent parent1 = Parent.findById(parent.id)
        List<Pet> pets = parent1.getAnimals()

        assertNotNull parent1
        assertNotNull pets
        assertEquals 2, pets.size()
        assertTrue pets*.ownerFkId.contains(parent1.id)

    }


    @Test
    void testSimpleAddToMyKids() {

        Parent parent = new Parent()
        parent.name = "test parent"
        parent.save()

        Child child = new Child()
        child.name = "test child of test parent"
        parent.addToMyKids( child )

        assertEquals 1, Parent.count
        assertEquals 1, Child.count

        Parent parent1 = Parent.findById(parent.id)
        Child child1 = Child.findById(child.id)

        assertNotNull parent1
        assertNotNull child1
        assertEquals parent1.id, child1.ownerId


    }

    @Test
    void testSimpleAddToCars() {
        Parent parent = new Parent()
        parent.name = "test parent"
        parent.save()


        com.bluelobsterstudios.Car car1 = new com.bluelobsterstudios.Car()
        car1.name = "fast"

        parent.addToCars( car1 )

        com.bluelobsterstudios.Car car2 = new com.bluelobsterstudios.Car()
        car2.name = "mini van"
        parent.addToCars(car2)

        com.bluelobsterstudios.Car car3 = new com.bluelobsterstudios.Car()
        car3.name = "gig car"
        parent.addToCars(car3)

        assertEquals 1, Parent.count
        assertEquals 3, com.bluelobsterstudios.Car.count

        List<com.bluelobsterstudios.Car> cars = parent.getCars()
        assertNotNull cars
        assertEquals 3, cars.size()

        println cars*.name


    }

    @Test
    void testSimpleRemoveFromMyKids() {
        Parent parent = new Parent()
        parent.name = "test parent"
        parent.save()

        Child child = new Child()
        child.name = "test child of test parent"
        parent.addToMyKids( child )

        assertEquals 1, Parent.count
        assertEquals 1, Child.count

        parent.removeFromMyKids( child )
        assertNull child.ownerId
        assertEquals 1, Parent.count
        assertEquals 1, Child.count



    }

    @Test
    void testSimpleGetMyKids() {

        Parent parent = new Parent()
        parent.name = "test parent"
        parent.save()

        Child child = new Child()
        child.name = "test child of test parent"
        parent.addToMyKids( child )

        assertEquals 1, Parent.count
        assertEquals 1, Child.count

        List<Child> children = parent.getMyKids()
        assertNotNull children
        assertEquals(1, children.size())
        assertEquals child.id, children.get(0).id
        assertEquals parent.id, children.get(0).ownerId

    }


    @Test
    void testSimpleSetMyKids() {

        Parent parent = new Parent()
        parent.name = "test parent"
        parent.save()

        List<Child> kids = []

        10.times { i ->
            Child child = new Child()
            child.name = "child name $i"
            kids << child
        }
        parent.setMyKids( kids )


        assertEquals 1, Parent.count
        assertEquals 10, Child.count

        Parent parent1 = Parent.findById(parent.id)
        assertNotNull parent1
        assertNotNull parent1.getMyKids()
        assertEquals 10, parent1.getMyKids().size()

        List<Child> kids2 = []
        5.times { i ->
            Child child = new Child()
            child.name = "child name $i"
            kids2 << child
        }
        parent.setMyKids( kids2 )


        assertEquals 1, Parent.count
        assertEquals 15, Child.count  // 15 because we do not delete the original 10, just disassociate
        Parent parent2 = Parent.findById(parent.id)
        assertNotNull parent2
        assertNotNull parent2.getMyKids()
        assertEquals 5, parent2.getMyKids().size()


    }

    @Test
    void testRemoveFromMyKids() {

        Parent parent = new Parent()
        parent.name = "test parent"
        parent.save()

        List<Child> kids = []

        10.times { i ->
            Child child = new Child()
            child.name = "child_$i"
            parent.addToMyKids(child)
        }

        assertEquals 1, Parent.count
        assertEquals 10, Child.count

        Parent parent1 = Parent.findById(parent.id)
        assertNotNull parent1
        assertNotNull parent1.getMyKids()
        assertEquals 10, parent1.getMyKids().size()

        Child child5 = Child.findByName('child_5')
        assertNotNull child5
        parent1.removeFromMyKids(child5)

        assertEquals 1, Parent.count
        assertEquals 10, Child.count
        assertEquals 9, parent1.getMyKids().size()
        child5.delete()
        assertEquals 1, Parent.count
        assertEquals 9, Child.count
        assertEquals 9, parent1.getMyKids().size()

    }

    @Test
    void testSimpleSetWithDeleteMyKids() {

        Parent parent = new Parent()
        parent.name = "test parent"
        parent.save()

        List<Child> kids = []

        10.times { i ->
            Child child = new Child()
            child.name = "child name $i"
            kids << child
        }
        parent.setMyKids( kids )


        assertEquals 1, Parent.count
        assertEquals 10, Child.count

        parent.deleteAllMyKids()

        Parent parent1 = Parent.findById(parent.id)
        assertNotNull parent1
        assertNotNull parent1.getMyKids()
        assertEquals 0, parent1.getMyKids().size()

        List<Child> kids2 = []
        5.times { i ->
            Child child = new Child()
            child.name = "child name $i"
            kids2 << child
        }
        parent.setMyKids( kids2 )


        assertEquals 1, Parent.count
        assertEquals 5, Child.count  //5 because we do  delete the original 10
        Parent parent2 = Parent.findById(parent.id)
        assertNotNull parent2
        assertNotNull parent2.getMyKids()
        assertEquals 5, parent2.getMyKids().size()


    }


    @Test
    void testKidsAndHobbies() {

        Parent parent = new Parent()
        parent.name = "test parent"
        parent.save()

        List<Child> kids = []

        10.times { i ->
            Child child = new Child()
            child.name = "child name $i"
            kids << child
        }
        parent.setMyKids( kids )

        List<Hobby> hobbies = []
        23.times { i ->
            Hobby hobby = new Hobby()
            hobby.name = "hobby name $i"
            hobbies << hobby
        }
        parent.setMyHobbies( hobbies)

        assertEquals 1, Parent.count
        assertEquals 10, Child.count
        assertEquals 23, Hobby.count

        Parent parent1 = Parent.findById(parent.id)
        assertNotNull parent1
        assertNotNull parent1.getMyKids()
        assertNotNull parent1.getMyHobbies()
        assertEquals 10, parent1.getMyKids().size()
        assertEquals 23, parent1.getMyHobbies().size()

        try {
            parent1.removeFromMyKids( hobbies[3])
            fail("should not try to remove the hobbies class because it is the wrong type")
        } catch(Exception e ) {
            assertTrue true
            println e.message
        }

        try {
            parent1.removeFromHobbies( kids[3])
            fail("should not try to remove the kids class because it is the wrong type")
        } catch(Exception e ) {
            assertTrue true
            println e.message
        }


    }

    @Test
    void testAddAllToMyKids() {

        Parent parent = new Parent()
        parent.name = "test parent"
        parent.save()

        List<Child> kids = []

        10.times { i ->
            Child child = new Child()
            child.name = "child name $i"
            kids << child
        }
        parent.addAllToMyKids( kids )


        assertEquals 1, Parent.count
        assertEquals 10, Child.count

        Parent parent1 = Parent.findById(parent.id)
        assertNotNull parent1
        assertNotNull parent1.getMyKids()
        assertEquals 10, parent1.getMyKids().size()


    }

    @Test
    void testAddAllToMyKidsBadCollection() {

        Parent parent = new Parent()
        parent.name = "test parent"
        parent.save()

        List<Object> kids = []

        10.times { i ->
            Child child = new Child()
            child.name = "child name $i"
            kids << child
        }
        kids << new Hobby(name: "kids are not hobbies")


        try {
            parent.addAllToMyKids( kids )
            fail("Should have failed because of invalid type in kids collection")
        } catch (Exception e) {
            assertEquals 1, Parent.count
            assertEquals 10, Child.count

            Parent parent1 = Parent.findById(parent.id)
            assertNotNull parent1
            assertNotNull parent1.getMyKids()
            assertEquals 10, parent1.getMyKids().size()

        }




    }

}
