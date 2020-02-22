package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class AutoNumbers {
    private List<AutoNumber> autoNumbers = new ArrayList<>();

    public AutoNumbers(int tryCount) {
        for (int i = 0; i < tryCount; i++) {
            autoNumbers.add(new AutoNumber());
        }
    }

    public List<AutoNumber> getAutoNumbers() {
        return autoNumbers;
    }
}
