package com.company;


import java.io.*;
import java.util.*;


public class QuizMain {

    private static int score;
    private static Map<String, Integer> leaderboard = new TreeMap<>();


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);

        showInfo();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        takeQuiz(scanner);

        System.out.println("Теперь таблица рейтинга выглядит следующим образом");
        showLeaderboard();

        scanner.close();
    }


    static void takeQuiz(Scanner scanner) {


        System.out.println("Привет. Введи свое имя: ");
        String username = scanner.nextLine();

        Question[] qArray = Question.readFromJSON();






        long before = System.currentTimeMillis();

        for (int i = 0; i < qArray.length; i++) {

            Question.showQuestion(qArray, i);

            if (qArray[i].tag.equals("SCQ")) {

                String answer = Question.validation(scanner); //считывание ответа, валидация
                qArray[i].setCurrentAnswer(answer); // присваивание ответа
                if (qArray[i].checkAnswer()) { // проверка правильности ответа
                    score++; //начисление балла за правильный ответ
                    System.out.println("верно");
                }

            } else if (qArray[i].tag.equals("TQ")) {

                String answer = scanner.nextLine().toLowerCase(Locale.ROOT);
                qArray[i].setCurrentAnswer(answer);
                if (qArray[i].checkAnswer()) {
                    score++;
                    System.out.println("верно");
                }

            } else if (qArray[i].tag.equals("MCQ")) {

                String answer = Question.validation(scanner);
                qArray[i].setCurrentAnswers(qArray[i].treeSetCurAns(answer)); //создание трисета из строки и присваивание ответа
                if (qArray[i].checkAnswerMSQ()) {
                    score++;
                    System.out.println("верно");
                }
            }
        }

        long after = System.currentTimeMillis();

        System.out.println("Ваш результат " + score + "/" + qArray.length);
        leaderboard.put(username, score);
        try {
            saveResult();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Затраченное время: " + ((after - before) / 1000) + " сек");

        scanner.close();
    }


    static void saveResult() throws IOException {

        File file = new File("leaderboard.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        try (BufferedWriter bf = new BufferedWriter(fileWriter)) {
            for (Map.Entry<String, Integer> entry : leaderboard.entrySet()) {
                bf.write(entry.getKey() + ":" + entry.getValue());
                bf.newLine();
            }
        }
    }

    static void showLeaderboard() {

        try {
            File myObj = new File("leaderboard.txt");
            TreeMap<Integer, String> tm = new TreeMap<>(Collections.reverseOrder());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String [] array = data.split(":");
                tm.put(Integer.parseInt(array[1]), array[0]);
            }

            int place = 0;
            for (Map.Entry<Integer, String> entry : tm.entrySet()) {
                place++;
                System.out.println(place + ". " +  entry.getValue() + " Результат: "  + entry.getKey());
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static void showInfo () {
        System.out.println("Правила квиза:" +
        "\n1. Вариант ответа может быть один, состоящий из нескольких вариантов, либо текстовым." +
        "\n2. Для ввода ответа пользуйтесь кириллицей(без учета регистра)." +
        "\n3. Для подтверждения ответа, нажмите ENTER." +
        "\n4. Если вариантов ответа несколько, вводите варианты без пробелов в одну строку.");
    }
}



