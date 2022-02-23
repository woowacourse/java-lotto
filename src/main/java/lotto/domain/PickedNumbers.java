package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PickedNumbers {
    private static final List<Integer> numbers = IntStream.rangeClosed(1, 45).boxed().collect(Collectors.toList());
    private List<Integer> pickedNumbers;

    public PickedNumbers() {
        pickedNumbers = new ArrayList<>();
        Collections.shuffle(numbers);
        generateRandom6AscendingNumber();
    }

    public PickedNumbers(String input) {
        pickedNumbers = Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    private void generateRandom6AscendingNumber() {
        List<Integer> list = numbers.stream().limit(6).collect(Collectors.toList());
        Collections.sort(list);
        pickedNumbers = list;
    }

    public List<Integer> getPickedNumbers() {
        return pickedNumbers;
    }
}
