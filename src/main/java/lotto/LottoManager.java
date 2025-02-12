package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LottoManager {
    public List<Set<Integer>> purchase(final int amount) {
        validateAmount(amount);
        return new ArrayList<>();
    }

    private static void validateAmount(final int amount) {
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("구입금액은 1000원으로 나누어져야 합니다.");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("구입금액은 양수여야 합니다.");
        }
    }
}
