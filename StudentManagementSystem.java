import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {

    static class Student {
        private int rollNumber;
        private String name;
        private int age;
        private String grade;

        public Student(int rollNumber, String name, int age, String grade) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.age = age;
            this.grade = grade;
        }

        public int getRollNumber() {
            return rollNumber;
        }

        public void setRollNumber(int rollNumber) {
            this.rollNumber = rollNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Roll Number: " + rollNumber + ", Name: " + name + ", Age: " + age + ", Grade: " + grade;
        }
    }

    static class StudentService {
        private ArrayList<Student> students = new ArrayList<>();
        private Scanner scanner = new Scanner(System.in);

        public void addStudent() {
            System.out.print("Enter Roll Number: ");
            int rollNumber;
            try {
                rollNumber = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Roll Number must be a number.");
                return;
            }

            if (isRollNumberExists(rollNumber)) {
                System.out.println("Roll Number already exists! Please use a different one.");
                return;
            }

            scanner.nextLine(); // consume newline
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Age: ");
            int age;
            try {
                age = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Age must be a number.");
                return;
            }

            if (age <= 0) {
                System.out.println("Invalid age. Age must be positive.");
                return;
            }

            scanner.nextLine(); // consume newline
            System.out.print("Enter Grade: ");
            String grade = scanner.nextLine();

            students.add(new Student(rollNumber, name, age, grade));
            System.out.println("Student added successfully!");
        }

        public void viewAllStudents() {
            if (students.isEmpty()) {
                System.out.println("No students found.");
            } else {
                System.out.println("\n--- Student List ---");
                for (Student student : students) {
                    System.out.println(student);
                }
            }
        }

        public void updateStudent() {
            System.out.print("Enter Roll Number to update: ");
            int rollNumber;
            try {
                rollNumber = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Roll Number must be a number.");
                return;
            }

            Student studentToUpdate = getStudentByRollNumber(rollNumber);
            if (studentToUpdate != null) {
                scanner.nextLine(); // Consume newline
                System.out.print("Enter New Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter New Age: ");
                int age;
                try {
                    age = Integer.parseInt(scanner.next());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Age must be a number.");
                    return;
                }

                if (age <= 0) {
                    System.out.println("Invalid age. Age must be positive.");
                    return;
                }

                scanner.nextLine(); // Consume newline
                System.out.print("Enter New Grade: ");
                String grade = scanner.nextLine();

                studentToUpdate.setName(name);
                studentToUpdate.setAge(age);
                studentToUpdate.setGrade(grade);

                System.out.println("Student details updated successfully!");
            } else {
                System.out.println("Student with Roll Number " + rollNumber + " not found.");
            }
        }

        public void deleteStudent() {
            System.out.print("Enter Roll Number to delete: ");
            int rollNumber;
            try {
                rollNumber = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Roll Number must be a number.");
                return;
            }

            Student studentToDelete = getStudentByRollNumber(rollNumber);
            if (studentToDelete != null) {
                students.remove(studentToDelete);
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student with Roll Number " + rollNumber + " not found.");
            }
        }

        public void searchStudent() {
            scanner.nextLine(); // consume newline
            System.out.print("Enter Roll Number or Name to search: ");
            String input = scanner.nextLine();
            boolean found = false;

            for (Student student : students) {
                if (String.valueOf(student.getRollNumber()).equals(input) ||
                        student.getName().equalsIgnoreCase(input)) {
                    System.out.println(student);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No matching student found.");
            }
        }

        private boolean isRollNumberExists(int rollNumber) {
            return getStudentByRollNumber(rollNumber) != null;
        }

        private Student getStudentByRollNumber(int rollNumber) {
            for (Student student : students) {
                if (student.getRollNumber() == rollNumber) {
                    return student;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentService();

        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

            switch (choice) {
                case 1:
                    studentService.addStudent();
                    break;
                case 2:
                    studentService.viewAllStudents();
                    break;
                case 3:
                    studentService.updateStudent();
                    break;
                case 4:
                    studentService.deleteStudent();
                    break;
                case 5:
                    studentService.searchStudent();
                    break;
                case 6:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
