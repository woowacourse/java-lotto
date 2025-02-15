package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> randomNumbers;

    public Lotto(Set<Integer> randomNumbers) {
        this.randomNumbers = new ArrayList<>(randomNumbers);
        Collections.sort(this.randomNumbers);
    }

    public List<Integer> getRandomNumbers() {
        return randomNumbers;
    }

    public String printLotto() {
        return "[" + String.join(", ",
                randomNumbers.stream()
                        .map(String::valueOf)
                        .collect(Collectors.toList())
        ) + "]";
    }
}
