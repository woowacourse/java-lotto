package model;


import static org.assertj.core.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusNumberTest {

    @Test
    @DisplayName("보너스 번호는 1~45 사이가 아닌 경우 예외를 발생한다.")
    void bonus_number_out_of_range_test(){
        assertThatThrownBy(() -> new BonusNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자는 1~45 사이여야 합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 로또 번호 내부에 존재하는 경우 true를 반환한다.")
    void is_bonus_number_true(){
        Set<Integer> lottoNumbers = Set.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(lottoNumbers);
        BonusNumber bonusNumber = new BonusNumber(5);

        assertThat(bonusNumber.isBonusNumber(lotto)).isTrue();
    }


}
