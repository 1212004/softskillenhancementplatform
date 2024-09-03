import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SoftSkillEnhancementApp {

    private static final Scanner scanner = new Scanner(System.in);

    // Mock user and admin credentials
    private static final Map<String, String> userCredentials = new HashMap<>();
    private static final Map<String, String> adminCredentials = new HashMap<>();
    private static boolean isAdmin = false;

    // Store lessons and practice questions
    private static final ArrayList<String> vocabularyLessons = new ArrayList<>();
    private static final ArrayList<String> speechLessons = new ArrayList<>();
    private static final ArrayList<String> pronunciationLessons = new ArrayList<>();
    private static final ArrayList<String> practiceQuestions = new ArrayList<>();

    static {
        // Predefined credentials (username -> password)
        userCredentials.put("user", "password");
        adminCredentials.put("admin", "adminpass");

        // Load initial lessons from files (assuming files exist)
        loadLessonsFromFile("vocabulary_lessons.txt", vocabularyLessons);
        loadLessonsFromFile("speech_lessons.txt", speechLessons);
        loadLessonsFromFile("pronunciation_lessons.txt", pronunciationLessons);

        // Sample practice questions
        practiceQuestions.add("Sample Question 1: Define 'vocabulary'.");
        practiceQuestions.add("Sample Question 2: How to improve speech fluency?");
    }

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Soft Skill Enhancement Application");
            System.out.println("1. User Login");
            System.out.println("2. Admin Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    if (login(userCredentials)) {
                        userDashboard();
                    }
                    break;
                case 2:
                    if (login(adminCredentials)) {
                        isAdmin = true;
                        adminMenu();
                        isAdmin = false; // Reset admin flag after exiting menu
                    }
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting the application. Have a great day!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static boolean login(Map<String, String> credentials) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password. Would you like to register? (yes/no)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                register(credentials);
                return login(credentials);
            }
            return false;
        }
    }

    private static void register(Map<String, String> credentials) {
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        if (credentials == userCredentials) {
            userCredentials.put(newUsername, newPassword);
        } else {
            adminCredentials.put(newUsername, newPassword);
        }
        System.out.println("Registration successful!");
    }

    private static void userDashboard() {
        boolean running = true;

        while (running) {
            System.out.println("\nUser Dashboard");
            System.out.println("1. Vocabulary Lessons");
            System.out.println("2. Speech Lessons");
            System.out.println("3. Pronunciation Lessons");
            System.out.println("4. Practice Test");
            System.out.println("5. Test Report");
            System.out.println("6. Learning Path");
            System.out.println("7. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    showLessons(vocabularyLessons, "Vocabulary Lessons");
                    break;
                case 2:
                    showLessons(speechLessons, "Speech Lessons");
                    break;
                case 3:
                    showLessons(pronunciationLessons, "Pronunciation Lessons");
                    break;
                case 4:
                    practiceTest();
                    break;
                case 5:
                    testReport();
                    break;
                case 6:
                    learningPath();
                    break;
                case 7:
                    running = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void showLessons(ArrayList<String> lessons, String sectionName) {
        System.out.println("\n" + sectionName);
        if (lessons.isEmpty()) {
            System.out.println("No lessons available.");
        } else {
            for (String lesson : lessons) {
                System.out.println("- " + lesson);
            }
        }
    }

    private static void practiceTest() {
        System.out.println("\nPractice Test:");
        for (String question : practiceQuestions) {
            System.out.println(question);
        }
        System.out.println("Complete the practice test and submit your answers.");
    }

    private static void testReport() {
        // Example test report
        System.out.println("\nTest Report:");
        System.out.println("Practice Test Score: 80/100");
        System.out.println("Comments: Good job! Keep practicing.");
    }

    private static void learningPath() {
        System.out.println("\nLearning Path:");
        System.out.println("You are on track to reach your goal. Points: 100/100");
    }

    private static void adminMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\nAdmin Menu");
            System.out.println("1. List Users");
            System.out.println("2. List Soft Skills");
            System.out.println("3. Add New Lesson");
            System.out.println("4. View Progress");
            System.out.println("5. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    listUsers();
                    break;
                case 2:
                    listSoftSkills();
                    break;
                case 3:
                    addNewLesson();
                    break;
                case 4:
                    viewProgress();
                    break;
                case 5:
                    running = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void listUsers() {
        System.out.println("\nList of Users:");
        userCredentials.keySet().forEach(System.out::println);
    }

    private static void listSoftSkills() {
        System.out.println("\nList of Soft Skills:");
        System.out.println("1. Vocabulary");
        System.out.println("2. Speech");
        System.out.println("3. Pronunciation");
    }

    private static void addNewLesson() {
        System.out.println("\nAdd New Lesson");
        System.out.println("1. Vocabulary");
        System.out.println("2. Speech");
        System.out.println("3. Pronunciation");
        System.out.print("Select a section: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Enter the file path for the new lesson: ");
        String filePath = scanner.nextLine();

        ArrayList<String> targetList;
        switch (choice) {
            case 1:
                targetList = vocabularyLessons;
                break;
            case 2:
                targetList = speechLessons;
                break;
            case 3:
                targetList = pronunciationLessons;
                break;
            default:
                System.out.println("Invalid section.");
                return;
        }
        loadLessonsFromFile(filePath, targetList);
        System.out.println("Lessons loaded successfully.");
    }

    private static void loadLessonsFromFile(String filePath, ArrayList<String> lessonsList) {
        lessonsList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lessonsList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void viewProgress() {
        System.out.println("\nProgress Report:");
        // Example progress report (could be expanded)
        System.out.println("Vocabulary Lessons: Completed 2/2");
        System.out.println("Speech Lessons: Completed 1/2");
        System.out.println("Pronunciation Lessons: Completed 1/2");
    }
}
