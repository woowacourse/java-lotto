package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
public class Lotto {
    private List<Integer> randomNumbers;

    public Lotto(List<Integer> randomNumbers) {
        if (isDuplicate(randomNumbers)) {
            throw new IllegalArgumentException("숫자가 중복되어서는 안됩니다.");
        }
        this.randomNumbers = randomNumbers;
    }

    private boolean isDuplicate(List<Integer> randomNumbers) {
        if (new HashSet<>(randomNumbers).size() != randomNumbers.size()) {
            return true;
        }
        return false;
    }
}
