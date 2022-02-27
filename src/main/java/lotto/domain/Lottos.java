package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Lottos {

    private static final String ERROR_NULL_MESSAGE = "입력된 값이 null이면 안됩니다.";

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        Objects.requireNonNull(lottos, ERROR_NULL_MESSAGE);

        this.lottos = new ArrayList<>(lottos);
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
