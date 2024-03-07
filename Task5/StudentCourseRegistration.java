import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CourseSystem {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private List<String> schedule;
    private List<String> registeredStudents;

    public CourseSystem(String code, String title, String description, int capacity, List<String> schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public List<String> getRegisteredStudents() {
        return registeredStudents;
    }

    public boolean registerStudent(String studentId) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(studentId);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeStudent(String studentId) {
        return registeredStudents.remove(studentId);
    }
}

class Student {
    private String id;
    private String name;
    private List<String> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    public void removeCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }
}

public class StudentCourseRegistration {
    private List<CourseSystem> courses;
    private List<Student> students;

    public StudentCourseRegistration() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(CourseSystem course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (CourseSystem course : courses) {
            int availableSlots = course.getCapacity() - course.getRegisteredStudents().size();
            System.out.println(course.getCode() + " - " + course.getTitle() + " (" + availableSlots + "/" + course.getCapacity() + ")");
            System.out.println("Description: " + course.getDescription());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println();
        }
    }

    public void displayStudents() {
        System.out.println("Registered Students:");
        for (Student student : students) {
            System.out.println(student.getId() + " - " + student.getName());
            System.out.println("Courses: " + student.getRegisteredCourses());
            System.out.println();
        }
    }

    public CourseSystem findCourse(String courseCode) {
        for (CourseSystem course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudent(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void registerStudent(String studentId, String courseCode) {
        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        CourseSystem course = findCourse(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (course.registerStudent(studentId)) {
            student.registerCourse(courseCode);
            System.out.println("Student successfully registered for course.");
        } else {
            System.out.println("Course is full. Student registration failed.");
        }
    }

    public static void main(String[] args) {
        CourseSystem javaCourse = new CourseSystem("JT101", "Java Programming", "Learn Java programming basics ", 20, List.of("Mon 10:00 AM", "Wed 10:00 AM", "Fri 10:00 AM", "Sat 10:00AM"));
        CourseSystem webCourse = new CourseSystem("JT102", "WebDevelopment", "Learn Web Development with project", 15, List.of("Tue 2:00 PM", "Thu 2:00 PM"));

        StudentCourseRegistration system = new StudentCourseRegistration();
        system.addCourse(javaCourse);
        system.addCourse(webCourse);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nCourse Registration System Menu:");
            System.out.println("1. Display Courses");
            System.out.println("2. Display Students");
            System.out.println("3. Register Student to Course");
            System.out.println("4. Remove Student from Course");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    system.displayCourses();
                    break;
                case 2:
                    system.displayStudents();
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.next();
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.next();
                    system.registerStudent(studentId, courseCode);
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    String studentIdToRemove = scanner.next();
                    Student studentToRemove = system.findStudent(studentIdToRemove);
                    if (studentToRemove == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.print("Enter course code: ");
                    String courseCodeToRemove = scanner.next();
                    CourseSystem courseToRemove = system.findCourse(courseCodeToRemove);
                    if (courseToRemove == null) {
                        System.out.println("Course not found.");
                        break;
                    }
                    if (courseToRemove.removeStudent(studentIdToRemove)) {
                        studentToRemove.removeCourse(courseCodeToRemove);
                        System.out.println("Student successfully removed from course.");
                    } else {
                        System.out.println("Student was not registered for this course.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting Course Registration System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
