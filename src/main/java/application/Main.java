package application;

import threads.Summator;
import tools.ArrayGenerator;

import java.util.concurrent.atomic.LongAdder;

public class Main {

    // кол-во операций в магазине
    private static final int NUMBER_OF_ACTIONS = 1000000;
    // размер максимального платежа в магазине
    private static final int MAX_PAYMENT_VALUE = 100;

    public static void main(String[] args) throws InterruptedException {
        // генерируем три массива
        int[] store1 = ArrayGenerator.generateArray(3 * NUMBER_OF_ACTIONS, MAX_PAYMENT_VALUE);
        int[] store2 = ArrayGenerator.generateArray(2 * NUMBER_OF_ACTIONS, 2 * MAX_PAYMENT_VALUE);
        int[] store3 = ArrayGenerator.generateArray(NUMBER_OF_ACTIONS, 3 * MAX_PAYMENT_VALUE);

        // создаем задачи для суммирования
        Summator store1Sum = new Summator(store1, new LongAdder());
        Summator store2Sum = new Summator(store2, new LongAdder());
        Summator store3Sum = new Summator(store3, new LongAdder());

        // создаем потоки для добавления элементов из каждого сгенерированного массива
        Thread store1Report = new Thread(null, store1Sum::add, "Магазин 1");
        Thread store2Report = new Thread(null, store2Sum::add, "Магазин 2");
        Thread store3Report = new Thread(null, store3Sum::add, "Магазин 3");

        // запуск потоков
        store1Report.start();
        store2Report.start();
        store3Report.start();

        // ожидаем окончания потоков
        store1Report.join();
        store2Report.join();
        store3Report.join();

        System.out.println("Общая сумма: " + (store1Sum.sum() + store2Sum.sum() + store3Sum.sum()));
    }
}
