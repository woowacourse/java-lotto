import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.LottoGame;
import domain.LottoNumber;
import domain.Money;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    @Test
    @DisplayName("입력받은 금액 로또로 바꿔주는 기능 테스트")
    void buyLottoTest() {
        LottoGame lottoGame = new LottoGame();
        assertThat(lottoGame.buyLotto(new Money(14000)).getSize()).isEqualTo(14);
    }

    @Test
    @DisplayName("수익률 계산하는 기능 테스트")
    void calculateYield() {
        Lotto lotto1 = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
        Lotto lotto2 = new Lotto(Stream.of(4, 5, 6, 7, 8, 9)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
        Lotto lotto3 = new Lotto(Stream.of(11, 12, 13, 14, 15, 16)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));

        LottoGame lottoGame = new LottoGame(Arrays.asList(lotto1, lotto2, lotto3));

        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 9;
        lottoGame.makeResult(winningNumbers, bonusNumber);
        double yield = lottoGame.getYield();

        assertThat(yield).isEqualTo((float) 2000005000 / (float) 3000);
    }
}
