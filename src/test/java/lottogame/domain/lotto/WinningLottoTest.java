package lottogame.domain.lotto;

import lottogame.utils.ManualLottoGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WinningLottoTest {
    private ManualLottoGenerator manualLottoGenerator;
    private Lotto lotto;
    private LottoNumber bonusNumber;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        manualLottoGenerator = new ManualLottoGenerator("1, 2, 3, 4, 5, 6");
        lotto = manualLottoGenerator.generateLotto();
        bonusNumber = LottoNumber.of(7);
        winningLotto = new WinningLotto(lotto, bonusNumber);
    }

    @Test
    @DisplayName("같은 값을 가지면 같은 객체인지 확인")
    void constructor1() {
        manualLottoGenerator = new ManualLottoGenerator("1, 2, 3, 4, 5, 6");
        lotto = manualLottoGenerator.generateLotto();
        bonusNumber = LottoNumber.of(7);
        WinningLotto newWinningLotto = new WinningLotto(lotto, bonusNumber);
        assertEquals(newWinningLotto, winningLotto);
    }

    @Test
    @DisplayName("로또번호에 보너스번호와 같은 숫자가 있으면 예외가 발행하는지 확인")
    void constructor2() {
        bonusNumber = LottoNumber.of(6);
        assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스번호는 로또번호와 달라야 합니다.");
    }
}
