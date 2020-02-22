package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoNumber {
    private static final int LOTTO_NUMBER_LENGTH = 6;
    private List<Integer> autoNumber = new ArrayList<>();

    public AutoNumber() {
        Collections.shuffle(LottoNumbers.getLottoNumbers());
        for (int i = 0; i < LOTTO_NUMBER_LENGTH; i++) {
            autoNumber.add(LottoNumbers.getLottoNumbers().get(i));
        }
        Collections.sort(autoNumber);
    }

    public AutoNumber(List<Integer> customizedAutoNumber) {
        this.autoNumber = customizedAutoNumber;
    }

    public List<Integer> getAutoNumber() {
        return autoNumber;
    }

    public boolean contains(int bonusBall) {
        return autoNumber.contains(bonusBall);
    }
}
