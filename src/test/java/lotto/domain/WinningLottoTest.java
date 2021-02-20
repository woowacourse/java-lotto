package lotto.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class WinningLottoTest {

    static Lotto lotto;

    @BeforeAll
    static void beforeAll() {
        LottoNumber number1 = new LottoNumber("1");
        LottoNumber number2 = new LottoNumber("2");
        LottoNumber number3 = new LottoNumber("3");
        LottoNumber number4 = new LottoNumber("4");
        LottoNumber number5 = new LottoNumber("5");
        LottoNumber number6 = new LottoNumber("6");
        List<LottoNumber> lottoNumbers = Arrays.asList(number1, number2, number3, number4, number5, number6);
        lotto = new Lotto(lottoNumbers);
    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호가 제대로 들어갔는지 테스트")
    void testCreateWinningLotto() {
        LottoNumber bonusNumber = new LottoNumber("7");
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);

        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThat(winningLotto)
                .isEqualTo(new WinningLotto(Lotto.ofLotto(winningNumbers), 7));
    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호가 중복되는 경우 테스트")
    void testValidateBonusNumber() {
        LottoNumber bonusNumber = new LottoNumber("2");
        assertThatIllegalArgumentException().isThrownBy(() -> new WinningLotto(lotto, bonusNumber));
    }

    @Test
    @DisplayName("당첨 숫자에 따른 등수 테스트")
    void testSeekRank() {
        LottoNumber bonusNumber = new LottoNumber("8");
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        assertThat(winningLotto.findRank(lotto)).isEqualTo(Rank.FIRST);
    }
}
