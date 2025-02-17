package model;

import static model.Lotto.LOTTO_SIZE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoMachine {
    public List<Integer> generateLotto() {
        Set<Integer> lotto = new HashSet<>();
        while (lotto.size() < LOTTO_SIZE) {
            int number = (int) (Math.random() * 45) + 1;
            lotto.add(number);
        }
        return new ArrayList<>(lotto);
    }
}
