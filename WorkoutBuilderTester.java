//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Workout Builder
// Course: CS 300 Fall 2023
//
// Author: Sungkar Bolat
// Email: bolat@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:
// Partner Email:
// Partner Lecturer's Name:
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:
// Online Sources:
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;

/**
 * Simple class to test WorkoutBuilder methods and demo
 */
public class WorkoutBuilderTester {

  /**
   * checks for the correctness of the WorkoutBuilder.clear() method
   * 
   * @return true - if WorkoutBuilder clear method works correctly
   */
  public static boolean testClear() {
    // Creating random test case with WorkoutBuilder
    WorkoutBuilder test = new WorkoutBuilder();
    test.add(new Exercise(WorkoutType.WARMUP, "Run 5 minutes"));
    test.add(new Exercise(WorkoutType.COOLDOWN, "Watch jjk"));
    test.add(new Exercise(WorkoutType.PRIMARY, "Swim 40 minutes"));
    Exercise.resetIDNumbers();
    // Clearing out our test
    test.clear();
    // Checking if it is empty
    return test.isEmpty();
  }

  /**
   * checks for the correctness of the WorkoutBuilder.add() method
   * 
   * @return true - if WorkoutBuilder add method works correctly
   */
  public static boolean testAddExercises() {
    // Creating random test case with WorkoutBuilder
    Exercise firstExercise = new Exercise(WorkoutType.WARMUP, "Run 5 minutes");
    Exercise secondExercise = new Exercise(WorkoutType.PRIMARY, "Watch jjk");
    Exercise thirdExercise = new Exercise(WorkoutType.COOLDOWN, "Swim 40 minutes");
    Exercise.resetIDNumbers();

    WorkoutBuilder test = new WorkoutBuilder();
    test.add(thirdExercise);
    test.add(firstExercise);
    test.add(secondExercise);

    // Checking the output of our result vs expected
    String result = test.toString();
    String expected =
        "WARMUP: Run 5 minutes (1) -> PRIMARY: Watch jjk (2) -> COOLDOWN: Swim 40 minutes (3) -> END";

    return result.equals(expected);
  }

  /**
   * checks for the correctness of BOTH of the WorkoutBuilder.removeExercise() methods
   * 
   * @return true - if WorkoutBuilder remove method works correctly for both two types
   */
  public static boolean testRemoveExercises() {
    // Creating random test case with WorkoutBuilder
    Exercise firstExercise = new Exercise(WorkoutType.WARMUP, "Run 5 minutes");
    Exercise secondExercise = new Exercise(WorkoutType.PRIMARY, "Watch jjk");
    Exercise thirdExercise = new Exercise(WorkoutType.COOLDOWN, "Swim 40 minutes");
    Exercise.resetIDNumbers();

    WorkoutBuilder test = new WorkoutBuilder();
    test.add(thirdExercise);
    test.add(firstExercise);
    test.add(secondExercise);

    // Checking implementation of remove method with specific ID
    {
      test.removeExercise(2);
      String result = test.toString();
      String expected = "WARMUP: Run 5 minutes (1) -> COOLDOWN: Swim 40 minutes (3) -> END";
      if (!result.equals(expected)) {
        return false;
      }
    }
    // Checking implementation of remove method with specific WorkoutType
    {
      test.add(secondExercise);
      test.removeExercise(WorkoutType.WARMUP);
      String result = test.toString();
      String expected = "PRIMARY: Watch jjk (2) -> COOLDOWN: Swim 40 minutes (3) -> END";
      if (!result.equals(expected)) {
        return false;
      }
    }

    return true;
  }


  /**
   * checks for the correctness of the WorkoutBuilder.get() method
   * 
   * @return true - if WorkoutBuilder get method works correctly
   */
  public static boolean testGetExercises() {
    // Creating random test case with WorkoutBuilder
    Exercise firstExercise = new Exercise(WorkoutType.WARMUP, "Run 5 minutes");
    Exercise secondExercise = new Exercise(WorkoutType.PRIMARY, "Watch jjk");
    Exercise thirdExercise = new Exercise(WorkoutType.COOLDOWN, "Swim 40 minutes");
    Exercise.resetIDNumbers();

    WorkoutBuilder test = new WorkoutBuilder();
    test.add(thirdExercise);
    test.add(firstExercise);
    test.add(secondExercise);

    // Checking result vs expected
    Exercise result = test.get(1);
    Exercise expected = secondExercise;

    return result.equals(expected);
  }

