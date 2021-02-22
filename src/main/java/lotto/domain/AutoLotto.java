package lotto.domain;

import lotto.util.LottoGenerator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AutoLotto {

    private final List<Lotto> autoLotto;

    public AutoLotto(int autoCount) {
        this.autoLotto = purchaseAutoLotto(autoCount);
    }

    private List<Lotto> purchaseAutoLotto(int count) {
        return Stream.generate(() -> new Lotto(LottoGenerator.make()))
                .limit(count)
                .collect(Collectors.toList());

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
