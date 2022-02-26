package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.model.number.LottoNumber;
import lotto.model.number.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {
    @DisplayName("보너스 번호가 당첨 번호들과 중복되면 예외가 발생한다")
    @Test
    void duplicate_exception() {
        WinningNumbers winningNumbers = WinningNumbers.from(new String[] {"1", "2", "3", "4", "5", "6"});
        LottoNumber bonusNumber = LottoNumber.from("1");

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다");
    }
}
