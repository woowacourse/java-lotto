package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AutoTicket {

    private static final int LOTTO_NUMBER_LENGTH = 6;
    private List<Integer> autoNumber = new ArrayList<>();

    public AutoTicket() {
        Collections.shuffle(LottoNumbers.getLottoNumbers());
        for (int i = 0; i < LOTTO_NUMBER_LENGTH; i++) {
            autoNumber.add(LottoNumbers.getLottoNumbers().get(i));
        }
        autoNumber.sort(Comparator.naturalOrder());
    }

    public AutoTicket(List<Integer> input) {
        this.autoNumber = input;
    }

    public List<Integer> getAutoTicket() {
        return autoNumber;
    }
}
