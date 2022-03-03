package lotto.domain;

import java.util.List;

public class LottoTestDataGenerator {

    public static List<List<Integer>> generateLottoTickets() {
        return List.of(
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44)
        );
    }

    public static List<Integer> generateNumbers() {
        return List.of(8, 21, 23, 41, 42, 43);
    }
}
