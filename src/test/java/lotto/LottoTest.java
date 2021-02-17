package lotto;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

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
}
