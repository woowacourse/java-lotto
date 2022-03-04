import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.LottoGame;
import domain.Money;
import domain.Rewards;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    private List<List<Integer>> makePickLottoNumbersBucket() {
        return List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 6),
                List.of(11, 12, 13, 14, 15, 16)
        );
    }

    @Test
    @DisplayName("입력받은 금액 로또로 바꿔주는 기능 테스트")
    void buyLottoTest() {
        LottoGame lottoGame = LottoGame.startLottoGame(new Money(14000), Collections.emptyList());
        assertThat(lottoGame.getLottos().numberOfLottery()).isEqualTo(14);
    }

    @Test
    @DisplayName("수익률 계산하는 기능 테스트")
    void calculateYield() {
        LottoGame lottoGame = LottoGame.startLottoGame(new Money(3000), makePickLottoNumbersBucket());
        List<Integer> winningNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 9;
        lottoGame.enterWinningLottoNumbersAndBonusNumber(winningNumbers, bonusNumber);
        float yield = lottoGame.calculateYield();
        assertThat(yield).isEqualTo((float) (Rewards.FIRST_REWARD.getPrize() + Rewards.FIRST_REWARD.getPrize()) / 3000);
    }

    @Test
    @DisplayName("로또 당첨 결과 테스트_1등 당첨 개수")
    void produceResultsTest() {
        LottoGame lottoGame = LottoGame.startLottoGame(new Money(3000), makePickLottoNumbersBucket());
        List<Integer> winningNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 9;
        lottoGame.enterWinningLottoNumbersAndBonusNumber(winningNumbers, bonusNumber);
        Map<Rewards, Integer> results = lottoGame.produceResults();

        assertThat(results.get(Rewards.FIRST_REWARD)).isEqualTo(2);
    }

    @Test
    @DisplayName("당첨 로또 번호, 보너스 번호 입력받는 기능 테스트_out_of_range테스트")
    void enterWinningLottoNumbersAndBonusNumberTest() {
        LottoGame lottoGame = LottoGame.startLottoGame(new Money(3000), makePickLottoNumbersBucket());
        List<Integer> winningNumbers = new ArrayList<>(Arrays.asList(0, 2, 3, 4, 5, 6));
        int bonusNumber = 9;
        assertThatThrownBy(() -> lottoGame.enterWinningLottoNumbersAndBonusNumber(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 로또 번호, 보너스 번호 입력받는 기능 테스트_중복테스트")
    void enterWinningLottoNumbersAndBonusNumberTest2() {
        LottoGame lottoGame = LottoGame.startLottoGame(new Money(3000), makePickLottoNumbersBucket());
        List<Integer> winningNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 1;
        assertThatThrownBy(() -> lottoGame.enterWinningLottoNumbersAndBonusNumber(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 로또 번호, 보너스 번호 입력받는 기능 테스트_5개 숫자로 이루어진 당첨번호")
    void enterWinningLottoNumbersAndBonusNumberTest3() {
        LottoGame lottoGame = LottoGame.startLottoGame(new Money(3000), makePickLottoNumbersBucket());
        List<Integer> winningNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int bonusNumber = 1;
        assertThatThrownBy(() -> lottoGame.enterWinningLottoNumbersAndBonusNumber(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 로또 번호, 보너스 번호 입력받는 기능 테스트_당첨번호 null")
    void enterWinningLottoNumbersAndBonusNumberTest4() {
        LottoGame lottoGame = LottoGame.startLottoGame(new Money(3000), makePickLottoNumbersBucket());
        int bonusNumber = 1;
        assertThatThrownBy(() -> lottoGame.enterWinningLottoNumbersAndBonusNumber(null, bonusNumber))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("로또게임 시작_수동로또개수 한도 초과")
    void buyLottoTest2() {
        assertThatThrownBy(() -> LottoGame.startLottoGame(
                new Money(2000), makePickLottoNumbersBucket()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또게임 시작_6자리가 아닌 수동로또번호 존재")
    void buyLottoTest4() {
        List<List<Integer>> pickLottoNumbersBucket = List.of(
                List.of(1, 2, 3, 4, 5, 6, 7),
                List.of(1, 2, 3, 4, 5, 6)
        );

        assertThatThrownBy(() -> LottoGame.startLottoGame(
                new Money(2000), pickLottoNumbersBucket))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또게임 시작_1-45사이가 아닌 수동로또번호 존재")
    void buyLottoTest5() {
        List<List<Integer>> pickLottoNumbersBucket = List.of(
                List.of(1, 2, 3, 4, 5, 46),
                List.of(1, 2, 3, 4, 5, 6)
        );

        assertThatThrownBy(() -> LottoGame.startLottoGame(
                new Money(2000), pickLottoNumbersBucket))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
