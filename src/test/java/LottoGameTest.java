import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.LottoGame;
import domain.RandomLottoGenerator;
import domain.WinningNumbers;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {


    @Test
    @DisplayName("입력받은 금액 로또로 바꿔주는 기능 테스트")
    void buyLottoTest() {
        LottoGame lottoGame = new LottoGame( new RandomLottoGenerator(14));
        assertThat(lottoGame.getLottos().getSize()).isEqualTo(14);
    }


    @Test
    @DisplayName("수익률 계산하는 기능 테스트")
    void calculateYield() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(4, 5, 6, 7, 8, 9));
        Lotto lotto3 = new Lotto(List.of(11, 12, 13, 14, 15, 16));

        LottoGame lottoGame = new LottoGame(
            () -> List.of(lotto1, lotto2, lotto3));

        WinningNumbers winningNumbers = new WinningNumbers(
            new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)), 9);

        lottoGame.makeResult(winningNumbers);

        double yield = lottoGame.getYield();

        assertThat(yield).isEqualTo((double) 2000005000 / (double) 3000);
    }

}
