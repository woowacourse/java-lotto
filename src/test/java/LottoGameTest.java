import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    private LottoGame lottoGame;

    @BeforeEach
    void init() {
        Lotto lotto1 = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toSet()));
        Lotto lotto2 = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toSet()));
        Lotto lotto3 = new Lotto(Stream.of(11, 12, 13, 14, 15, 16)
                .map(LottoNumber::new)
                .collect(Collectors.toSet()));

        lottoGame = new LottoGame(Arrays.asList(lotto1, lotto2, lotto3));
    }

    @Test
    @DisplayName("입력받은 금액 로또로 바꿔주는 기능 테스트")
    void buyLottoTest() {
        LottoGame lottoGame = LottoGame.startLottoGame(new Money(14000));
        assertThat(lottoGame.getLottos().numberOfLottery()).isEqualTo(14);
    }

    @Test
    @DisplayName("수익률 계산하는 기능 테스트")
    void calculateYield() {
        List<Integer> winningNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 9;
        lottoGame.enterWinningLottoNumbersAndBonusNumber(winningNumbers, bonusNumber);
        float yield = lottoGame.calculateYield();
        assertThat(yield).isEqualTo((float) (Rewards.FIRST_REWARD.getPrize() + Rewards.FIRST_REWARD.getPrize()) / 3);
    }

    @Test
    @DisplayName("로또 당첨 결과 테스트_1등 당첨 개수")
    void produceResultsTest() {
        List<Integer> winningNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 9;
        lottoGame.enterWinningLottoNumbersAndBonusNumber(winningNumbers, bonusNumber);
        Map<Rewards, Integer> results = lottoGame.produceResults();

        assertThat(results.get(Rewards.FIRST_REWARD)).isEqualTo(2);
    }

    @Test
    @DisplayName("당첨 로또 번호, 보너스 번호 입력받는 기능 테스트_out_of_range테스트")
    void enterWinningLottoNumbersAndBonusNumberTest() {
        List<Integer> winningNumbers = new ArrayList<>(Arrays.asList(0, 2, 3, 4, 5, 6));
        int bonusNumber = 9;
        assertThatThrownBy(() -> lottoGame.enterWinningLottoNumbersAndBonusNumber(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 로또 번호, 보너스 번호 입력받는 기능 테스트_중복테스트")
    void enterWinningLottoNumbersAndBonusNumberTest2() {
        List<Integer> winningNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 1;
        assertThatThrownBy(() -> lottoGame.enterWinningLottoNumbersAndBonusNumber(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 로또 번호, 보너스 번호 입력받는 기능 테스트_5개 숫자로 이루어진 당첨번호")
    void enterWinningLottoNumbersAndBonusNumberTest3() {
        List<Integer> winningNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int bonusNumber = 1;
        assertThatThrownBy(() -> lottoGame.enterWinningLottoNumbersAndBonusNumber(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 로또 번호, 보너스 번호 입력받는 기능 테스트_당첨번호 null")
    void enterWinningLottoNumbersAndBonusNumberTest4() {
        int bonusNumber = 1;
        assertThatThrownBy(() -> lottoGame.enterWinningLottoNumbersAndBonusNumber(null, bonusNumber))
                .isInstanceOf(NullPointerException.class);
    }
}
