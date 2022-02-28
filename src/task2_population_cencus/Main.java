package task2_population_cencus;

import task2_population_cencus.person.Education;
import task2_population_cencus.person.Person;
import task2_population_cencus.person.Sex;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        //Для генерации исходных данных населения создаём две коллекции списка: имена и фамилии
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        //содаём коллекцию объектов Person
        // на основе коллекций выше и наборов пола, образования, создаём
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),  //получаем рандомное имя из коллекции списка имён
                    families.get(new Random().nextInt(families.size())),    //получаем рандомную фамилию
                    new Random().nextInt(100),  //задаём возраст до 100
                    Sex.values()[new Random().nextInt(Sex.values().length)],    //задаём пол из набора полов
                    Education.values()[new Random().nextInt(Education.values().length)])    //получаем образование
            );

            //выводим получившуюся коллекцию с населением
            System.out.println(persons);

            //поиск числа несовершеннолетних
            long underageCount = persons.stream()
                    .filter(x -> x.getAge() < 18)   //с помощью промежуточной операции filter
                    // оставляем только население младще 18
                    .count(); //с помощью терминальной операции count подсчитываем оставшиеся элементы
            System.out.printf("Количество несовершенолетних: %d", underageCount);

            //список фамилий призывников (т.е. мужчин от 18 и до 27 лет)
            List<String> conscripts = persons.stream()
                    //фильтруем с помощью промежуточной операции по возрасту и полу
                    .filter(x -> (x.getSex().toString() == "MAN" && x.getAge() >= 18 && x.getAge() <= 27))
                    // с помощью промеж операции маппинга map преобразуем объекты Person в String(получаем только имена)
                    .map(Person::getFamily)
                    //с помощью терминальной операции collect собираем полученный стрим в отдельный лист
                    .collect(Collectors.toList());
            System.out.println(conscripts);

            

        }


    }
}
