package lotto.model.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberFactory {
    private static final String ERROR_TYPE = "[ERROR] 로또 번호는 숫자로만 입력해주세요";
    private static final List<LottoNumber> LOTTO_BALLS;

    static {
        LOTTO_BALLS = LottoNumber.ofAllNumbers();
    }

    private static LottoNumber getNumber(int number) {
        return LOTTO_BALLS.stream()
                .filter(lottoNumber -> lottoNumber.getValue() == number)
                .findFirst()
                .orElse(new LottoNumber(number));
    }

    public static LottoNumber getNumber(String rawNumber) {
        try {
            int number = Integer.parseInt(rawNumber.trim());
            return LottoNumberFactory.getNumber(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TYPE);
        }
    }

    public static List<LottoNumber> getRandomNumbers(int size) {
        Collections.shuffle(LOTTO_BALLS);
        return new ArrayList<>(LOTTO_BALLS.subList(0, size));
    }
}
