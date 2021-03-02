package Library;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    private static Project project = new Project();
    public static void main(String[] args) {

        project.run();
        play();
    }

    private static void play() {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;

        while (!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1:
                    project.printAllBooks();
                    break;
                case 2:
                    project.printAllAuthors();
                    break;
                case 3:
                     break;
                case 4:
                    quit = true;
                    break;
            }
//        }
        }
    }

        public static void printMenu() {
            System.out.println("Welcome to the library!");
            System.out.println("Search for a desired book of yours:");
            System.out.println("Find: \n" +
                    "1. See the list of all books. \n" +
                    "2. See the list of all authors. \n" +
                    "3. Find the book according critereous. \n");
        }
    }


/*

    - DB
    - BACK
        - Entity
        - Bendravimas su DB (DAO)
        - Service layer
        - Presentation layer <- consoles valdymas

 */

