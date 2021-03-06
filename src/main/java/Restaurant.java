import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    public List<String> selectedFoodItems = new ArrayList<String>();
    private int orderValue;

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {

        LocalTime currentTime = getCurrentTime();

        int currentAndOpenTimeCompareValue = currentTime.compareTo(openingTime);
        int currentAndCloseTimeCompareValue = currentTime.compareTo(closingTime);

        if (currentAndOpenTimeCompareValue >= 0 && currentAndCloseTimeCompareValue <= 0)
            return true;
        else
            return false;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
        
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public void addToOrder(String foodName) {
        selectedFoodItems.add(foodName);
    }

    public void removeFromOrder(String foodName)  {
        selectedFoodItems.remove(foodName);
    }

    public int displayOrderValue(ArrayList<String> selectedFoodItems) {
        for (String foodName : selectedFoodItems) {
            for (Item item : menu) {
                if (item.getName().equals(foodName))
                    orderValue = orderValue + item.getPrice();
            }
        }
        System.out.println("Your order will cost: Rs." + orderValue);
        return orderValue;
    }


}
