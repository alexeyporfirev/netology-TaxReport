package threads;


import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

public class Summator {
    private int[] arr;
    private LongAdder adder;

    /**
     * Создание объекта для суммирования
     * @param arr Массив с элементами для суммирования
     * @param adder объект типа LongAdder для последующего накопления и суммирования элементов массива
     */
    public Summator(int[] arr, LongAdder adder) {
        this.arr = arr;
        this.adder = adder;
    }

    /**
     * Добавление новых элементов в общий массив
     */
    public void add() {
        IntStream.range(0, arr.length)
                .forEach(i -> adder.add(arr[i]));
    }

    /**
     * Подсчет общей суммы элементов массива
     * @return Посчитанная сумма элементов массива
     */
    public long sum() {
        return adder.sum();
    }

}
