package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AutoNumber {
    public static final int LOTTO_NUMBER_LENGTH = 6;
    private List<Integer> autoNumber = new ArrayList<>();

    public AutoNumber() {
        Collections.shuffle(LottoNumbers.getLottoNumbers());
        for (int i = 0; i < LOTTO_NUMBER_LENGTH; i++) {
            autoNumber.add(LottoNumbers.getLottoNumbers().get(i));
        }
        Collections.sort(autoNumber);
    }

    public AutoNumber(List<Integer> input) {
        this.autoNumber = input;
    }

    public List<Integer> getAutoNumber() {
        return autoNumber;
    }
}
