package lotto.domain.number;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {

    @Test
    @DisplayName("로또 넘버에 중복이 있으면 예외")
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

    @Test
    @DisplayName("로또넘버가 6개가 아니면 예외")
    public void lottoNumbersMustHaveSixLottoNumbers() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> new LottoNumbers(
                Arrays.asList(
                    new LottoNumber(1),
                    new LottoNumber(45),
                    new LottoNumber(2),
                    new LottoNumber(3),
                    new LottoNumber(4)
                ))).withMessage("로또 넘버가 6개가 아닙니다.");
    }
}
