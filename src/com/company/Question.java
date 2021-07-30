package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.source.tree.Tree;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Question {

    @JsonProperty
    String question;
    @JsonProperty
    String[] offeredAnswers;
    @JsonProperty
    String correctAnswer;
    @JsonProperty
    TreeSet<String> correctAnswers;
    @JsonProperty
    String tag;


    String currentAnswer;
    TreeSet<String> currentAnswers;

    public Question(String question, String[] offeredAnswers, String correctAnswer, String tag) {
        this.question = question;
        this.offeredAnswers = offeredAnswers;
        this.correctAnswer = correctAnswer;
        this.tag = tag;
    }

    public Question(String question, String correctAnswer, String tag) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.tag = tag;
    }

    public Question(String question, String[] offeredAnswers, TreeSet<String> correctAnswer, String tag) {
        this.question = question;
        this.offeredAnswers = offeredAnswers;
        this.correctAnswers = correctAnswer;
        this.tag = tag;
    }


    public Question() {
    }

    public boolean checkAnswer() {
        if (this.currentAnswer.equalsIgnoreCase(correctAnswer)) {
            return true;
        } else return false;
    }

    public boolean checkAnswerMSQ() {
        if (this.currentAnswers.equals(correctAnswers)) {
            return true;
        } else return false;
    }

    public static String validation(Scanner scanner) {

        String answer = scanner.nextLine();

        Pattern pattern = Pattern.compile("[^абвг]{1,4}");
        Matcher matcher = pattern.matcher(answer.toLowerCase(Locale.ROOT));

        if (matcher.find()) {
            try {
                throw new QuizException();
            } catch (QuizException qe) {
                System.out.println("Ответ не может содержать другие буквы и символы, кроме А, Б, В или Г (без учета регистра)");
                validation(scanner);
            }
        } return answer;
    }


    public static Question[] readFromJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("newquestions.json");
        Question[] arrayQ = new Question[0];
        try {
            arrayQ = objectMapper.readValue(file, Question[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayQ;
    }

    public TreeSet<String> treeSetCurAns(String str) {
        String[] array = str.split("");
        TreeSet<String> currentAnswer = new TreeSet<>();
        currentAnswer.addAll(Arrays.asList(array));
        return currentAnswer;
    }

    public static void showQuestion(Question[] qArray, int i) {

        if (qArray[i].getTag().equals("SCQ") || qArray[i].getTag().equals("MCQ")) {
            System.out.println("\n" + (i + 1) + "/" + qArray.length + ". " + qArray[i].question);
            System.out.println("А. " + qArray[i].offeredAnswers[0]);
            System.out.println("Б. " + qArray[i].offeredAnswers[1]);
            System.out.println("В. " + qArray[i].offeredAnswers[2]);
            System.out.println("Г. " + qArray[i].offeredAnswers[3]);
            System.out.print("Введите ваш ответ: ");
        } else {
            System.out.println("\n" + qArray[i].question);
            System.out.print("Введите ваш ответ: ");
        }
    }


    public String getTag() {
        return tag;
    }

    public void setCurrentAnswer(String currentAnswer) {
        this.currentAnswer = currentAnswer;
    }

    public void setCurrentAnswers(TreeSet<String> currentAnswers) {
        this.currentAnswers = currentAnswers;
    }
}
