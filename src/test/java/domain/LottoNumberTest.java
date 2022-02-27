package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoNumberTest {

    @Test
    @DisplayName("같은 숫자를 가진 로또 넘버 객체는 동일하다.")
    void lottoNumberEqual() {
        Number lottoNumber1 = new Number(12);
        Number lottoNumber2 = new Number(12);

        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }

    @DisplayName("보너스 번호가 범위를 벗어난 경우 예외가 발생한다.")
    @ParameterizedTest(name = "{index} {displayName} bonus={0}")
    @ValueSource(ints = {0, -1, 46})
    void checkBonusOutRange_throwIllegalException(final int bonus) {
        assertThatThrownBy(() -> new Number(bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1에서 45 사이의 값을 입력해줘야 합니다.");
    }

}
