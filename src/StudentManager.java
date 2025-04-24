package src;
import java.util.*;

public class StudentManager {
    private StudentNode head;
    private Queue<String> attendance = new LinkedList<>();

    public void addStudent(String name, int grade) {
        Student newStudent = new Student(name, grade);
        StudentNode newNode = new StudentNode(newStudent);

        if (head == null) {
            head = newNode;
        } else {
            StudentNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void removeStudent(String name) {
        if (head == null) return;

        if (head.student.name.equalsIgnoreCase(name)) {
            head = head.next;
            return;
        }

        StudentNode current = head;
        while (current.next != null && !current.next.student.name.equalsIgnoreCase(name)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public void sortByGrade() {
        if (head == null || head.next == null) return;

        StudentNode sorted = null;
        StudentNode current = head;

        while (current != null) {
            StudentNode next = current.next;
            sorted = insertSorted(sorted, current);
            current = next;
        }

        head = sorted;
    }

    private StudentNode insertSorted(StudentNode sorted, StudentNode newNode) {
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

    public List<String> getStudentList() {
        List<String> list = new ArrayList<>();
        StudentNode current = head;
        while (current != null) {
            list.add(current.student.name + " - Grade: " + current.student.grade);
            current = current.next;
        }
        return list;
    }

    public void markAttendance(String name) {
        attendance.add(name);
    }

    public void clearAttendance() {
        attendance.clear();
    }

    public List<String> getAttendanceList() {
        return new ArrayList<>(attendance);
    }
}
