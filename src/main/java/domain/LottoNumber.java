package domain;

import java.util.Arrays;
import java.util.List;

public enum LottoNumber {
    ERROR(-1),
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
    TWENTYONE(21),
    TWENTYTWO(22),
    TWENTYTHREE(23),
    TWENTYFOUR(24),
    TWENTYFIVE(25),
    TWENTYSIX(26),
    TWENTYSEVEN(27),
    TWENTYEIGHT(28),
    TWENTYNINE(29),
    THIRTY(30),
    THIRTYONE(31),
    THIRTYTWO(32),
    THIRTYTHREE(33),
    THIRTYFOUR(34),
    THIRTYFIVE(35),
    THIRTYSIX(36),
    THIRTYSEVEN(37),
    THIRTYEIGHT(38),
    THIRTYNINE(39),
    FOURTY(40),
    FOURTYONE(41),
    FOURTYTWO(42),
    FOURTYTHREE(43),
    FOURTYFOUR(44),
    FOURTYFIVE(45);

//    private static final int MIN_LOTTO_NUMBER = 1;
//    private static final int MAX_LOTTO_NUMBER = 45;

    private final int number;

    LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber getLottoNumber(int number) {
        List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumber.values());

        return lottoNumbers.stream()
                .filter(lottoNumber -> lottoNumber.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("1부터 45사이의 숫자를 입력해주세요."));
    }
//
//    private static void validateNumber(int number) {
//        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
//            throw new IllegalArgumentException("1부터 45사이의 숫자를 입력해주세요.");
//        }
//    }
}
