package lotto.domain;

import static lotto.common.Constants.LINE_SEPARATOR;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class LottoGroup {
    private final List<Lotto> item = new ArrayList<>();

    public static LottoGroup create() {
        return new LottoGroup();
    }

    public void add(List<Integer> lottoNumbers) {
        item.add(Lotto.from(convertToLottoNumbers(lottoNumbers)));
    }

    public List<Lotto> getItem() {
        return item;
    }

    private List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
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
