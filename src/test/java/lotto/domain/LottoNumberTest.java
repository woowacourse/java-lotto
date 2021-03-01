package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @DisplayName("로또번호 확인")
    @Test
    void correctBonusNumber() {
        int number = 11;

        LottoNumber bonusNum = new LottoNumber(number);

        assertThat(bonusNum).isEqualTo(new LottoNumber(11));
    }

    @DisplayName("보너스 볼 생성시 1~45 사이인지 확인")
    @Test
    void validateBonusNumber() {
        int bonusNumber = 46;

        assertThatThrownBy(() -> {
            new LottoNumber(bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 로또 번호입니다.");

    }
}
