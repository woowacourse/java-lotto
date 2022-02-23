package model;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

public class RandomLottoNumbersGenerator extends LottoNumbersGenerator{
    private final List<Integer> numberPool;

    public RandomLottoNumbersGenerator(int start, int end) {
        numberPool = createNumbers(start, end);
    }

    protected LottoNumbers createLottoNumbers() {
        Set<Integer> lottoNumbers = nextSixNumbers();
        Iterator<Integer> iterator = lottoNumbers.iterator();
        return LottoNumbers.withSixNumbers(iterator.next(), iterator.next(), iterator.next(),
            iterator.next(), iterator.next(), iterator.next());
    }

    private List<Integer> createNumbers(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().collect(toList());
    }

    private Set<Integer> nextSixNumbers() {
        return get(6);
    }

    public Set<Integer> get(int quantity) {
        shuffle(numberPool);
        Queue<Integer> queue = new LinkedList<>(numberPool);
        Set<Integer> result = new HashSet<>();

        for (int i = 0; i < quantity; i++) {
            result.add(queue.remove());
        }

        return result;
    }

    private List<Integer> shuffle(List<Integer> list) {
        Collections.shuffle(list);
        return list;
    }
}
