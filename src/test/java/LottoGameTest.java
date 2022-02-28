import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.LottoGame;
import domain.LottoGenerator;
import domain.Money;
import domain.RandomLottoGenerator;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    @Test
    @DisplayName("입력받은 금액 로또로 바꿔주는 기능 테스트")
    void buyLottoTest() {
        LottoGame lottoGame = new LottoGame(new Money(14000), new RandomLottoGenerator());
        assertThat(lottoGame.getLottos().getSize()).isEqualTo(14);
    }

    @Test
    @DisplayName("수익률 계산하는 기능 테스트")
    void calculateYield() {
        Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(Arrays.asList(4, 5, 6, 7, 8, 9));
        Lotto lotto3 = new Lotto(Arrays.asList(11, 12, 13, 14, 15, 16));

        Money money = new Money(3000);

        LottoGame lottoGame = new LottoGame(money, (mockAmount) -> Arrays.asList(lotto1, lotto2, lotto3));

        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 9;
        lottoGame.makeResult(winningNumbers, bonusNumber);
        double yield = lottoGame.getYield();

        assertThat(yield).isEqualTo((float) 2000005000 / (float) 3000);
    }
}
