package model;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {

    @ParameterizedTest
    @DisplayName("보너스 번호의 범위를 벗어난 경우 예외가 발생한다.")
    @ValueSource(ints = {0, 46, -1, 100})
    void 보너스_번호의_범위를_벗어난_경우_예외가_발생한다(int number) {
        assertThatThrownBy(() -> new BonusNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자는 1~45 사이여야 합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 로또 번호 내부에 존재하는 경우 true를 반환한다.")
    void 보너스_번호가_로또_번호_내부에_존재하는_경우_true를_반환한다() {
        Lotto lotto = new Lotto(new TestLottoNumberGenerator(List.of(1, 2, 3, 4, 5, 6)));
        BonusNumber bonusNumber = new BonusNumber(5);

        assertThat(bonusNumber.isBonusMatch(lotto)).isTrue();
    }


}
