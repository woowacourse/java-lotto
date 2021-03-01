package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGroup {

    private final List<Lotto> lottoGroup;

    public LottoGroup(List<Lotto> lottoGroup) {
        this.lottoGroup = lottoGroup;
    }

    public List<Lotto> getLottoGroup() {
        return Collections.unmodifiableList(lottoGroup);
    }

    public LottoGroup merge(LottoGroup target) {
        return new LottoGroup(Stream.concat(lottoGroup.stream(), target.lottoGroup.stream())
            .collect(Collectors.toList()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoGroup lottos = (LottoGroup) o;
        return Objects.equals(lottoGroup, lottos.lottoGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoGroup);
    }
}
