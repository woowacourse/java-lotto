package lotto.domain;

import static lotto.constants.ErrorConstants.ERROR_NULL_MESSAGE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        Objects.requireNonNull(lottos, ERROR_NULL_MESSAGE);

        this.lottos = new ArrayList<>(lottos);
    }

    public Map<LottoPrize, Integer> confirmWinnings(WinningLotto winningLotto) {
        Objects.requireNonNull(winningLotto, ERROR_NULL_MESSAGE);
        Map<LottoPrize, Integer> result = new EnumMap<>(LottoPrize.class);
        putZeroLottoPrize(result);
        confirmLottoMatch(winningLotto, result);

        return result;
    }

    private void confirmLottoMatch(WinningLotto winningLotto, Map<LottoPrize, Integer> result) {
        for (Lotto lotto : lottos) {
            LottoPrize prize = lotto.confirmWinning(winningLotto);
            result.merge(prize, 1, Integer::sum);
        }
    }

    private void putZeroLottoPrize(Map<LottoPrize, Integer> result) {
        for (LottoPrize prize : LottoPrize.values()) {
            result.put(prize, 0);
        }
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lottos lottos1 = (Lottos) o;
        return Objects.equals(lottos, lottos1.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
