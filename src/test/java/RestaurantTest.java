import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RestaurantTest {
    Restaurant restaurant;
   
   //REFACTORED CODE BELOW
	
	@BeforeEach
    public void beforeEachTest(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        //Arrange
        Restaurant restaurantMock = Mockito.spy(restaurant);
        Mockito.doReturn(LocalTime.parse("11:30:00")).when(restaurantMock).getCurrentTime();

        //Act
        Boolean restaurantOpen = restaurantMock.isRestaurantOpen();

        //Assert
        assertTrue(restaurantOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        //Arrange
        Restaurant restaurantMock = Mockito.spy(restaurant);
        Mockito.doReturn(LocalTime.parse("23:30:00")).when(restaurantMock).getCurrentTime();

        //Act
        Boolean restaurantOpen = restaurantMock.isRestaurantOpen();

        //Assert
        assertTrue(restaurantOpen);
    }



    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>Food Order Cost<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void not_selecting_any_food_items_from_menu_should_display_order_cost_as_zero() {

        //Act
        int orderValue = restaurant.displayOrderValue((ArrayList<String>) restaurant.selectedFoodItem);

        //Assert
        assertEquals(0,orderValue);
    }


    @Test
    public void selecting_food_items_from_menu_should_display_order_cost_as_438() {

        //Arrange
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Sizzling brownie",319);

        restaurant.addToOrder("Sizzling brownie");
        restaurant.addToOrder("Sweet corn soup");

        //Act
        int orderValue = restaurant.displayOrderValue((ArrayList<String>) restaurant.selectedFoodItem);

        //Assert
        assertEquals(438,orderValue);
    }


    @Test
    public void removing_food_item_from_order_should_display_reduced_cost_as_119() {

        //Arrange
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Sizzling brownie",319);


        restaurant.addToOrder("Sizzling brownie");
        restaurant.addToOrder("Sweet corn soup");
        restaurant.removeFromOrder("Sizzling brownie");

        //Act
        int orderValue = restaurant.displayOrderValue((ArrayList<String>) restaurant.selectedFoodItem);

        //Assert
        assertEquals(119,orderValue);

    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>Food Order Cost<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
}