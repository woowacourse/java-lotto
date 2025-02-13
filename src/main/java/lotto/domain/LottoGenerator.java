package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.constant.LottoConstants;

public class LottoGenerator {

    public int generateRandomNumber(int start, int end) {
        return (int) ((Math.random() * end) + start);
    }

    public List<Integer> generateLotto() {
        List<Integer> lotto = new ArrayList<>();
        while(lotto.size() < LottoConstants.LENGTH.getNumber()) {
            int number = generateRandomNumber(LottoConstants.LOTTO_MINIMUM_NUMBER.getNumber(), LottoConstants.LOTTO_MAXIMUM_NUMBER.getNumber());
            checkDuplicate(lotto, number);
        }
        Collections.sort(lotto);
        return lotto;
    }

    private void checkDuplicate(List<Integer> lotto, int number) {
        if (!lotto.contains(number)) {
            lotto.add(number);
        }
    }

}
