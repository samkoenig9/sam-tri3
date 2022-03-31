package src;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import src.Week0.*;
import src.Week1.*;
import src.Week2.*;
/**
 * Menu: custom implementation
 * @author     John Mortensen
 *
 * Uses String to contain Title for Option
 * Uses Runnable to store Class-Method to be run when Title is selected
 */

// The Menu Class has a HashMap of Menu Rows
public class Menu {
    // Format
    // Key {0, 1, 2, ...} created based on order of input menu
    // Value {MenuRow0, MenuRow1, MenuRow2,...} each corresponds to key
    // MenuRow  {<Exit,Noop>, Option1, Option2, ...}
    Map<Integer, MenuRow> menu = new HashMap<>();

    /**
     *  Constructor for Menu,
     *
     * @param  rows,  is the row data for menu.
     */
    public Menu(MenuRow[] rows) {
        int i = 0;
        for (MenuRow row : rows) {
            // Build HashMap for lookup convenience
            menu.put(i++, new MenuRow(row.getTitle(), row.getAction()));
        }
    }

    /**
     *  Get Row from Menu,
     *
     * @param  i,  HashMap key (k)
     *
     * @return  MenuRow, the selected menu
     */
    public MenuRow get(int i) {
        return menu.get(i);
    }

    /**
     *  Iterate through and print rows in HashMap
     */
    public void print() {
        for (Map.Entry<Integer, MenuRow> pair : menu.entrySet()) {
            System.out.println(pair.getKey() + ": " + pair.getValue().getTitle());
        }
    }

    /**
     *  To test run Driver
     */
    public static void main(String[] args) {
        Driver.main(args);
    }

}

// The MenuRow Class has title and action for individual line item in menu
class MenuRow {
    String title;       // menu item title
    Runnable action;    // menu item action, using Runnable

    /**
     *  Constructor for MenuRow,
     *
     * @param  title,  is the description of the menu item
     * @param  action, is the run-able action for the menu item
     */
    public MenuRow(String title, Runnable action) {
        this.title = title;
        this.action = action;
    }

    /**
     *  Getters
     */
    public String getTitle() {
        return this.title;
    }
    public Runnable getAction() {
        return this.action;
    }

    /**
     *  Runs the action using Runnable (.run)
     */
    public void run() {
        action.run();
    }
}

// The Main Class illustrates initializing and using Menu with Runnable action
class Driver {
    /**
     *  Menu Control Example
     */
    public static void main(String[] args) {
        // Row initialize
        MenuRow[] rows = new MenuRow[]{
                new MenuRow("Go Back", () -> main(null)),
                new MenuRow("Week 0 (Data Structures)", () -> Menu0.main(null)), 
                new MenuRow("Week 1 (Linked Lists)", () -> Menu1.main(null)),
          new MenuRow("Week 2 (Calculator)", () -> Menu2.main(null)),
          // lambda style, () -> to point to Class.Method
        };

        // Menu construction
        Menu menu = new Menu(rows);

        // Run menu forever, exit condition contained in loop
        while (true) {
            System.out.println("Select Something");
            // print rows
            menu.print();

            // Scan for input
            try {
                Scanner sc = new Scanner(System.in);
                int selection = sc.nextInt();

                // menu action
                try {
                    MenuRow row = menu.get(selection);
                    // stop menu condition
                    if (row.getTitle().equals("Go Back"))
                        return;
                    // run option
                    row.run();
                } catch (Exception e) {
                    System.out.printf("Invalid selection %d\n", selection);
                }
            } catch (Exception e) {
                System.out.println("Not a number");
            }
        }
    }
}
