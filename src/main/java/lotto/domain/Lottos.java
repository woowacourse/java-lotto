package lotto.domain;

import java.util.List;
import java.util.Objects;

public class Lottos {
    private final ManualLotto manualLotto;
    private final AutoLotto autoLotto;

    public Lottos(ManualLotto manualLotto, AutoLotto autoLotto) {
        this.manualLotto = manualLotto;
        this.autoLotto = autoLotto;
    }

    public List<Lotto> getManualLotto() {
        return manualLotto.getManualLotto();
    }

    public List<Lotto> getAutoLotto() {
        return autoLotto.getAutoLotto();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lottos)) return false;

        Lottos lottos = (Lottos) o;

        if (!Objects.equals(manualLotto, lottos.manualLotto)) return false;
        return Objects.equals(autoLotto, lottos.autoLotto);
    }

    @Override
    public int hashCode() {
        int result = manualLotto != null ? manualLotto.hashCode() : 0;
        result = 31 * result + (autoLotto != null ? autoLotto.hashCode() : 0);
        return result;
    }
}
