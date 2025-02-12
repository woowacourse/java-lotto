package domain;

import java.util.HashSet;
import java.util.Set;

public class LottoMachine {

    public Lotto createLotto() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() != Lotto.NUMBERS_SIZE) {
            numbers.add(selectNumber());
        }
        return new Lotto(numbers);
    }

    private int selectNumber() {
        return (int) (Math.random() * 45) + 1;
    }
}
