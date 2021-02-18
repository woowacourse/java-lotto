package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {

    @Test
    @DisplayName("로또 넘버에 중복이 있으면 예외")
    public void duplicateLottoNumber() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> LottoNumbers.valueOf("1,1,2,3,4,5"))
            .withMessage("로또 넘버에 중복이 있습니다.");
    }

    @Test
    @DisplayName("로또넘버가 6개가 아니면 예외")
    public void lottoNumbersMustHaveSixLottoNumbers() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> LottoNumbers.valueOf("1,2,3,4,5"))
            .withMessage("로또 넘버가 6개가 아닙니다.");
    }
}