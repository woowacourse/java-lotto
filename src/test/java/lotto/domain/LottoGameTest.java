package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGameTest {

    @DisplayName("LottoGame 생성자는 인자를 입력받지 않는다.")
    @Test
    void constructor() {
        assertThatNoException().isThrownBy(LottoGame::new);
    }

    @DisplayName("purchase 메서드는 입력한 money만큼의 Lotto를 구매하여 Lottos에 추가한다")
    @Test
    void purchase() {
        LottoGame lottoGame = new LottoGame();
        lottoGame.purchase(new Money(10000));

        List<Lotto> lottoResults = lottoGame.getLottos();

        assertThat(lottoResults.size()).isEqualTo(10);
    }

    @DisplayName("confirmWinnings 메서드는 당첨 번호를 입력받아 당첨 결과인 LottoResults를 반환한다.")
    @Test
    void confirmWinnings() {
        List<LottoNumber> lottoNumbers;
        LottoNumber bonusNumber = new LottoNumber(30);
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }

        LottoGame lottoGame = new LottoGame();
        lottoGame.purchase(new Money(10000));
        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, bonusNumber);
        assertThat(lottoGame.confirmWinnings(winningNumbers)).isInstanceOf(LottoResults.class);
    }
}
