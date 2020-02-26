package domain.lotto;

import domain.RepeatCount;
import domain.lotto.lottoresult.LottoRank;
import domain.lotto.lottoresult.LottoResult;
import domain.lotto.lottoresult.LottoWinner;
import domain.lotto.lottoresult.ResultCount;
import generator.TestNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

public class LottoGameTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    @DisplayName("게임생성 테스트")
    void test1(int value) {
        Assertions.assertThatCode(() -> LottoGame.create(new TestNumberGenerator(), new RepeatCount(value))).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource(value = {"1,2,3", "0,0,0", "0,1,1", "1,0,1"})
    @DisplayName("게임합치기테스트")
    void test3(int input1, int input2, int sum) {
        LottoGame lottoGame1 = LottoGame.create(new TestNumberGenerator(), new RepeatCount(input1));
        LottoGame lottoGame2 = LottoGame.create(new TestNumberGenerator(), new RepeatCount(input2));
        LottoGame mergedLottoGame = LottoGame.merge(lottoGame1, lottoGame2);
        Assertions.assertThat(mergedLottoGame.getLottoGame().size()).isEqualTo(sum);
    }

    @Test
    @DisplayName("null방어 확인")
    void test4() {
        Assertions.assertThatThrownBy(() -> new LottoGame(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null값이 입력되었습니다.");
    }

    @Test
    @DisplayName("Result생성 확인")
    void test5() {
        LottoGame lottoGame = LottoGame.create(new TestNumberGenerator(), new RepeatCount(2));
        LottoWinner winner = new LottoWinner(LottoNumbersFactory.createLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), LottoNumberFactory.getInstance(7));
        LottoResult result = lottoGame.createGameResult(winner);
        Assertions.assertThat(result.getResult().get(LottoRank.FIRST)).isEqualTo(new ResultCount(2));
    }
}
