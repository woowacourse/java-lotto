import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.LottoGame;
import domain.LottoNumber;
import domain.Money;
import domain.Rewards;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    @Test
    @DisplayName("입력받은 금액 로또로 바꿔주는 기능 테스트")
    void buyLottoTest() {
        LottoGame lottoGame = new LottoGame();
        assertThat(lottoGame.buyLotto(new Money(14000)).numberOfLottery()).isEqualTo(14);
    }

    @Test
    @DisplayName("수익률 계산하는 기능 테스트")
    void calculateYield() {
        //TODO
    }

    @Test
    @DisplayName("로또 당첨 결과 테스트_1등 당첨 개수")
    void produceResultsTest() {
        Lotto lotto1 = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
        Lotto lotto2 = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
        Lotto lotto3 = new Lotto(Stream.of(11, 12, 13, 14, 15, 16)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));

        LottoGame lottoGame = new LottoGame(Arrays.asList(lotto1, lotto2, lotto3));
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 9;
        lottoGame.enterWinningLottoNumbersAndBonusNumber(winningNumbers, bonusNumber);
        Map<Rewards, Integer> results = lottoGame.produceResults();

        assertThat(results.get(Rewards.FIRST_REWARD)).isEqualTo(2);
    }

}
