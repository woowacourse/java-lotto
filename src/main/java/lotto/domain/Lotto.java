package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private List<Integer> lottoNumber;
    public Lotto() {
        this.lottoNumber = generateLotto();
    }
    private List<Integer> generateLotto() {
        List<Integer> lotto = new ArrayList<>();
        while(lotto.size() < 6) {
            int number = generateRandomNumber();
            checkDuplicate(lotto, number);
        }
        Collections.sort(lotto);
        return lotto;
    }

    private int generateRandomNumber() {
        return (int) ((Math.random() * 45) + 1);
    }

    private void checkDuplicate(List<Integer> lotto, int number) {
        if (!lotto.contains(number)) {
            lotto.add(number);
        }
    }

    @Override
    public String toString() {
        return lottoNumber.toString() + System.lineSeparator();
    }
}
