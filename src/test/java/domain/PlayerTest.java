package domain;

import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.AutoLottoGenerator;
import domain.LottoGenerator.LottoGenerator;
import domain.LottoGenerator.WinningLottoGenerator;
import domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private static final int BONUS_BALL_NUMBER = 22;
    private final int money = 15000;
    private final Player player = new Player(money);


    @BeforeEach
    void setUp() {
        LottoGenerator lottoGenerator = new AutoLottoGenerator();
        while (player.canBuyLotto()) {
            player.purchaseLotto(lottoGenerator.generateLotto());
        }
    }

    @Test
    @DisplayName("로또를 최대한으로 구매한다.")
    void getNumberOfPurchases() {
        List<Lotto> actual = player.getLottos();
        int expected = 15;
        assertThat(actual.size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Player의 모든 로또에 대해 당첨 번호와 비교한다.")
    void judgeAll() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(i);
        }
        LottoGenerator lottoGenerator = new WinningLottoGenerator();
        WinningLotto winningLotto = new WinningLotto(lottoGenerator.generateWinningLotto(lottoNumbers), new LottoNumber(BONUS_BALL_NUMBER));
        List<Result> actual = player.judgeAll(winningLotto);
        int expected = 15;

        assertThat(actual.size()).isEqualTo(expected);
    }
}