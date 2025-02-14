package lotto.model;

import java.util.Set;

public class LottoFixtures {

    public static Lotto createLottoOneToSix() {
        return new Lotto(Set.of(1, 2, 3, 4, 5, 6));
    }

    public static Lotto createByNumbers(Set<Integer> numbers) {
        return new Lotto(numbers);
    }
}
