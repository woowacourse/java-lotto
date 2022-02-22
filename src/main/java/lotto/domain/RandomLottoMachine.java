package lotto.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RandomLottoMachine {

    public static Set<Integer> createRandomLottoNumbers() {
        return new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    }
}
