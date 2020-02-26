package lotto.domain.LottoRule;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.exceptions.InvalidLottoNumberException;

public class LottoBalls {
    private static List<LottoNumber> lottoBalls;

    private static final int LOWER_LIMIT = 1;
    private static final int UPPER_LIMIT = 45;

    static {
        lottoBalls = IntStream.rangeClosed(LOWER_LIMIT, UPPER_LIMIT)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toList());
    }

    public static LottoNumber find(String value) {
        return lottoBalls.stream().filter(number -> number.getValue() == getParsedValue(value))
            .findFirst()
            .orElseThrow(() -> new InvalidLottoNumberException("범위를 벗어난 잘못된 로또 번호입니다."));
    }

    private static int getParsedValue(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidLottoNumberException("당첨 번호는 숫자로 이루어져 있어야 합니다.");
        }
    }

    public static List<LottoNumber> getLottoBalls() {
        return lottoBalls;
    }
}
