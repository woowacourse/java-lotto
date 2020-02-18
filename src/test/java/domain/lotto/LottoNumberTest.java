package domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @Test
    void testLottoNumber() {
        int expected = 3;
        LottoNumber lottoNumber = new LottoNumber(expected);
        assertThat(lottoNumber.getNumber()).isEqualTo(expected);
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

    public static LottoNumber getLottoNumberOneFixture() {
        return new LottoNumber(1);
    }

    public static LottoNumber getLottoNumberSevenFixture() {
        return new LottoNumber(7);
    }
}
