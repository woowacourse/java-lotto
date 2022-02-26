package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.generator.CustomLottoGenerator;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    @DisplayName("LottoGame 생성자 테스트")
    @Test
    void lottoGame_constructor_test() {
        assertThatNoException().isThrownBy(LottoGame::new);
    }

    @DisplayName("purchase 메서드 테스트")
    @Test
    void purchase_test() {
        LottoGame lottoGame = new LottoGame();
        lottoGame.purchase(new Money(10000), new CustomLottoGenerator());
        List<Lotto> lottos = lottoGame.getLottos();

        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }

        assertThat(lottos).hasSize(10);
        assertThat(lottos).allSatisfy(lotto -> lotto.getNumbers().containsAll(lottoNumbers));
    }

    @DisplayName("confirmWinnings 메서드 테스트")
    @Test
    void confirmWinnings_test() {
        List<LottoNumber> lottoNumbers;
        LottoNumber bonusNumber = new LottoNumber(30);
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }

        LottoGame lottoGame = new LottoGame();
        lottoGame.purchase(new Money(10000), new CustomLottoGenerator());
        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(lottoNumbers), bonusNumber);
        LottoResults results = lottoGame.confirmWinnings(winningNumbers);
        assertThat(results).isInstanceOf(LottoResults.class);
        assertThat(results.getPrizeNumber(LottoPrize.FIRST)).isEqualTo(10);
    }
}
