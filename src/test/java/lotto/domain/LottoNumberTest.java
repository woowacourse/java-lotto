package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoNumberTest {

    @DisplayName("로또 번호 범위 밖의 숫자가 들어올 때 예외 테스트")
    @Test
    void testValidateNumber() {
        assertThatIllegalArgumentException().isThrownBy(() -> LottoNumber.from("46"));
        assertThatIllegalArgumentException().isThrownBy(() -> LottoNumber.from("0"));
    }

    @DisplayName("캐시를 이용해 LottoNumber를 생성하도록 테스트")
    @Test
    void testCreateLottoNumber() {
        assertThat(LottoNumber.from(4)).isSameAs(LottoNumber.from("4"));
    }
}
