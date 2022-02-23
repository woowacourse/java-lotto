package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

    private static final List<Integer> allNumbers = IntStream.range(1, 45)
            .boxed()
            .collect(Collectors.toList());

    private final List<Integer> numbers;

    public Lotto() {
        Collections.shuffle(allNumbers);
        List<Integer> lottoNums = new ArrayList<>(allNumbers.subList(0, 6));
        Collections.sort(lottoNums);

        this.numbers = lottoNums;
    }

    public Lotto(List<Integer> lottoNums) {
        if (lottoNums.size() != 6) {
            throw new RuntimeException();
        }
        Collections.sort(lottoNums);
        this.numbers = lottoNums;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return "Lotto{" + "numbers=" + numbers + '}';
    }
}
