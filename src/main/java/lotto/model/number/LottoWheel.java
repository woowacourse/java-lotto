package lotto.model.number;

import java.util.Collections;
import java.util.List;

public class LottoWheel {
    private static final List<LottoNumber> LOTTO_BALLS;

    static {
        LOTTO_BALLS = LottoNumber.ofAllNumbers();
    }

    public static LottoNumber get(int number) {
        return LOTTO_BALLS.stream()
                .filter(lottoNumber -> lottoNumber.getValue() == number)
                .findFirst()
                .orElse(null);
    }

    public static List<LottoNumber> draw(int size) {
        Collections.shuffle(LOTTO_BALLS);
        return LOTTO_BALLS.subList(0, size);
    }
}
