package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.exception.LottoNumberException;

public enum LottoNumber {

    NUMBER_1(1), NUMBER_2(2), NUMBER_3(3), NUMBER_4(4), NUMBER_5(5),
    NUMBER_6(6), NUMBER_7(7), NUMBER_8(8), NUMBER_9(9), NUMBER_10(10),
    NUMBER_11(11), NUMBER_12(12), NUMBER_13(13), NUMBER_14(14), NUMBER_15(15),
    NUMBER_16(16), NUMBER_17(17), NUMBER_18(18), NUMBER_19(19), NUMBER_20(20),
    NUMBER_21(21), NUMBER_22(22), NUMBER_23(23), NUMBER_24(24), NUMBER_25(25),
    NUMBER_26(26), NUMBER_27(27), NUMBER_28(28), NUMBER_29(29), NUMBER_30(30),
    NUMBER_31(31), NUMBER_32(32), NUMBER_33(33), NUMBER_34(34), NUMBER_35(35),
    NUMBER_36(36), NUMBER_37(37), NUMBER_38(38), NUMBER_39(39), NUMBER_40(40),
    NUMBER_41(41), NUMBER_42(42), NUMBER_43(43), NUMBER_44(44), NUMBER_45(45);

    private static final String LOTTO_NUMBER_PREFIX = "NUMBER_";
    private static final String REGEX_FOR_NATURAL_NUMBER = "^[1-9][0-9]*$";
    private static final int MINIMUM_RANGE = 1;
    private static final int MAXIMUM_RANGE = 45;

    private final int number;

    LottoNumber(int number) {
        this.number = number;
    }

    public static List<LottoNumber> getRandomLottoNumbers(int pickupCount) {
        return shuffleLottoNumbers().stream()
                .limit(pickupCount)
                .sorted()
                .collect(Collectors.toList());
    }

    private static List<LottoNumber> shuffleLottoNumbers() {
        List<LottoNumber> lottoNumbers = getLottoNumbers();
        Collections.shuffle(lottoNumbers);
        return lottoNumbers;
    }

    private static List<LottoNumber> getLottoNumbers() {
        return Arrays.stream(values())
                .collect(Collectors.toList());
    }

    public static LottoNumber getByString(String input) {
        checkNaturalNumber(input);
        checkRange(input);
        return valueOf(LOTTO_NUMBER_PREFIX + input);
    }

    private static void checkNaturalNumber(String input) {
        if (!isNaturalNumber(input)) {
            throw new LottoNumberException(LottoNumberException.LOTTO_NUMBER_NATURAL_NUMBER_ERROR_MESSAGE);
        }
    }

    private static boolean isNaturalNumber(String input) {
        return Pattern.compile(REGEX_FOR_NATURAL_NUMBER)
                .matcher(input)
                .find();
    }

    private static void checkRange(String input) {
        if (!isCorrectRange(Integer.parseInt(input))) {
            throw new LottoNumberException(LottoNumberException.LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    private static boolean isCorrectRange(int input) {
        return input >= MINIMUM_RANGE && input <= MAXIMUM_RANGE;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
