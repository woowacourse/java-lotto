package lotto.domain;

import java.util.Arrays;

import lotto.exceptions.InvalidLottoNumberException;

public enum LottoNumber {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    ELEVEN(11),
    TWELVE(12),
    THIRTEEN(13),
    FOURTEEN(14),
    FIFTEEN(15),
    SIXTEEN(16),
    SEVENTEEN(17),
    EIGHTEEN(18),
    NINETEEN(19),
    TWENTY(20),
    TWENTY_ONE(21),
    TWENTY_TWO(22),
    TWENTY_THREE(23),
    TWENTY_FOUR(24),
    TWENTY_FIVE(25),
    TWENTY_SIX(26),
    TWENTY_SEVEN(27),
    TWENTY_EIGHT(28),
    TWENTY_NINE(29),
    THIRTY(30),
    THIRTY_ONE(31),
    THIRTY_TWO(32),
    THIRTY_THREE(33),
    THIRTY_FOUR(34),
    THIRTY_FIVE(35),
    THIRTY_SIX(36),
    THIRTY_SEVEN(37),
    THIRTY_EIGHT(38),
    THIRTY_NINE(39),
    FORTY(40),
    FORTY_ONE(41),
    FORTY_TWO(42),
    FORTY_THREE(43),
    FORTY_FOUR(44),
    FORTY_FIVE(45);

    private final int value;

    LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber find(String value) {
        int parsedValue = getParsedValue(value.trim());
        return Arrays.stream(LottoNumber.values())
            .filter(number -> number.getValue() == parsedValue)
            .findFirst()
            .orElseThrow(() ->
                new InvalidLottoNumberException("잘못된 로또 번호입니다.")
            );
    }

    private static int getParsedValue(String value) {
        return validNumber(value);
    }

    private static int validNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidLottoNumberException("잘못된 로또 번호를 입력하셨습니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
