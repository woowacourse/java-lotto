package lotto.domain;

import java.util.List;
import java.util.Objects;

public class AutoLotto {

    private final List<Lotto> autoLotto;

    public AutoLotto(List<Lotto> autoLotto) {
        this.autoLotto = autoLotto;
    }

    public List<Lotto> getAutoLotto() {
        return autoLotto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AutoLotto)) return false;

        AutoLotto autoLotto1 = (AutoLotto) o;

        return Objects.equals(autoLotto, autoLotto1.autoLotto);
    }

    @Override
    public int hashCode() {
        return autoLotto != null ? autoLotto.hashCode() : 0;
    }
}
