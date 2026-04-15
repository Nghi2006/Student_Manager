import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class StudentManager {
    private List<Student> list = new ArrayList<>();
    private Map<String, Student> map = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    public void create() {
        int amount = 0;
        while (true) {
            try {
                System.out.print("Number of students you want to enter: ");
                amount = Integer.parseInt(sc.nextLine());
                if (amount > 0) break;
                System.out.println("Error: Amount must be > 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Please enter a whole number.");
            }
        }

        for (int i = 1; i <= amount; i++) {
            System.out.println("\n--- Entering details for student " + i + " ---");
            Student student = new Student();

            while (true) {
                System.out.print("ID: ");
                String id = sc.nextLine().trim();
                if (id.isEmpty()) {
                    System.out.println("ID cannot be empty!");
                    continue;
                }
                if (map.containsKey(id)) {
                    System.out.println("Error: Student ID '" + id + "' already exists. Please use a different ID.");
                } else {
                    student.setId(id);
                    break;
                }
            }

            System.out.print("Name: ");
            student.setName(sc.nextLine());

            while (true) {
                System.out.print("Marks (0-10): ");
                try {
                    float marks = Float.parseFloat(sc.nextLine());
                    if (marks >= 0 && marks <= 10) {
                        student.setMarks(marks);
                        break;
                    }
                    System.out.println("Invalid mark. It must be between 0 and 10.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                }
            }

            list.add(student);
            map.put(student.getId(), student);
        }
        System.out.println("\n>>> Add students success!!");
    }

    // Helper function to print one student
    public void print(Student st) {
        System.out.println("Student id: " + st.getId() +
                ", name: " + st.getName() +
                ", marks: " + st.getMarks() +
                ", academic ability: " + st.getAcademicAbility());
    }

    // Display all students and their rankings
    public void printOut() {
        if (list.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        System.out.println("List of students:");
        for (Student st : list) {
            print(st);
        }
    }

    // Linear Search
    public void findByIdLinear() {
        System.out.print("Enter ID to find: ");
        String id = sc.nextLine().trim();

        if (!map.containsKey(id)) {
            System.out.println("Error: Student with ID '" + id + "' does not exist in the system.");
            return;
        }
        Student result = map.get(id);
        print(result);
    }

    // Binary Search
    public void findByIdBinary() {
//        sc.nextLine();
        System.out.print("Enter ID to find: ");
        String id = sc.nextLine().trim();

        if (list.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        list.sort((a, b) -> a.getId().compareToIgnoreCase(b.getId()));

        int left = 0, right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = list.get(mid).getId().compareToIgnoreCase(id);

            if (cmp == 0) {
                print(list.get(mid));
                return;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("Student not found!");
    }

    // Delete
    public void deleteById() {
        System.out.print("Enter ID to delete: ");
        String id = sc.nextLine().trim();

        // (Operational Error check)
        if (!map.containsKey(id)) {
            System.out.println("Delete Failed: Cannot find student with ID '" + id + "'. Operation aborted.");
            return;
        }

        map.remove(id);
        list.removeIf(st -> st.getId().equalsIgnoreCase(id));
        System.out.println("Success: Student '" + id + "' has been removed.");
    }

    // Edit
    public void editStudent() {
        System.out.print("Enter ID to edit: ");
        String id = sc.nextLine().trim();

        Student st = map.get(id);

        if (st == null) {
            System.out.println("Error: Student ID '" + id + "' not found.");
            return;
        }

        System.out.print("Enter new name (Enter to skip): ");
        String newName = sc.nextLine().trim();
        if (!newName.isEmpty()) {
            st.setName(newName);
        }

        while (true) {
            System.out.print("Enter new marks 0-10 (Enter to skip): ");
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                break;
            }

            try {
                float newMark = Float.parseFloat(input);
                if (newMark >= 0 && newMark <= 10) {
                    st.setMarks(newMark);
                    break;
                } else {
                    System.out.println("Invalid mark! Must be 0-10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }

        System.out.println("Update successful!");
    }


    public void listMenu() {
        System.out.println("\t\t\t\t|-------------------------Menu-------------------------|");
        System.out.println("\t\t\t\t| 1. Enter a list of students                         |");
        System.out.println("\t\t\t\t| 2. Print out list of students                       |");
        System.out.println("\t\t\t\t| 3. Find student by ID entered from the keyboard.    |");
        System.out.println("\t\t\t\t|    (Linear Search)                                  |");
        System.out.println("\t\t\t\t| 4. Find student by ID entered from the keyboard.    |");
        System.out.println("\t\t\t\t|    (Binary Search)                                  |");
        System.out.println("\t\t\t\t| 5. Delete student by ID                             |");
        System.out.println("\t\t\t\t| 6. Edit student by ID                               |");
        System.out.println("\t\t\t\t| 7. Sort student by mark. (Bubble Sort)              |");
        System.out.println("\t\t\t\t| 8. Sort student by mark. (Insertion Sort)           |");
        System.out.println("\t\t\t\t| 9. Sort student by mark. (Quick Sort)               |");
        System.out.println("\t\t\t\t| 0. Exit                                             |");
        System.out.println("\t\t\t\t|-----------------------------------------------------|");
    }

    public boolean repeatFunction() {
        while (true) {
            try {
                System.out.print("Press 1 to continue or 0 to return to Menu: ");
                int option = Integer.parseInt(sc.nextLine());
                if (option == 0) return false;
                if (option == 1) return true;
                System.out.println("Please enter only 0 or 1.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public void menu() {
        while (true) {
            listMenu();
            try {
                System.out.print("Enter your choice: ");
                int function = Integer.parseInt(sc.nextLine());

                switch (function) {
                    case 1:
                        do {
                            create();
                        } while (repeatFunction());
                        break;
                    case 2:
                        do {
                            printOut();
                        } while (repeatFunction());
                        break;
                    case 3:
                        do {
                            findByIdLinear();
                        } while (repeatFunction());
                        break;
                    case 4:
                        do {
                            findByIdBinary();
                        } while (repeatFunction());
                        break;
                    case 5:
                        do {
                            deleteById();
                        } while (repeatFunction());
                        break;
                    case 6:
                        do {
                            editStudent();
                        } while (repeatFunction());
                        break;
                    case 7:
                        do {
                            sortMarkBubble();
                        } while (repeatFunction());
                        break;
                    case 8:
                        do {
                            sortMarkInsertion();
                        } while (repeatFunction());
                        break;
                    case 9:
                        do {
                            sortMarkQuick();
                        } while (repeatFunction());
                        break;
                    case 0:
                        System.out.println("Exiting program...");
                        return;
                    default:
                        System.out.println("Wrong option! Please choose 0-9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Choice must be a number!");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    public void sortMarkBubble() {
        if (list.isEmpty()) {
            System.out.println("List is empty! Nothing to sort.");
            return;
        }
        int choice = 0;
        while (true) {
            try {
                System.out.print("Ascending (1) or Descending (2): ");
                choice = Integer.parseInt(sc.nextLine()); // Dùng nextLine để chống trôi lệnh và bắt lỗi
                if (choice == 1 || choice == 2) break;
                System.out.println("Invalid choice! Please enter 1 or 2.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a number (1 or 2), not text.");
            }
        }
        Sort.bubbleSort(list, choice);
        System.out.println("List after Bubble Sort:");
        printOut();
    }

    public void sortMarkInsertion() {
        if (list.isEmpty()) {
            System.out.println("List is empty! Nothing to sort.");
            return;
        }
        int choice = 0;
        while (true) {
            try {
                System.out.print("Ascending (1) or Descending (2): ");
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 1 || choice == 2) break;
                System.out.println("Invalid choice! Please enter 1 or 2.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a number (1 or 2).");
            }
        }
        Sort.insertionSort(list, choice);
        System.out.println("List after Insertion Sort:");
        printOut();
    }

    public void sortMarkQuick() {
        if (list.isEmpty()) {
            System.out.println("List is empty! Nothing to sort.");
            return;
        }
        int choice = 0;
        while (true) {
            try {
                System.out.print("Ascending (1) or Descending (2): ");
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 1 || choice == 2) break;
                System.out.println("Invalid choice! Please enter 1 or 2.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a number (1 or 2).");
            }
        }
        Sort.quickSort(list, 0, list.size() - 1, choice);
        System.out.println("List after Quick Sort:");
        printOut();
    }
}


