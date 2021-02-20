package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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

    public LottoGroup addition(LottoGroup lottoGroup) {
        return new LottoGroup(
                Stream.concat(
                        lottos.stream(),
                        lottoGroup.lottos.stream()
                ).collect(toList())
        );
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), lottos.stream()
            .map(LottoNumbers::toString).collect(toList()));
    }


}