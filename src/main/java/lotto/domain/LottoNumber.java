package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoNumber {
    private static final int MIN = 1;
    private static final int MAX = 45;

    private static final String NUMBER_RANGE_ERROR = "로또 숫자는 " + MIN + " 이상 " + MAX + " 이하의 숫자만 가능합니다.";
    public static final String TYPE_ERROR = "로또 번호는 숫자만 가능합니다.";

    private static final List<LottoNumber> lottoNumbers = new ArrayList<>();

    private final int number;

    static {
        for (int i = MIN; i <= MAX; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber of(String input) {
        int number = convertToInt(input);
        validateNumber(number);
        return lottoNumbers.get(number - 1);
    }

    public int toInt() {
        return number;
    }

    private static int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(TYPE_ERROR);
        }
    }

    private static void validateNumber(int number) {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "number=" + number +
                '}';
    }
}
