package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private List<Integer> randomNumbers;

    public List<Integer> getRandomNumbers() {
        return randomNumbers;
    }

    public Lotto(List<Integer> randomNumbers) {
        if (isDuplicate(randomNumbers)) {
            throw new IllegalArgumentException("숫자가 중복되어서는 안됩니다.");
        }
        this.randomNumbers = randomNumbers;
        Collections.sort(randomNumbers);
    }

    private boolean isDuplicate(List<Integer> randomNumbers) {
        if (new HashSet<>(randomNumbers).size() != randomNumbers.size()) {
            return true;
        }
        return false;
    }
    public String printLotto() {
        return "[" + String.join(", ",
                randomNumbers.stream()
                        .map(String::valueOf)
                        .collect(Collectors.toList())
        ) + "]";
    }
}
