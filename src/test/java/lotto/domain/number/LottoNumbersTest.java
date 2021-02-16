package lotto.domain.number;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {

    @Test
    public void duplicateLottoNumber() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> new LottoNumbers(
                Arrays.asList(
                    new LottoNumber(1),
                    new LottoNumber(1),
                    new LottoNumber(2),
                    new LottoNumber(3),
                    new LottoNumber(4),
                    new LottoNumber(5)
                ))).withMessage("로또 넘버에 중복이 있습니다.");
    }
}
