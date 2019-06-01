package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lottos {
    private final List<Lotto> totalLottos;

    public Lottos(List<String> manualInputLottos, int totalLottoCount) {
        checkValidManualCount(totalLottoCount, manualInputLottos.size());
        this.totalLottos = new ArrayList<>();
        generateManualLottos(manualInputLottos);
        generateRandomLottos(totalLottoCount - manualInputLottos.size());
    }

    private void checkValidManualCount(int totalLottoCount, int manualLottoCount) {
        if (totalLottoCount < manualLottoCount) {
            throw new IllegalArgumentException("수동 구매 개수는 전체 구매 개수보다 작아야 합니다.");
        }
    }

    private void generateManualLottos(List<String> manualInputLottos) {
        for (String manualInputLotto : manualInputLottos) {
            totalLottos.add(generateManualLotto(manualInputLotto));
        }
    }

    private Lotto generateManualLotto(String manualInputLotto) {
        LottoManualGenerator lottoManualGenerator = new LottoManualGenerator(manualInputLotto);
        return new Lotto(lottoManualGenerator.generate());
    }

    private void generateRandomLottos(int randomLottoCount) {
        for (int i = 0; i < randomLottoCount; i++) {
            totalLottos.add(generateRandomLotto());
        }
    }

    private Lotto generateRandomLotto() {
        LottoRandomGenerator lottoRandomGenerator = new LottoRandomGenerator();
        return new Lotto(lottoRandomGenerator.generate());
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