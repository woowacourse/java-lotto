package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.model.number.LottoNumber;

public class LottoWheel {
    private static final String ERROR_TYPE = "[ERROR] 로또 번호는 숫자로만 입력해주세요";
    public static final int LOTTO_SIZE = 6; //없애야 함
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

    public static LottoNumber getNumber(String input) {
        try {
            int number = Integer.parseInt(input.trim());
            return LottoWheel.getNumber(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TYPE);
        }
    }

    public static Lotto draw() {
        Collections.shuffle(LOTTO_BALLS);
        List<LottoNumber> numbers = new ArrayList<>(LOTTO_BALLS.subList(0, LOTTO_SIZE));
        return new Lotto(numbers, true);
    }

    public static Lotto from(List<String> inputs) {
        List<LottoNumber> numbers = inputs.stream()
                .map(LottoWheel::getNumber)
                .collect(Collectors.toList());
        return new Lotto(numbers, false);
    }
}
