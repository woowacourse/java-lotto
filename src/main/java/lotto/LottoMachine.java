package lotto;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LottoMachine {
    public Set<Integer> createLotto() {
        Set<Integer> lotto = new HashSet<>();
        while (lotto.size() < 6) {
            int number = new Random().nextInt(44) + 1;
            lotto.add(number);
        }
        return lotto;
    }
}
