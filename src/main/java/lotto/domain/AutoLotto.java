package lotto.domain;

import lotto.util.LottoGenerator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AutoLotto {

    private final List<Lotto> autoLotto;

    private AutoLotto(List<Lotto> autoLotto) {
        this.autoLotto = autoLotto;
    }

    public static AutoLotto createAutoLotto(int count) {
        return new AutoLotto(Stream.generate(() -> new Lotto(LottoGenerator.makeLottoNumbers()))
                .limit(count)
                .collect(Collectors.toList()));

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
