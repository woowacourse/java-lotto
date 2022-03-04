package domain;

import domain.Lotto.Lotto;
import domain.Lotto.LottoNumberFactory;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.AutoLottoGenerator;
import domain.player.Money;
import domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private static final int BONUS_BALL_NUMBER = 22;
    private final Money money = new Money(15000);
    private final Player player = new Player(money);


    @Test
    @DisplayName("수동으로 입력한 숫자대로 로또를 구매한 뒤, 잔액을 갱신한다.")
    void purchaseManualLotto() {
        player.purchaseManualLotto(new AutoLottoGenerator(), Arrays.asList(Arrays.asList(1,2,3,4,5,6),Arrays.asList(1,2,3,4,5,7)));
        List<Lotto> actual = player.getLottos();
        int expectedQuantity = 2;
        int expectedAmount = 13000;
        assertThat(actual.size()).isEqualTo(expectedQuantity);
        assertThat(money.getAmount()).isEqualTo(expectedAmount);
    }

    @Test
    @DisplayName("자동 로또를 잔액을 통해 살 수 있는 최대한으로 구매한다.")
    void getNumberOfPurchases() {
        player.purchaseAutoLotto(new AutoLottoGenerator(), LottoNumberFactory.makeBoundary());
        List<Lotto> actual = player.getLottos();
        int expected = 15;
        assertThat(actual.size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Player의 모든 로또에 대해 당첨 번호와 비교한다.")
    void judgeAll() {
        player.purchaseManualLotto(new AutoLottoGenerator(), Arrays.asList(Arrays.asList(1,2,3,4,5,6),Arrays.asList(1,2,3,4,5,7)));
        player.purchaseAutoLotto(new AutoLottoGenerator(), LottoNumberFactory.makeBoundary());
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(i);
        }
        WinningLotto winningLotto = new WinningLotto(lottoNumbers,BONUS_BALL_NUMBER);
        List<Result> actual = player.judgeAll(winningLotto);
        int expected = 15;

        assertThat(actual.size()).isEqualTo(expected);
    }


    @Test
    @DisplayName("Player의 수익률을 계산한다.")
    void calculateIncomeRate() {
        Money money = new Money(2000);
        Player player = new Player(money);

        player.purchaseManualLotto(new AutoLottoGenerator(), Arrays.asList(Arrays.asList(1,2,3,4,5,6),Arrays.asList(1,2,7,8,9,10)));
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(i);
        }
        WinningLotto winningLotto = new WinningLotto(lottoNumbers,BONUS_BALL_NUMBER);
        List<Result> results = player.judgeAll(winningLotto);
        HitResult hitResult = new HitResult();
        double actual = player.calculateIncomeRate(results, hitResult);
        double expected = 1000000;
        assertThat(actual).isEqualTo(expected);
    }
}