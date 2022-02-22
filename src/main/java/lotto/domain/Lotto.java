package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    private static final List<Integer> numbers = IntStream.rangeClosed(1, 45).boxed().collect(Collectors.toList());
    private List<Integer> pickedNumbers;

    public Lotto() {
        Collections.shuffle(numbers);
        List<Integer> list = getRandom6AscendingNumber();
        pickedNumbers = list;
    }

    private List<Integer> getRandom6AscendingNumber() {
        List<Integer> list = numbers.subList(0, 6);
        Collections.sort(list);
        return list;
    }

    public int getSize() {
        return pickedNumbers.size();
    }

    public List<Integer> getPickedNumbers(){
        return pickedNumbers;
    }
}
