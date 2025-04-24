package src;
import java.util.*;

// Singly Linked List for student records
class StudentLinkedList {
    StudentNode head;

    // Add student at the end
    void addStudent(String name, int grade) {
        Student student = new Student(name, grade);
        StudentNode newNode = new StudentNode(student);

        if (head == null) {
            head = newNode;
        } else {
            StudentNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Student added: " + name);
    }

    // Remove student by name
    void removeStudent(String name) {
        if (head == null) return;

        if (head.student.name.equalsIgnoreCase(name)) {
            head = head.next;
            System.out.println("Student removed: " + name);
            return;
        }

        StudentNode current = head;
        while (current.next != null && !current.next.student.name.equalsIgnoreCase(name)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            System.out.println("Student removed: " + name);
        } else {
            System.out.println("Student not found: " + name);
        }
    }

    // Sort students by grade using insertion sort
    void sortByGrade() {
        if (head == null || head.next == null) return;

        StudentNode sorted = null;
        StudentNode current = head;

        while (current != null) {
            StudentNode next = current.next;
            sorted = insertInSortedOrder(sorted, current);
            current = next;
        }

        head = sorted;
        System.out.println("Students sorted by grade.");
    }

    private StudentNode insertInSortedOrder(StudentNode sorted, StudentNode newNode) {
        if (sorted == null || newNode.student.grade < sorted.student.grade) {
            newNode.next = sorted;
            return newNode;
        }

        StudentNode current = sorted;
        while (current.next != null && current.next.student.grade <= newNode.student.grade) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;

        return sorted;
    }

    // Display all students
    void displayStudents() {
        if (head == null) {
            System.out.println("No students to display.");
            return;
        }

        System.out.println("\nStudent List:");
        StudentNode current = head;
        while (current != null) {
            System.out.println("Name: " + current.student.name + ", Grade: " + current.student.grade);
            current = current.next;
        }
    }
}

// Queue for attendance
class AttendanceQueue {
    Queue<String> attendance = new LinkedList<>();

    void markPresent(String name) {
        attendance.add(name);
        System.out.println(name + " marked present.");
    }

    void displayAttendance() {
        if (attendance.isEmpty()) {
            System.out.println("No attendance marked.");
        } else {
            System.out.println("\nToday's Attendance:");
            for (String name : attendance) {
                System.out.println(name);
            }
        }
    }

    void clearAttendance() {
        attendance.clear();
        System.out.println("Attendance cleared.");
    }
}

// Main class
public class StudentGradeBook{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentLinkedList studentList = new StudentLinkedList();
        AttendanceQueue attendance = new AttendanceQueue();

        while (true) {
            System.out.println("\n--- Student Gradebook Menu ---");
            System.out.println("1. Add student");
            System.out.println("2. Remove student");
            System.out.println("3. Display students");
            System.out.println("4. Sort students by grade");
            System.out.println("5. Mark attendance");
            System.out.println("6. Show attendance");
            System.out.println("7. Clear attendance");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter grade: ");
                    int grade = scanner.nextInt();
                    studentList.addStudent(name, grade);
                }
                case 2 -> {
                    System.out.print("Enter name to remove: ");
                    String name = scanner.nextLine();
                    studentList.removeStudent(name);
                }
                case 3 -> studentList.displayStudents();
                case 4 -> studentList.sortByGrade();
                case 5 -> {
                    System.out.print("Enter name to mark present: ");
                    String name = scanner.nextLine();
                    attendance.markPresent(name);
                }
                case 6 -> attendance.displayAttendance();
                case 7 -> attendance.clearAttendance();
                case 8 -> {
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}