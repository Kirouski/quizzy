package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

import java.util.TreeSet;

public class QuestionToJSON {

    static File file = new File("newquestions.json");

    public QuestionToJSON() {
    }

    public static void main(String[] args) {


        String[] answers = {"Минск", "Гродно", "Витебск", "Могилев"};
        String[] answers2 = {""};
        String corAnswers = "а";
        String[] answers3 = {"Канада", "Россия", "Африка", "Египет"};
        String corAnswers4 = "б";
        String corAnswers5 = "г";
        String[] answers4 = {"Париж", "Лондон", "Венеция", "Прага"};
        String corAnswers6 = "а";
        String corAnswers7 = "в";
        String[] answers5 = {"Чикаго", "Cингапур", "Мальта", "Бразилия"};
        String corAnswers8 = "б";
        String corAnswers9 = "а";
        String[] answers6 = {"Эрио", "Олимпиада", "Багдад", "Афины"};
        String corAnswers10 = "г";
        String corAnswers2 = "Вашингтон";
        String corAnswers3 = "Пиза";
        String corAnswers11 = "Токио";
        String[] answers7 = {"Вязьма", "Туапсе", "Йошкар-Ола", "Улан-Удэ"};

        TreeSet<String> corAnswers12 = new TreeSet<>();
        corAnswers12.add("а");
        corAnswers12.add("б");
        corAnswers12.add("в");
        corAnswers12.add("г");

        String[] answers8 = {"Сингапур", "Мумбаи", "Пекин", "Дали"};
        TreeSet<String> corAnswers13 = new TreeSet<>();
        corAnswers13.add("б");
        corAnswers13.add("г");
        String[] answers9 = {"Норвегия", "Бельгия", "Швеция", "Финляндия"};
        TreeSet<String> corAnswers14 = new TreeSet<>();
        corAnswers14.add("а");
        corAnswers14.add("в");
        corAnswers14.add("г");
        String[] answers10 = {"Светлогорск", "Балтийск", "Калининград", "Калязин"};

        TreeSet<String> corAnswers15 = new TreeSet<>();
        corAnswers15.add("а");
        corAnswers15.add("б");
        corAnswers15.add("в");

        Question[] questions = new Question[15];

        questions[0] = new Question("Столица Беларуси?", answers, corAnswers, "SCQ");
        questions[1] = new Question("Самое глубокое озеро в мире называется Байкал. В какой из перечисленных стран оно находится?", answers3, corAnswers4, "SCQ");
        questions[2] = new Question("В какой из предложенных стран находятся самые высокие и известные пирамиды фараонов?", answers3, corAnswers5, "SCQ");
        questions[3] = new Question("В каком городе можно увидеть Эйфелеву башню и побывать в Диснейленде?", answers4, corAnswers6, "SCQ");
        questions[4] = new Question("Уникальный город на воде, славящийся своими карнавалами?", answers4, corAnswers7, "SCQ");
        questions[5] = new Question("Выберите вариант, в котором город-столица и страна имеют одинаковые названия:", answers5, corAnswers8, "SCQ");
        questions[6] = new Question("В каком городе впервые было построено (Чёртово колесо)?", answers5, corAnswers9, "SCQ");
        questions[7] = new Question("Какая европейская столица, имеет название как у богини?", answers6, corAnswers10, "SCQ");
        questions[8] = new Question("Столица США?", answers2, corAnswers2, "TQ");
        questions[9] = new Question("В каком городе распологается падающая башня?", answers2, corAnswers3, "TQ");
        questions[10] = new Question("Какой город признан самым населеннам?", answers2, corAnswers11, "TQ");
        questions[11] = new Question("Какие города распологаются в России?", answers7, corAnswers12, "MCQ");
        questions[12] = new Question("Какие города расположены в Индии?", answers8, corAnswers13, "MCQ");
        questions[13] = new Question("Какие страны называют 'скандинавским'?", answers9, corAnswers14, "MCQ");
        questions[14] = new Question("Какие из перечисленных городов относятся к так называемому 'Янтарному краю'?", answers10, corAnswers15, "MCQ");

        writeToJSON(questions);

    }

    static void writeToJSON(Question [] qArray) {
        ObjectMapper objectMapper =new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            objectMapper.writeValue(file, qArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
