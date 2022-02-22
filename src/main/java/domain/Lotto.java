package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

    private final List<Integer> numbers;
    private final static List<Integer> allNumbers = IntStream.range(1, 45)
            .boxed()
            .collect(Collectors.toList());

    public Lotto() {
        Collections.shuffle(allNumbers);
        List<Integer> lottoNums = allNumbers.subList(0, 6);
        Collections.sort(lottoNums);

        this.numbers = lottoNums;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
