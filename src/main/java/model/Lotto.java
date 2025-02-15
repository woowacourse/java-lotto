package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> randomNumbers;

    public Lotto(List<Integer> randomNumbers) {
        if (isDuplicate(randomNumbers)) {
            throw new IllegalArgumentException("숫자가 중복되어서는 안됩니다.");
        }
        this.randomNumbers = randomNumbers;
        Collections.sort(randomNumbers);
    }

    public List<Integer> getRandomNumbers() {
        return randomNumbers;
    }

    private boolean isDuplicate(List<Integer> randomNumbers) {
        return (new HashSet<>(randomNumbers).size() != randomNumbers.size());
    }
    public String printLotto() {
        return "[" + String.join(", ",
                randomNumbers.stream()
                        .map(String::valueOf)
                        .collect(Collectors.toList())
        ) + "]";
    }
}
