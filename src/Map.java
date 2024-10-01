/*CLASS RESPONSIBILITY: Map is the class that initializes the rooms and connects them.
It builds the map for the game */

public class Map {
    private Room room1, room2, room3, room4, room5, room6, room7, room8, room9;
    private Item compass, sword, book, potion, boots;

    //Constructor, that by standard links all the rooms together in the desired way with the buildWorld-method.
    public Map() {
        buildWorld();
    }

    //buildWorld-method that initializes all the rooms and links them together as neighbors.
    public void buildWorld() {
        //Initializes the rooms and sets their names and descriptions.
        room1 = new Room("the Lobby", "The welcoming area filled with cozy seating and a reception desk.");
        room2 = new Room("the Dining Room", "A spacious area where guests enjoy meals together.");
        room3 = new Room("the Library", "A quiet room lined with bookshelves and comfortable reading nooks.");
        room4 = new Room("the Casino", "A lively room filled with card tables and bright slot machines.");
        room5 = new Room("the Basement", "A dark, eerie space filled with shadows and the sound of dripping water.");
        room6 = new Room("the Conference Room", "A modern space equipped for meetings and events");
        room7 = new Room("the Cinema", "A nostalgic theater featuring classic films and vintage decor.");
        room8 = new Room("the Ball Hall", "A grand hall for dancing, adorned with chandeliers and mirrors.");
        room9 = new Room("the Rooftop Terrace", "An outdoor area with stunning views and seating for guests.");
        //Sets the rooms neighbors, so it creates a map.
        room1.setNeighbor(null, room4, room2, null);
        room2.setNeighbor(null, null, room3, room1);
        room3.setNeighbor(null, room6, null, room2);
        room4.setNeighbor(room1, room7, null, null);
        room5.setNeighbor(null, room8, null, null);
        room6.setNeighbor(room3, room9, null, null);
        room7.setNeighbor(room4, null, room8, null);
        room8.setNeighbor(room5, null, room9, room7);
        room9.setNeighbor(room6, null, null, room8);
        //create items to the the game.
        compass = new Item("Compass", "a rusty compass");
        sword = new Item("Sword", "a long sword");
        book = new Item("Book", "an old book");
        potion = new Item("Potion", "a health potion");
        boots = new Item("Boots", "boots");
        room1.addRoomItems(compass);
        room3.addRoomItems(book);
        room2.addRoomItems(sword);
        room5.addRoomItems(boots);
        room7.addRoomItems(potion);

    }

    // GETMETHODS ------------------------------------------------------------------------------------------------------

    public Room getStartingRoom() {
        return room1;
    }

    public Item getCompass() {
        return compass;
    }

}
