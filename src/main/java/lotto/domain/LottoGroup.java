package lotto.domain;

import static lotto.common.Constants.LINE_SEPARATOR;

import java.util.List;
import java.util.StringJoiner;

public class LottoGroup {
    private final List<Lotto> item;

    public LottoGroup(List<Lotto> item) {
        this.item = item;
    }

    public static LottoGroup from(List<Lotto> lottoList) {
        return new LottoGroup(lottoList);
    }

    public List<Lotto> getItem() {
        return item;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(LINE_SEPARATOR);

        for (Lotto lotto : item) {
            joiner.add(lotto.toString());
        }

        return joiner.toString();
    }
}
