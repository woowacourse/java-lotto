package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> randomNumbers;

    public Lotto(Set<Integer> randomNumbers) {
        this.randomNumbers = new ArrayList<>(randomNumbers);
        Collections.sort(this.randomNumbers);
    }

    public List<Integer> getRandomNumbers() {
        return randomNumbers;
    }

    public boolean contains(int number){
        return randomNumbers.contains(number);
    }
}
