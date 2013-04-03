package com.bluelobsterstudios

import com.bluelobsterstudios.groovy.UnmanagedBagGenerator
import org.junit.After
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertEquals

class VendingMachineIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testAddFoodCollectionToVendingMachine() {
        UnmanagedBagGenerator.generateUnmanagedBagMethods(VendingMachine,'id','foodItems','ownerId',FoodItem)

        VendingMachine vendingMachine = new VendingMachine()
        vendingMachine.name = "food-o-matic"
        vendingMachine.save()

        10.times { i ->
            vendingMachine.addToFoodItems( new FoodItem(name: "foodItem_$i"))
        }

        assertEquals 1, VendingMachine.count
        assertEquals 10, FoodItem.count
        VendingMachine vendingMachine1 = VendingMachine.findById(vendingMachine.id)
        assertEquals 10, vendingMachine1.getFoodItems().size()

    }
}
