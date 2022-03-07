package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    private static final LottoFactory lottoFactory = new LottoFactory();

    @Test
    @DisplayName("우승로또 객체 생성시 null 값이 들어가는 경ㅇ")
    void createWinningLottoCheckNull() {
        assertThatThrownBy(() -> new WinningLotto(null, null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("당청번호와 구매의 로또 번호의 일치 여부를 계산하는 경우")
    void compareWithPurchaseLotto() {
        Lotto purchaseLotto = lottoFactory.generateLotto(List.of(1, 2, 3, 4, 44, 6));
        WinningLotto winningLotto = new WinningLotto(
            lottoFactory.generateLotto(List.of(1, 2, 3, 10, 11, 12)), LottoNumber.valueOf(4));

        assertThat(winningLotto.calculateMatchResult(purchaseLotto)).isEqualTo(LottoReward.FIFTH);
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호가 중복된 경우")
    void checkDuplicateNumber() {
        Lotto winningLotto = lottoFactory.generateLotto(List.of(12, 23, 6, 44, 17, 16));
        LottoNumber bonusNumber = LottoNumber.valueOf(12);

        assertThatThrownBy(() -> new WinningLotto(winningLotto, bonusNumber))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호가 유효한 경우")
    void checkValidDuplicateNumber() {
        Lotto winningLotto = lottoFactory.generateLotto(List.of(12, 23, 6, 44, 17, 16));
        LottoNumber bonusNumber = LottoNumber.valueOf(13);

        assertThat(new WinningLotto(winningLotto, bonusNumber)).isNotNull();
    }
}
