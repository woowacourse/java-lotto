package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGroup {

    private final List<LottoNumbers> lottos;

    public LottoGroup(List<LottoNumbers> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public int getCount() {
        return lottos.size();
    }

    public List<LottoNumbers> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), lottos.stream()
            .map(LottoNumbers::toString).collect(Collectors.toList()));
    }
}