  /**
   * a test suite method to run all your test methods
   * 
   * @return true - if all methods work well as expected
   */
  public static boolean runAllTests() {
    boolean clear = testClear(), add = testAddExercises(), remove = testRemoveExercises(),
        get = testGetExercises();

    System.out.println("test clear: " + (clear ? "pass" : "FAIL"));
    System.out.println("test add: " + (add ? "pass" : "FAIL"));
    System.out.println("test remove: " + (remove ? "pass" : "FAIL"));
    System.out.println("test get: " + (get ? "pass" : "FAIL"));

    // TODO: add calls to any other test methods you write

    return clear && add && remove && get; // TODO: replace this with the correct value
  }

  /**
   * a method to run method tests and our demo from P07 overview
   * 
   * @param args - not used
   */
  public static void main(String[] args) {
    runAllTests();
    demo();
  }

  /**
   * Helper method to display the size and the count of different boxes stored in a list of boxes
   * 
   * @param list a reference to an InventoryList object
   * @throws NullPointerException if list is null
   */
  private static void displaySizeCounts(WorkoutBuilder list) {
    System.out.println(
        "  Size: " + list.size() + ", warmupCount: " + list.getWarmupCount() + ", primaryCount: "
            + list.getPrimaryCount() + ", cooldownCount: " + list.getCooldownCount());
  }

  /**
   * Demo method showing how to use the implemented classes in P07 Inventory Storage System
   */
  public static void demo() {
    // Create a new empty WorkoutBuilder object
    WorkoutBuilder list = new WorkoutBuilder();
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // Add a primary exercise to an empty list
    list.add(new Exercise(WorkoutType.PRIMARY, "5k run")); // adds PRIMARY: 5k run (1)
    System.out.println(list); // prints list's content
    list.add(new Exercise(WorkoutType.WARMUP, "stretch")); // adds WARMUP: stretch (2) at the head
                                                           // of the list
    System.out.println(list); // prints list's content
    list.add(new Exercise(WorkoutType.PRIMARY, "bench press")); // adds PRIMARY: bench press (3)
    System.out.println(list); // prints list's content
    list.add(new Exercise(WorkoutType.WARMUP, "upright row")); // adds WARMUP: upright row (4) at
                                                               // the head of the list
    System.out.println(list); // prints list's content
    list.add(new Exercise(WorkoutType.WARMUP, "db bench")); // adds WARMUP: db bench (5) at the head
                                                            // of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // Add more exercises to list and display its contents
    list.add(new Exercise(WorkoutType.COOLDOWN, "stretch")); // adds COOLDOWN: stretch (6) at the
                                                             // end of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.add(new Exercise(WorkoutType.COOLDOWN, "sit-ups")); // adds COOLDOWN: sit-ups (7) at the
                                                             // end of the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: sit-ups (7) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.add(new Exercise(WorkoutType.PRIMARY, "deadlift")); // adds PRIMARY: deadlift (8)
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: stretch (6) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(WorkoutType.WARMUP); // removes WARMUP: db bench (5)
    System.out.println(list); // prints list's content
    list.removeExercise(3); // removes PRIMARY: bench press (3) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    try {
      list.removeExercise(25); // tries to remove box #25
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    // remove all warm-ups
    while (list.getWarmupCount() != 0) {
      list.removeExercise(WorkoutType.WARMUP);
    }
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(1); // removes PRIMARY: 5k run (1) from the list -> empty list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.add(new Exercise(WorkoutType.COOLDOWN, "walk")); // adds COOLDOWN: walk (9) to the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(8); // removes PRIMARY: deadlift (8) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: walk (9) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.add(new Exercise(WorkoutType.WARMUP, "pull-up")); // adds WARMUP: pull-up (10) to the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
    list.removeExercise(10); // removes WARMUP: pull-up (10) from the list
    System.out.println(list); // prints list's content
    displaySizeCounts(list); // displays list's size and counts
  }

}
