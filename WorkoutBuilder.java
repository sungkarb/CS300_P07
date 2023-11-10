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
 * This class uses a singly-linked list data structure to maintain a list of exercises organized
 * according to their WorkoutType.
 */
public class WorkoutBuilder implements ListADT<Exercise> {
  /** The node containing the element at index 0 of this singly-linked list */
  private LinkedExercise head;
  /** The node containing the last element of this singly-linked list */
  private LinkedExercise tail;
  /** The total number of exercises contained in this WorkoutBuilder list */
  private int size;
  /** The number of exercises with WorkoutType equal to WARMUP in this WorkoutBuilder list */
  private int warmupCount;
  /** The number of exercises with WorkoutType equal to PRIMARY in this WorkoutBuilder list */
  private int primaryCount;
  /** The number of exercises with WorkoutType equal to COOLDOWN in this WorkoutBuilder list */
  private int cooldownCount;

  /**
   * Accesses the total number of elements in this WorkoutBuilder list
   * 
   * @return the size of this list
   */
  public int size() {
    return this.size;
  }

  /**
   * Accesses the number of warm-up exercises stored in this WorkoutBuilder list
   * 
   * @return the count of exercises with WorkoutType equal to WARMUP
   */
  public int getWarmupCount() {
    return this.warmupCount;
  }

  /**
   * Accesses the number of primary exercises stored in this WorkoutBuilder list
   * 
   * @return the count of exercises with WorkoutType equal to PRIMARY
   */
  public int getPrimaryCount() {
    return this.primaryCount;
  }

  /**
   * Accesses the number of cool-down exercises stored in this WorkoutBuilder list
   * 
   * @return the count of exercises with WorkoutType equal to COOLDOWN
   */
  public int getCooldownCount() {
    return this.cooldownCount;
  }

  /**
   * Checks whether this WorkoutBuilder list is empty
   * 
   * @return true if this list contains no elements and neither its head nor tail refer to
   *         LinkedExercise objects
   */
  public boolean isEmpty() {
    return (size == 0 && head == null && tail == null);
  }

  /**
   * Removes all elements from this list. The list will be empty after this call returns.
   */
  public void clear() {
    this.size = 0;
    this.head = null;
    this.tail = null;
  }

  /**
   * Finds the index of a given exercise in this WorkoutBuilder list, if it is present. Note that
   * Exercise contains an overridden equals() method for use here.
   * 
   * @param findObject - the exercise to search for in this list
   * @return the index of this object in the list if it is present; -1 if it is not
   */
  public int indexOf(Exercise findObject) {
    // Traversing through our linked list
    LinkedExercise current = head;
    int index = 0;
    while (current != null) {
      if (current.equals(findObject)) {
        return index;
      }
      current = current.getNext();
      index = index + 1;
    }
    return -1;
  }

