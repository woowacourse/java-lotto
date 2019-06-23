package lotto.service;

import lotto.domain.LottoCount;
import lotto.domain.Money;

import java.util.regex.Pattern;

public class LottoCountParser {
    static final Pattern LOTTO_COUNT_FORMAT = Pattern.compile("^[0-9]+$");

    public static LottoCount parse(Money money, String input) {
        if (!LOTTO_COUNT_FORMAT.matcher(input).find()) {
            throw new IllegalArgumentException("로또의 개수는 숫자 입니다.");
        }
        return new LottoCount(money, Integer.parseInt(input));
    }
}
