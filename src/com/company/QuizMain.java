package com.company;


import java.util.*;


public class QuizMain {

    private static User user;
    private static Question question;
    private TreeSet<Integer> currentAnswers;
    private static int score;
    private static Map<String, String> userAnswers = new TreeMap<>();


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Привет. Введи свое имя: ");

        user = new User(scanner.nextLine());


        Map<String, String> answers8 = new TreeMap<>();
        UserAnswers userAnswers = new UserAnswers(user, answers8);


//       startApp(scanner);

        takeQuiz(scanner);
    }


//    static void startApp (Scanner scanner) {
//
//        System.out.println("Выбери режим:" + "\n 1. Пройти Квиз" + "\n 2. Посмотреть рейтинговую таблицу" + "\n Введи 1 или 2");
//
//        int mode = scanner.nextInt();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        if (mode == 1) {
//            takeQuiz(scanner);
//        }
//    }

    static void takeQuiz(Scanner scanner) {

        Question[] qArray = Question.readFromJSON();


        long before = System.currentTimeMillis();

        for (int i = 0; i < qArray.length; i++) {

            Question.showQuestion(qArray, i);

            if (qArray[i].tag.equals("SCQ")) {

                String answer = Question.validation(scanner); //считывание ответа, валидация
                qArray[i].setCurrentAnswer(answer); // присваивание ответа
                userAnswers.put(qArray[i].question, answer); // запись вопроса и ответа пользователя в мэп
                if (qArray[i].checkAnswer()) { // проверка правильности ответа
                    score++; //начисление балла за правильный ответ
                    System.out.println("верно");
                }

            } else if (qArray[i].tag.equals("TQ")) {

                String answer = scanner.nextLine().toLowerCase(Locale.ROOT);
                qArray[i].setCurrentAnswer(answer);
                userAnswers.put(qArray[i].question, answer);
                if (qArray[i].checkAnswer()) {
                    score++;
                    System.out.println("верно");
                }

            } else if (qArray[i].tag.equals("MCQ")) {

                String answer = Question.validation(scanner);
                qArray[i].setCurrentAnswers(qArray[i].treeSetCurAns(answer)); //создание трисета из строки и присваивание ответа
                userAnswers.put(qArray[i].question, answer);
                if (qArray[i].checkAnswerMSQ()) {
                    score++;
                    System.out.println("верно");
                }
            }
        }

        long after = System.currentTimeMillis();

        System.out.println("Ваш результат " + score + "/" + qArray.length);
        System.out.println("Затраченное время: " + ((after - before) / 1000) + " сек");

        scanner.close();
    }
}

