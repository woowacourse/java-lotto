package lotto.domain.lotto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @Test
    void testLottoNumber() {
        int expected = 3;
        LottoNumber lottoNumber = new LottoNumber(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void createLottoNumberThrowsException(int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    public static Set<LottoNumber> getProperLottoNumbersFixture() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(new LottoNumber(1));
        numbers.add(new LottoNumber(2));
        numbers.add(new LottoNumber(3));
        numbers.add(new LottoNumber(4));
        numbers.add(new LottoNumber(5));
        numbers.add(new LottoNumber(6));
        return numbers;
    }

    public static Set<LottoNumber> getNotProperLottoNumbersFixture() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(new LottoNumber(1));
        numbers.add(new LottoNumber(2));
        numbers.add(new LottoNumber(3));
        numbers.add(new LottoNumber(4));
        numbers.add(new LottoNumber(5));
        numbers.add(new LottoNumber(5));
        return numbers;
    }

    public static Set<LottoNumber> getFromOneToSevenWithoutSixFixture() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(new LottoNumber(1));
        numbers.add(new LottoNumber(2));
        numbers.add(new LottoNumber(3));
        numbers.add(new LottoNumber(4));
        numbers.add(new LottoNumber(5));
        numbers.add(new LottoNumber(7));
        return numbers;
    }

    public static Set<LottoNumber> getLottoNumberFromTwoToEightWithoutSevenFixture() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(new LottoNumber(2));
        numbers.add(new LottoNumber(3));
        numbers.add(new LottoNumber(4));
        numbers.add(new LottoNumber(5));
        numbers.add(new LottoNumber(6));
        numbers.add(new LottoNumber(8));
        return numbers;
    }

    public static Set<LottoNumber> getLottoNumberFromThreeToEightFixture() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(new LottoNumber(3));
        numbers.add(new LottoNumber(4));
        numbers.add(new LottoNumber(5));
        numbers.add(new LottoNumber(6));
        numbers.add(new LottoNumber(7));
        numbers.add(new LottoNumber(8));
        return numbers;
    }

    public static Set<LottoNumber> getLottoNumberFromFourToNineFixture() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(new LottoNumber(4));
        numbers.add(new LottoNumber(5));
        numbers.add(new LottoNumber(6));
        numbers.add(new LottoNumber(7));
        numbers.add(new LottoNumber(8));
        numbers.add(new LottoNumber(9));
        return numbers;
    }

    public static Set<LottoNumber> getLottoNumberFromFiveToTenFixture() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(new LottoNumber(5));
        numbers.add(new LottoNumber(6));
        numbers.add(new LottoNumber(7));
        numbers.add(new LottoNumber(8));
        numbers.add(new LottoNumber(9));
        numbers.add(new LottoNumber(10));
        return numbers;
    }

    public static LottoNumber getLottoNumberOneFixture() {
        return new LottoNumber(1);
    }

    public static LottoNumber getLottoNumberSevenFixture() {
        return new LottoNumber(7);
    }
}
