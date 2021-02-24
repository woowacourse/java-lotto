package lotto.domain;

import lotto.util.LottoFactory;

import java.util.List;
import java.util.Objects;

public class ManualLotto extends LottoFactory {

    private List<Lotto> manualLotto;

    public ManualLotto(List<Lotto> manualLotto) {
        super();
        this.manualLotto = manualLotto;
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

    @Override
    public List<Lotto> getLottos() {
        return manualLotto;
    }
}
