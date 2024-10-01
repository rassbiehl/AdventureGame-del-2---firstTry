/* CLASS RESPONSIBILITY: Playerklassen er selve spilleren. Den kender sin egen position klassen Map,
og den sporer sit nuværende rum, og kontrollerer sine bevægelser i forhold til Map */

import java.util.ArrayList;

public class Player {
    private String userName;
    private Room currentRoom;
    private ArrayList<Item> inventory;

    /*Constructor. You can't decide the player's current room from start,
    since the player-class is not directly linked to the mapclass. That's why i set startingRoom as a parameter for later use.*/
    public Player(Room startingRoom) {
        currentRoom = startingRoom;
        inventory = new ArrayList<>();
        currentRoom.setVisitedBefore();
    }

    // GETMETHODS--------------------------------------------------------------------------------------------------------
    /* Gets the currentRoom for player. the player's currentRoom is initialized in adventureClass, and in order to
    use it, i have to create a get method for it in this class.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    //gets username for a player.
    public String getUserName() {
        return userName;
    }

    //gets the player's inventory
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    // SETMETHODS--------------------------------------------------------------------------------------------------------

    //setMethod for changing playername.
    public void setPlayerName(String newName) {
        this.userName = newName;
    }

    /*setMethod for adding new items to the playerItem arraylist and
    removing the item from the currentrooms roomitem arraylist.*/
    public boolean addItem(String input) {
        for (Item item : currentRoom.getRoomItems()) {
            if (item.getItemName().equalsIgnoreCase(input.toLowerCase())) {
                inventory.add(item);
                currentRoom.getRoomItems().remove(item);
                currentRoom.updateRoomDescription();
                return true;
            }
        }
        return false;
    }

    /*setMethod for removing new items to the roomITem arraylist and
    removing the item from the currentrooms roomitem arraylist.*/
    public boolean removeItem(String input) {

        for (Item item : inventory) {
            if (item.getItemName().equalsIgnoreCase(input.toLowerCase())) {
                inventory.remove(item);
                currentRoom.getRoomItems().add(item);
                currentRoom.updateRoomDescription();
                return true;
            }
        }
        return false;
    }


    /* Keeps track of the player's currentRoom based on userChoice (scanner-string). It makes most sense to do it in
    this class, as it holds all the information needed in relation to the player's currentRoom;*/
    public boolean changeCurrentRoom(String direction) {
        Room nextRoom = switch (direction) {
            case "go north", "go n", "north", "n" -> currentRoom.getNorth();
            case "go south", "go s", "south", "s" -> currentRoom.getSouth();
            case "go east", "go e", "east", "e" -> currentRoom.getEast();
            case "go west", "west", "w" -> currentRoom.getWest();
            default -> null;
        };

        if (nextRoom != null) {
            currentRoom = nextRoom;
            return true;
        } else {
            return false;
        }

    }


}

