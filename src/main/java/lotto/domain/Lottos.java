package lotto.domain;

import lotto.domain.generator.LottosGenerator;

import java.util.List;
import java.util.Objects;

public class Lottos {
    private static final String VALID_COUNT_MESSAGE = "수동 구매 개수는 전체 구매 개수보다 작아야 합니다.";
    private final List<Lotto> totalLottos;

    public Lottos(List<String> manualInputLottos, int totalLottoCount) {
        checkValidManualCount(totalLottoCount, manualInputLottos.size());
        this.totalLottos = LottosGenerator.generate(manualInputLottos, totalLottoCount);
    }

    private void checkValidManualCount(int totalLottoCount, int manualLottoCount) {
        if (totalLottoCount < manualLottoCount) {
            throw new IllegalArgumentException(VALID_COUNT_MESSAGE);
        }
    }

    public int getLottoCount() {
        return this.totalLottos.size();
    }

    public Lotto getLottoByIndex(int index) {
        return totalLottos.get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottos lottos = (Lottos) o;
        return Objects.equals(totalLottos, lottos.totalLottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalLottos);
    }
}