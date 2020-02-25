package domain.lotto;

import domain.RepeatCount;
import domain.lotto.lottoresult.LottoRank;
import domain.lotto.lottoresult.LottoResult;
import domain.lotto.lottoresult.LottoWinner;
import domain.lotto.lottoresult.ResultCount;
import generator.TestNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LottoGameTest {
    @Test
    void 게임생성_확인() {
        LottoGame actualLottoGame = LottoGame.create(new TestNumberGenerator(), new RepeatCount(2));
        Assertions.assertThat(actualLottoGame)
                .isNotNull()
                .isInstanceOf(LottoGame.class);
    }

    @Test
    void 게임생성_확인2() {
        LottoGame actualLottoGame = LottoGame.create(new TestNumberGenerator(), new RepeatCount(0));
        Assertions.assertThat(actualLottoGame)
                .isNotNull()
                .isInstanceOf(LottoGame.class);
    }

    @Test
    void 게임합치기_테스트() {
        LottoGame lottoGame1 = LottoGame.create(new TestNumberGenerator(), new RepeatCount(2));
        LottoGame lottoGame2 = LottoGame.create(new TestNumberGenerator(), new RepeatCount(3));
        LottoGame mergedLottoGame = LottoGame.merge(lottoGame1, lottoGame2);
        Assertions.assertThat(mergedLottoGame.getLottoGame().size()).isEqualTo(5);
    }

    @Test
    void null방어_확인() {
        Assertions.assertThatThrownBy(() -> new LottoGame(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null값이 입력되었습니다.");
    }

    @Test
    void Result생성_확인() {
        LottoGame lottoGame = LottoGame.create(new TestNumberGenerator(), new RepeatCount(2));
        LottoWinner winner = new LottoWinner(LottoNumbersFactory.createLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), LottoNumberFactory.getInstance(7));
        LottoResult result = lottoGame.createGameResult(winner);
        Assertions.assertThat(result.getResult().get(LottoRank.FIRST)).isEqualTo(new ResultCount(2));
    }
}
