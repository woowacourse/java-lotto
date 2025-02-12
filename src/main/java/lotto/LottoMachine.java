package lotto;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LottoMachine {
    public Lotto createLotto() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < 6) {
            int number = new Random().nextInt(44) + 1;
            numbers.add(number);
        }
        return new Lotto(numbers);
    }
}
