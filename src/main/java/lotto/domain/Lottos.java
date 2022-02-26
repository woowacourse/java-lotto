package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {

    private static final String ERROR_NULL_MESSAGE = "입력된 값이 null이면 안됩니다.";

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        validateNull(lottos);
        this.lottos = new ArrayList<>(lottos);
    }

    private void validateNull(List<Lotto> numbers) {
        if (numbers == null) {
            throw new NullPointerException(ERROR_NULL_MESSAGE);
        }
    }

    public Map<LottoPrize, Integer> confirmWinnings(WinningNumbers winningNumbers) {
        Map<LottoPrize, Integer> result = new EnumMap<>(LottoPrize.class);
        for (LottoPrize prize : LottoPrize.values()) {
            result.put(prize, 0);
        }

        for (Lotto lotto : lottos) {
            LottoPrize prize = lotto.confirmWinning(winningNumbers);
            result.put(prize, result.get(prize) + 1);
        }

        return result;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
