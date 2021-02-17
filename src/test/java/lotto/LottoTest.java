package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoTest {

    @Test
    void init() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // when
        ThrowingCallable throwingCallable
            = () -> Lotto.fromGenerator(new FixedNumberGenerator(numbers));

        // then
        assertThatCode(throwingCallable).doesNotThrowAnyException();
    }

    @Test
    void init_NotMatchLength_ExceptionThrown() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // when
        ThrowingCallable throwingCallable
            = () -> Lotto.fromGenerator(new FixedNumberGenerator(numbers));

        // then
        assertThatIllegalArgumentException().isThrownBy(throwingCallable);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:true", "7:false"}, delimiter = ':')
    void contains_containLottoNumber_TrueOrFalse(int lottoNum, boolean expected) {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.fromGenerator(new FixedNumberGenerator(numbers));
        LottoNumber lottoNumber = LottoNumber.from(lottoNum);

        // when
        boolean actual = lotto.contains(lottoNumber);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}