  /**
   * Returns the exercise stored at the given index of this list without removing it.
   * 
   * @param index - position within this list
   * @return the exercise stored at the given index of this list
   * @throws IndexOutOfBoundsException - with a descriptive error message if the index is not valid
   *                                   for the current size of this list
   */
  public Exercise get(int index) throws IndexOutOfBoundsException {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException("index is out of the bounds");
    }
    // Traversing through linked list to search for an object
    LinkedExercise current = head;
    int counter = 0;
    while (counter != index) {
      current = current.getNext();
      counter += 1;
    }
    return current.getExercise();
  }

  /**
   * Adds the provided Exercise to the appropriate position in the list for its WorkoutType, and
   * increments the corresponding counter fields: WARMUP: adds to the FRONT (head) of the list
   * PRIMARY: adds after all warm-ups and before any cool-downs; if there are any existing primary
   * exercises, adds after all of those as well COOLDOWN: adds to the END (tail) of the list We
   * recommend implementing private helper methods for each of these cases, but this is not
   * required. Remember to consider the case where you are adding the very first exercise to the
   * list!
   * 
   * @param newObject - the exercise to add to the WorkoutBuilder list
   */
  public void add(Exercise newObject) {
    LinkedExercise newExercise = new LinkedExercise(newObject);
    WorkoutType exerciseType = newObject.getType();

    if (exerciseType.equals(WorkoutType.WARMUP)) {
      addWarmup(newExercise);
    } else if (exerciseType.equals(WorkoutType.PRIMARY)) {
      addPrimary(newExercise);
    } else {
      addCooldown(newExercise);
    }
  }

  /**
   * Adds the warm up exercise to the beginning of our WorkoutBuilder list
   * 
   * @param exercise - the warm up exercise to add to the WorkoutBuilder list
   */
  private void addWarmup(LinkedExercise exercise) {
    // Incrementing the number of warm up exercises
    this.warmupCount += 1;
    // Increment the size of linked list
    this.size += 1;
    // Checking if linked list is empty
    if (this.size == 1) {
      this.head = exercise;
      this.tail = exercise;
      return;
    }
    // If not then our new exercise becomes head
    exercise.setNext(this.head);
    this.head = exercise;
  }

  /**
   * Adds the cooldown exercise to the end of our WorkoutBuilder list
   * 
   * @param exercise - the cooldown exercise to add to the WorkoutBuilder list
   */
  private void addCooldown(LinkedExercise exercise) {
    // Incrementing the number of cooldown exercises
    this.cooldownCount += 1;
    // Incrementing the size of linked list
    this.size += 1;
    // Checking if linked list is empty
    if (this.size == 1) {
      this.head = exercise;
      this.tail = exercise;
      return;
    }
    // If not, make our exercise a new tail
    this.tail.setNext(exercise);
    this.tail = exercise;
  }

  /**
   * Adds the primary exercise in between warm up and cooldown exercises
   * 
   * @param exercise - the primary exercise to add to the WorkoutBuilder list
   */
  private void addPrimary(LinkedExercise exercise) {
    // Incrementing the number of primary exercises
    this.primaryCount += 1;
    // Incrementing the size of linked list
    this.size += 1;
    // Checking if linked list is empty
    if (this.size == 1) {
      this.head = exercise;
      this.tail = exercise;
      return;
    }

    // Checking if there are no warmups or cooldown exercises
    LinkedExercise current = this.head;
    if (current.getExercise().getType().equals(WorkoutType.COOLDOWN)) {
      exercise.setNext(this.head);
      this.head = exercise;
      return;
    }

    // If there are, find a spot which is empty or is occupied by cooldown exercise
    while (current.getNext() != null
        && (!current.getNext().getExercise().getType().equals(WorkoutType.COOLDOWN)
            && !current.getNext().getExercise().getType().equals(WorkoutType.PRIMARY))) {
      current = current.getNext();
    }

    // If there are no cooldown exercises, make our primary exercise as tail
    if (current.getNext() == null) {
      current.setNext(exercise);
      this.tail = exercise;
    }
    // If there are, add primary exercise between cooldown exercise and warmup or primary exercise
    else {
      LinkedExercise supposedNext = current.getNext();
      current.setNext(exercise);
      exercise.setNext(supposedNext);
    }
  }

  /**
   * Removes an exercise of the provided type from the list, if one exists, and decrements the
   * corresponding counter fields: WARMUP: removes the FIRST (head) element from the list PRIMARY:
   * removes the FIRST exercise of type PRIMARY from the list COOLDOWN: removes the LAST (tail)
   * element from the list You are encouraged to implement private helper methods for each of these
   * cases as well, but this is not required. Be sure to check that there are any exercises with the
   * given type present in the list, and remember to consider the case where you are removing the
   * very last exercise from the entire list!
   * 
   * @param type - the type of exercise to remove from the list
   * @return the exercise object that has been removed from the list
   * @throws NoSuchElementException - if there are no exercises in the list with the given
   *                                WorkoutType
   */
  public Exercise removeExercise(WorkoutType type) throws NoSuchElementException {
    if (size == 0) {
      throw new NoSuchElementException("There are no exercises in workout!");
    }
    Exercise result = null;
    if (type.equals(WorkoutType.WARMUP)) {
      result = removeWarmup();
    } else if (type.equals(WorkoutType.PRIMARY)) {
      result = removePrimary();
    } else {
      result = removeCooldown();
    }
    return result;
  }

  /**
   * Removes the first warm up exercise from WorkoutBuilder
   * 
   * @throws NoSuchElementException - if there are no warm up exercises in the list
   */
  private Exercise removeWarmup() throws NoSuchElementException {
    LinkedExercise current = this.head;
    // If we have a warm up exercise, it should be our head
    if (!current.getExercise().getType().equals(WorkoutType.WARMUP)) {
      throw new NoSuchElementException("There are no warm ups to remove!");
    }
    // Decrementing the number of warm up exercises
    this.warmupCount -= 1;
    // If the size is 1, clear WorkoutBuilder
    if (size == 1) {
      this.clear();
    }
    // IF the size is > 1, change our head and decrease the size
    else {
      this.head = current.getNext();
      this.size -= 1;
    }
    return current.getExercise();
  }

  /**
   * Removes the last cooldown exercise from WorkoutBuilder
   * 
   * @throws NoSuchElementException - if there are no cooldown exercises in the list
   */
  private Exercise removeCooldown() throws NoSuchElementException {
    LinkedExercise current = this.tail;
    // If we have cooldown exercises, it should be the tail
    if (!current.getExercise().getType().equals(WorkoutType.COOLDOWN)) {
      throw new NoSuchElementException("There are no cooldowns to remove!");
    }
    // Decrementing the number of cooldown exercises
    this.cooldownCount -= 1;
    // If we have only 1 exercise, clear WorkoutBuilder
    if (size == 1) {
      this.clear();
    } else {
      // Else, we are going to update our tail
      LinkedExercise head = this.head;
      while (!head.getNext().equals(current)) {
        head = head.getNext();
      }
      this.tail = head;
      head.setNext(null);
      this.size -= 1;
    }
    return current.getExercise();
  }

  /**
   * Removes the first primary exercise from WorkoutBuilder
   * 
   * @throws NoSuchElementException - if there are no primary exercises in the list
   */
  private Exercise removePrimary() throws NoSuchElementException {
    LinkedExercise current = this.head;
    // Checking if there is only element in the WorkOutBuilder
    if (size == 1) {
      // Checking to see if there are primary exercises
      if (!current.getExercise().getType().equals(WorkoutType.PRIMARY)) {
        throw new NoSuchElementException("There are no primary exercises to remove!");
      } else {
        // If yes, then clear everything and return our head exercise;
        this.clear();
        // Decrementing the number of primary exercises
        this.primaryCount -= 1;
        return current.getExercise();
      }
    }

    // If the first element is primary, change our head
    if (current.getExercise().getType().equals(WorkoutType.PRIMARY)) {
      this.head = this.head.getNext();
      this.size -= 1;
      // Decrement number of primary exercises
      this.primaryCount -= 1;
      return current.getExercise();
    }

    // Traversing through WorkoutBuilder to find primary exercise
    while (current.getNext() != null
        && !current.getNext().getExercise().getType().equals(WorkoutType.PRIMARY)) {
      current = current.getNext();
    }

    // If there are none, display an error
    if (current.getNext() == null) {
      throw new NoSuchElementException("There are no primary exercises to remove!");
    } else {
      // If we have a primary exercise check for two cases
      LinkedExercise result = current.getNext();
      // If our exercise is the last one, update our tail
      if (result.getNext() == null) {
        current.setNext(null);
        this.tail = current;
        this.size -= 1;
      }
      // If not then just skip it
      else {
        current.setNext(current.getNext().getNext());
        this.size -= 1;
      }

      // Decrement the number of primary exercises
      this.primaryCount -= 1;
      return result.getExercise();
    }
  }

  /**
   * Removes the exercise with the provided ID number from the list, if it is present, and adjusts
   * any corresponding counter fields as necessary. This method uses a linear search algorithm.
   * 
   * @param exerciseID - the unique identifier of the exercise to be removed
   * @return the exercise object that has been removed from the list
   * @throws NoSuchElementException - if no exercises in the list match the provided exerciseID
   *                                number
   */
  public Exercise removeExercise(int exerciseID) throws NoSuchElementException {
    LinkedExercise current = this.head;

    // If there are no elements, display error message
    if (size == 0) {
      throw new NoSuchElementException("There are no elements in the workout");
    }
    if (size == 1) {
      // If our only element mathes ID, we are clearing WorkoutBuilder
      if (current.getExercise().getExerciseID() == exerciseID) {
        this.clear();
        // Resetting our count for exercises
        this.warmupCount = 0;
        this.primaryCount = 0;
        this.cooldownCount = 0;
        return current.getExercise();
      } else {
        // Display error message
        throw new NoSuchElementException("There are no elements in the workout with given ID");
      }
    }

    // If the first element,
    if (current.getExercise().getExerciseID() == exerciseID) {
      // Change our head
      this.head = this.head.getNext();
      // Decrease the size of workout and decrease number of appropriate count
      this.size -= 1;
      this.decreaseWorkoutTypeCount(current.getExercise().getType());
      return current.getExercise();
    }

    // Searching for our element
    while (current.getNext() != null
        && current.getNext().getExercise().getExerciseID() != exerciseID) {
      current = current.getNext();
    }

    // If there is no element with given ID, display error message
    if (current.getNext() == null) {
      throw new NoSuchElementException("There are no elements in the workout with given ID");
    }
    // If we have an exercise with given ID check for two cases
    LinkedExercise result = current.getNext();
    // If our exercise is the last one, update our tail
    if (result.getNext() == null) {
      current.setNext(null);
      this.tail = current;
      // Decrease the size and the appropriate count
      this.decreaseWorkoutTypeCount(result.getExercise().getType());
      this.size -= 1;
    }
    // If not then just skip it
    else {
      current.setNext(result.getNext());
      this.decreaseWorkoutTypeCount(result.getExercise().getType());
      this.size -= 1;
    }

    return result.getExercise();
  }

  /**
   * Decreases the count of warm up, cooldown, or primary exercises depending on provided type
   *
   * @param type - the type of exercises to decrease the count
   */
  private void decreaseWorkoutTypeCount(WorkoutType type) {
    if (type.equals(WorkoutType.WARMUP)) {
      this.warmupCount -= 1;
    } else if (type.equals(WorkoutType.PRIMARY)) {
      this.primaryCount -= 1;
    } else {
      this.cooldownCount -= 1;
    }
  }

  /**
   * Returns a String representation of the contents of this list, as the concatenated String
   * representations of all LinkedExercise nodes in this list. See the sample output at the end of
   * the writeup for examples.
   *
   * @return return a String representation of the content of this list. If this list is empty, an
   *         empty String ("") will be returned.
   */
  @Override
  public String toString() {
    String result = "";
    LinkedExercise current = this.head;
    while (current != null) {
      result = result + current.toString();
      current = current.getNext();
    }
    return result;
  }
}
