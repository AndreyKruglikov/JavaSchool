package com.company.reflection;

/**
 * Интерфейс счетчика геттеров в классе
 */
public interface GetterCounter {
    /**
     * Возвращает колличество геттеров в переданном класс
     * @param clazz класс в котором необходимо посчитать геттеры
     * @return возвращает количество найденных геттеров
     */
    int calcGetterCount(Class<?> clazz);
}