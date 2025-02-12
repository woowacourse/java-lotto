package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import view.util.LottoGenerator;

public class Lotto {
    private List<Integer> randomNumbers;

    public Lotto(ArrayList<Integer> randomNumbers) {

        if (!isDuplicate(randomNumbers)) {
            throw new IllegalArgumentException("숫자가 중복되어서는 안됩니다.");
        }
        this.randomNumbers = randomNumbers;
    }

    private boolean isDuplicate(ArrayList<Integer> randomNumbers) {
        if (new HashSet<>(randomNumbers).size() != randomNumbers.size()) {
            return true;
        }
        return false;
    }
}
