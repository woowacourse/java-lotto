package lotto.domain.vo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lotto.domain.LottoPrize;

public class Lottos {

    private static final String ERROR_NULL_MESSAGE = "null로 Lottos를 생성할 수 없습니다.";

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        validateNull(lottos);

        this.lottos = lottos;
    }

    private void validateNull(List<Lotto> lottos) {
        if (lottos == null) {
            throw new NullPointerException(ERROR_NULL_MESSAGE);
        }
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public Map<LottoPrize, Integer> confirmWinnings(WinningNumbers winningNumbers) {
        Map<LottoPrize, Integer> result = new HashMap<>();
        for (LottoPrize prize : LottoPrize.values()) {
            result.put(prize, 0);
        }

        for (Lotto lotto : lottos) {
            LottoPrize prize = lotto.confirmWinning(winningNumbers);
            result.put(prize, result.get(prize) + 1);
        }

        return Collections.unmodifiableMap(result);
    }

    @Override
    public String toString() {
        return "Lottos{" +
                "lottos=" + lottos +
                '}';
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
