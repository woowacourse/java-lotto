package lotto;

import static lotto.Lotto.LOTTO_SIZE;
import static lotto.Lotto.MAX_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LottoMachine {
    private LottoMachine() {
    }

    public static Lotto createLotto() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < LOTTO_SIZE) {
            int number = new Random().nextInt(MAX_LOTTO_NUMBER - 1) + 1;
            numbers.add(number);
        }
        return new Lotto(new ArrayList<>(numbers));
    }
}
