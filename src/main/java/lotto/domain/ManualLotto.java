package lotto.domain;

import java.util.List;
import java.util.Objects;

public class ManualLotto {

    private List<Lotto> manualLotto;

    public ManualLotto(List<Lotto> manualLotto) {
        this.manualLotto = manualLotto;
    }

    public List<Lotto> getManualLotto() {
        return manualLotto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ManualLotto)) return false;

        ManualLotto that = (ManualLotto) o;

        return Objects.equals(manualLotto, that.manualLotto);
    }

    @Override
    public int hashCode() {
        return manualLotto != null ? manualLotto.hashCode() : 0;
    }
}
