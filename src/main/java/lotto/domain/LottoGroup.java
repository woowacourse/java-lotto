package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGroup {

    private final List<Lotto> lottoGroup;

    public LottoGroup(List<Lotto> lottoGroup) {
        this.lottoGroup = lottoGroup;
    }

    public List<Lotto> getLottoGroup() {
        return lottoGroup;
    }

    public LottoGroup merge(LottoGroup target) {
        return new LottoGroup(Stream.concat(lottoGroup.stream(), target.getLottoGroup().stream())
            .collect(Collectors.toList()));
    }
}
