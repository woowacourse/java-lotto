package lotto.model;

import lotto.utils.LottoRules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private List<Integer> autoNumber = new ArrayList<>();

    public LottoTicket() {
        Collections.shuffle(LottoNumbers.getLottoNumbers());
        for (int i = 0; i < LottoRules.LOTTO_NUMBER_LENGTH.getNumber(); i++) {
            autoNumber.add(LottoNumbers.getLottoNumber(i));
        }
        Collections.sort(autoNumber);
    }

    public LottoTicket(List<Integer> customizedAutoNumber) {
        this.autoNumber = customizedAutoNumber;
    }

    public List<Integer> getAutoNumber() {
        return autoNumber;
    }

    public boolean contains(int bonusBall) {
        return autoNumber.contains(bonusBall);
    }
}
