/* CLASS RESPONSIBILITY: this class handles the user's input and will inform the user about the game (output)*/

import java.util.Scanner;


public class UI {
    private Adventure adventure;
    private Scanner scanner;


    public UI() {
        adventure = new Adventure();
        scanner = new Scanner(System.in);
    }


    public void startGame() {
        gameIntro(); // launches the intro.
        boolean gameOver = false;
        while (!gameOver) { //
            System.out.println("Now, what will you do?");
            String input = scanner.nextLine().trim().toLowerCase();
            gameOver = handleInput(input); /*i put the method handleinput in gameover, and if it returns true ("exit" is pressed)
          the game will end.*/
        }
    }

    public boolean handleInput(String input) {
        switch (input) {
            case "help" -> userGuide();
            case "look" -> lookAround();
            case "move" -> move();
            case "inventory" -> showInventory();
            case "take" -> takeItem();
            case "drop" -> dropItem();
            case "find" -> lookForItem();
            case "exit" -> {
                System.out.println("You have now quit the game.");
                return true;
            }
            default -> System.out.println("Unvalid input. Try again.");
        }
        return false;
    }


    //prints the userguide.
    public void userGuide() {
        System.out.println("\"help\" - get instructions and an overview of possible commands.");
        System.out.println("\"look\" - get a description of the room you are currently in.");
        System.out.println("\"move\" - with your compass, move to a new room in a path of your choice.");
        System.out.println("\"inventory\" - provides a list of all the items in your inventory.");
        System.out.println("\"take\" - pick up an item and add it to your inventory.");
        System.out.println("\"drop\" - drop an item and remove it from your inventory.");
        System.out.println("\"find\" - find out if a given item is in your current room");
        System.out.println("\"exit\" - quit the game.");
    }

    // everytime this command is used the player will get  the currentrooms extended description.
    public void lookAround() {
        System.out.println("*Looks around* " + adventure.getPlayerCurrentRoom().getExtendedDescription());
    }

    public void lookForItem() {
        System.out.println("What are you looking for?");
        String input = scanner.nextLine().trim();
        Item foundItem = adventure.getPlayer().getCurrentRoom().findItem(input);

        if (foundItem == null) {
            System.out.println("There is no such item in the room.");
        } else {
            System.out.println(foundItem.getItemName() + " was found in the current room!");
        }

    }

    public void move() {
        if (adventure.hasCompass()) {
            System.out.println("In which direction do you want to go? (N, S, E, W or C to cancel)");
            String direction = scanner.nextLine().trim().toLowerCase();
            boolean roomChange = adventure.changePlayerRoom(direction);
            if (!roomChange) {
                System.out.println("You can't go that way! You must choose another path");
            } else if (!adventure.getPlayerCurrentRoom().getVisitedBefore()) {
                System.out.println(adventure.getPlayerCurrentRoom());
                adventure.setPlayerCurrentRoomVisitedBefore(); //Changes visitedBefore to true for every new room.
            }
        } else {
            System.out.println("You must need a compass, if you want to continue to another room. Use your eyes.");
        }
    }

    //this method prints out the player's inventory and it's sorted in number-rows. if no items, it will inform the player.
    public void showInventory() {
        if (!adventure.getPlayer().getInventory().isEmpty()) {
            System.out.println("Inventory:");
            for (int i = 0; i < adventure.getPlayer().getInventory().size(); i++) {
                System.out.println(i + 1 + ". " + adventure.getPlayer().getInventory().get(i));

            }
        } else {
            System.out.println("As of right now, you do not have any items in your inventory.");
        }

    }

    /*This method let's the player ADD an item to the inventory, and this automatically removes
    the item from the currentrooms roomitems-arraylist.*/
    public void takeItem() {
        System.out.println("Which item do you want to take?");
        String input = scanner.nextLine();
        boolean inventoryChange = adventure.getPlayer().addItem(input);
        if (!inventoryChange) {
            System.out.println("\"" + input + "\"cannot be found in the current room.");
        } else {
            System.out.println("You have now added " + adventure.getPlayer().getInventory().get(adventure.getPlayer().
                    getInventory().size() - 1).getItemLongName() + " to your inventory."); //prints out last item in inventory.
        }
    }

    /*This method let's the player REMOVE an item to the inventory, and this automatically adds
the item to the currentrooms roomitems-arraylist.*/
    public void dropItem() {
        if (!adventure.getPlayerInventory().isEmpty()) { // making sure that inventory is not empty, if you want to drop something.
            System.out.println("Which item do you want to drop?");
            String input = scanner.nextLine();
            boolean inventoryChange = adventure.getPlayer().removeItem(input);
            if (!inventoryChange) {
                System.out.println("\"" + input + "\" cannot be found in your inventory");
            } else {
                System.out.println("You have now removed " + input + " from your inventory");
            }
        } else {
            System.out.println("Your inventory is empty!");
        }
    }


    //gives the player an introduction. is used at the start of the game.
    public void gameIntro() {
        System.out.println("Choose your username:");
        String newUserName = scanner.nextLine();
        adventure.setPlayerName(newUserName);
        System.out.println("Welcome " + adventure.getPlayerName() + ".");
        System.out.println("You are now at the Starting Point. \n");
        System.out.println("Here is a guide, to help you carry through this game: \n");
        userGuide();
    }


}


