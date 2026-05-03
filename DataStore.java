import java.util.*;

class DataStore {

    static ArrayList<Equipment> equipmentList = new ArrayList<>();
    static ArrayList<Request> requests = new ArrayList<>();
        static ArrayList<BorrowRecord> history = new ArrayList<>();


    static {
        equipmentList.add(new Equipment("Cricket Kit"));
        equipmentList.add(new Equipment("Football"));
        equipmentList.add(new Equipment("Basketball"));
        equipmentList.add(new Equipment("Badminton Racket Set"));
        equipmentList.add(new Equipment("Table Tennis Bat Set"));
        equipmentList.add(new Equipment("Volleyball"));
        equipmentList.add(new Equipment("Tennis Racket"));
        equipmentList.add(new Equipment("Hockey Stick"));
        equipmentList.add(new Equipment("Gym Dumbbells Set"));
        equipmentList.add(new Equipment("Skipping Rope"));
        equipmentList.add(new Equipment("Goal Keeper Gloves"));
        equipmentList.add(new Equipment("Cones Training Set"));
        equipmentList.add(new Equipment("Chess Set"));
        equipmentList.add(new Equipment("Carrom Board"));
        equipmentList.add(new Equipment("Stopwatch"));
    }
}