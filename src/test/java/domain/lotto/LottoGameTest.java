package domain.lotto;

import domain.lotto.lottoresult.LottoRank;
import domain.lotto.lottoresult.LottoResult;
import domain.lotto.lottoresult.LottoWinner;
import domain.lotto.lottoresult.ResultCount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LottoGameTest {
    @Test
    void 게임생성_확인() {
        LottoGame actualLottoGame = LottoGame.create(new TestNumberGenerator(), 2);
        Assertions.assertThat(actualLottoGame).isInstanceOf(LottoGame.class);
    }

    @Test
    void Result생성_확인() {
        LottoGame lottoGame = LottoGame.create(new TestNumberGenerator(), 2);
        LottoWinner winner = new LottoWinner(LottoNumbersFactory.createLottoNumbers(Arrays.asList(1,2,3,4,5,6)), LottoNumber.of(7));
        LottoResult result = lottoGame.createGameResult(winner);
        Assertions.assertThat(result.getResult().get(LottoRank.FIRST)).isEqualTo(new ResultCount(2));
    }
}
