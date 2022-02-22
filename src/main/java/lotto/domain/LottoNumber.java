package lotto.domain;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum LottoNumber {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    ELEVEN(11), TWELVE(12), THIRTEEN(13), FOURTEEN(14), FIFTEEN(15),
    SIXTEEN(16), SEVENTEEN(17), EIGHTEEN(18), NINETEEN(19), TWENTY(20),
    TWENTY_ONE(21), TWENTY_TWO(22), TWENTY_THREE(23), TWENTY_FOUR(24), TWENTY_FIVE(25),
    TWENTY_SIX(26), TWENTY_SEVEN(27), TWENTY_EIGHT(28), TWENTY_NINE(29), THIRTY(30),
    THIRTY_ONE(31), THIRTY_TWO(32), THIRTY_THREE(33), THIRTY_FOUR(34), THIRTY_FIVE(35),
    THIRTY_SIX(36), THIRTY_SEVEN(37), THIRTY_EIGHT(38), THIRTY_NINE(39), FORTY(40),
    FORTY_ONE(41), FORTY_TWO(42), FORTY_THREE(43), FORTY_FOUR(44), FORTY_FIVE(45);

    private static final Pattern LOTTO_NUMBER_REGEX = Pattern.compile("^[0-9]");
    private static final String INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE = "로또 번호는 1 ~ 45 사이의 자연수여야합니다.";

    private final int number;

    LottoNumber(final int value) {
        this.number = value;
    }

    public static LottoNumber from(final String value) {
        validateNotNegativeInteger(value);
        return Arrays.stream(values())
                .filter(number -> number.number == Integer.parseInt(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE));
    }

    private static void validateNotNegativeInteger(final String value) {
        if (!LOTTO_NUMBER_REGEX.matcher(value).matches()) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE);
        }
    }
}
