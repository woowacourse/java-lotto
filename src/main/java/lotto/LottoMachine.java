package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LottoMachine {
    public List<Integer> createLotto() {
        Set<Integer> lotto = new HashSet<>();
        while (lotto.size() < 6) {
            int number = new Random().nextInt(44) + 1;
            lotto.add(number);
        }
        List<Integer> sortedLotto = new ArrayList<>(lotto);
        Collections.sort(sortedLotto);
        return sortedLotto;
    }
}
