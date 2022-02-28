import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.LottoGame;
import domain.LottoGenerator;
import domain.Lottos;
import domain.Money;
import domain.RandomLottoGenerator;
import domain.Rewards;
import domain.WinningNumbers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
    Lotto lotto2 = new Lotto(Arrays.asList(4, 5, 6, 7, 8, 9));
    Lotto lotto3 = new Lotto(Arrays.asList(11, 12, 13, 14, 15, 16));

    Money money = new Money(3000);

    LottoGame lottoGame = new LottoGame(money,
        (mockAmount) -> Arrays.asList(lotto1, lotto2, lotto3));

    WinningNumbers winningNumbers = new WinningNumbers(
        new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)), 9);

    @Test
    @DisplayName("입력받은 금액 로또로 바꿔주는 기능 테스트")
    void buyLottoTest() {
        LottoGame lottoGame = new LottoGame(new Money(14000), new RandomLottoGenerator());
        assertThat(lottoGame.getLottos().getSize()).isEqualTo(14);
    }


    @Test
    @DisplayName("로또 당첨 1등 성공")
    void compareAllLottoTest1() {

        lottoGame.makeResult(winningNumbers);

        assertThat(Rewards.getCount(Rewards.FIRST_REWARD)).isEqualTo(1);
    }


    @Test
    @DisplayName("수익률 계산하는 기능 테스트")
    void calculateYield() {

        double yield = lottoGame.getYield();

        assertThat(yield).isEqualTo((double) 2000005000 / (double) 3000);
    }

}
