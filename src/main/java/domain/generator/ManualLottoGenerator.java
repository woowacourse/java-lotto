package domain.generator;

import domain.Lotto;
import java.util.List;

public class ManualLottoGenerator implements LottoGenerator {

    private final Lotto manualLotto;

    public ManualLottoGenerator(final List<String> manualLotto) {
        this.manualLotto = Lotto.fromInput(manualLotto);
    }

    @Override
    public Lotto generate() {
        return this.manualLotto;
    }
}
