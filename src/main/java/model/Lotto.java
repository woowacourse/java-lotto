package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    public static final int LOTTO_SIZE = 6;
    private final List<Integer> lotto;

    public Lotto() {
        this.lotto = generateLotto();
    }

    public List<Integer> getLotto() {
        return Collections.unmodifiableList(lotto);
    }

    private List<Integer> generateLotto() {
        Set<Integer> lotto = new HashSet<>();
        while (lotto.size() < LOTTO_SIZE) {
            int number = (int) (Math.random() * 45) + 1;
            lotto.add(number);
        }
        return new ArrayList<>(lotto);
    }
}
