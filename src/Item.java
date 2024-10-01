public class Item {
    String itemName;
    String longName;

    public Item(String itemName, String longName) {
        this.itemName = itemName;
        this.longName = longName;

    }

    //GETMETHODS-------------------------------------------------------------------------------------------------------

    public String getItemName() {
        return itemName;
    }

    public String getItemLongName() {
        return longName;
    }
    //SETMETHODS-----------------------------------------------------------------------------------------------------------


    @Override
    public String toString() {
        return itemName;
    }
}
