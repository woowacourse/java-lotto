package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.model.number.LottoNumber;

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
        return new ArrayList<>(LOTTO_BALLS.subList(0, size));
    }
}
