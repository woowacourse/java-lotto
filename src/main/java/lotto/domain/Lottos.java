package lotto.domain;

import java.util.List;

public class Lottos {
    private final List<Lotto> manualLotto;
    private final List<Lotto> autoLotto;

    public Lottos(ManualLotto manualLotto, AutoLotto autoLotto) {
        this.manualLotto = manualLotto.getManualLotto();
        this.autoLotto = autoLotto.getAutoLotto();
    }

    public List<Lotto> getManualLotto() {
        return manualLotto;
    }

    public List<Lotto> getAutoLotto() {
        return autoLotto;
    }
}
