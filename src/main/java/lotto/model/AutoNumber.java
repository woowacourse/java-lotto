package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AutoNumber {
    private List<Integer> autoNumber = new ArrayList<>();

    public AutoNumber() {
        Collections.shuffle(LottoNumbers.getLottoNumbers());
        for (int i = 0; i <= 5; i++) {
            autoNumber.add(LottoNumbers.getLottoNumbers().get(i));
        }
        autoNumber.sort(Comparator.naturalOrder());
    }

    public AutoNumber(List<Integer> input) {
        this.autoNumber = input;
    }

    public List<Integer> getAutoNumber() {
        return autoNumber;
    }
}
