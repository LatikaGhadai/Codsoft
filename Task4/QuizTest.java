import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String question;
    private List<String> options;
    private int correctOption;

    public Question(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

public class QuizTest {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;

    public QuizTest(List<Question> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    public void start() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                nextQuestion();
            }
        }, 0, 20000); // Timer for 20 seconds per question

        Scanner scanner = new Scanner(System.in);
        while (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            System.out.println("Question: " + currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            System.out.print("Enter your choice: ");
            int userChoice = scanner.nextInt();

            if (userChoice == currentQuestion.getCorrectOption()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. Correct answer was: " + currentQuestion.getCorrectOption());
            }

            currentQuestionIndex++;
        }

        timer.cancel();

        System.out.println("\nQuiz completed!");
        System.out.println("Your score: " + score + "/" + questions.size());
    }

    private void nextQuestion() {
        System.out.println("\nTime's up for question " + (currentQuestionIndex + 1));
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            System.out.println("Next question...");
        } else {
            timer.cancel();
        }
    }

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("How many Access Specifier are available in java?", List.of("3", "4", "5", "6"), 2));
        questions.add(new Question("What is String in java?", List.of("Predifined class", "Userdefined class", "variable", "reference"), 1));
        questions.add(new Question("What is Object in java?", List.of("instance of class", "method ", "variable", "reference"), 1));

        QuizTest quizTest = new QuizTest(questions);
        quizTest.start();
    }
}
