package lotto.domain;

import static lotto.common.Constants.LINE_SEPARATOR;

import java.util.List;
import java.util.StringJoiner;

public class LottoGroup {
    private final List<Lotto> lottos;

    public LottoGroup(List<Lotto> item) {
        this.lottos = item;
    }

    public static LottoGroup from(List<Lotto> lottoList) {
        return new LottoGroup(lottoList);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(LINE_SEPARATOR);

        for (Lotto lotto : lottos) {
            joiner.add(lotto.toString());
        }

        return joiner.toString();
    }
}
