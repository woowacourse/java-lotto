package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @DisplayName("당첨 번호와 보너스 번호가 중복되면 예외가 발생한다.")
    @Test
    void createWinningLottoWithDuplication() {
        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));
        LottoNumber bonusNumber = new LottoNumber(1);

        assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호와 보너스 번호는 중복될 수 없습니다.");

    }


}
