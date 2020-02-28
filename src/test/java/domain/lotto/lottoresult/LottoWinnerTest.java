package domain.lotto.lottoresult;

import domain.lotto.LottoNumbers;
import domain.lotto.LottoNumbersFactory;
import generator.TestNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LottoWinnerTest {
    @Test
    @DisplayName("생성 테스트")
    void test0() {
        Assertions.assertThatCode(() -> LottoWinner.create(Arrays.asList(1, 2, 3, 4, 5, 6), 7))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("보너스볼 중복 예외 확인")
    void test2() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Assertions.assertThatThrownBy(() -> LottoWinner.create(lottoNumbers, 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 숫자가 중복되었습니다.");
    }

    @Test
    @DisplayName("등수 1등 반환 확인")
    void test3() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoWinner winner = LottoWinner.create(lottoNumbers, 7);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.FIRST);
    }

    @Test
    @DisplayName("등수 2등 반환 확인")
    void test4() {
        List<Integer> value = Arrays.asList(1, 2, 3, 4, 6, 7);
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator(value));
        LottoWinner winner = LottoWinner.create(lottoNumbers, 7);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.SECOND);
    }

    @Test
    @DisplayName("등수 3등 반환 확인")
    void test5() {
        List<Integer> value = Arrays.asList(1, 2, 3, 4, 6, 8);
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator(value));
        LottoWinner winner = LottoWinner.create(lottoNumbers, 7);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.THIRD);
    }

    @Test
    @DisplayName("등수 4등 반환 확인")
    void test6() {
        List<Integer> value = Arrays.asList(1, 2, 3, 4, 7, 8);
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator(value));
        LottoWinner winner = LottoWinner.create(lottoNumbers, 7);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.FOURTH);
    }

    @Test
    @DisplayName("등수 5등 반환 확인")
    void test7() {
        List<Integer> value = Arrays.asList(1, 2, 3, 8, 9, 10);
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator(value));
        LottoWinner winner = LottoWinner.create(lottoNumbers, 7);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.FIFTH);
    }

    @Test
    @DisplayName("등수 없음 반환 확인")
    void test8() {
        List<Integer> value = Arrays.asList(11, 21, 13, 14, 16, 17);
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator(value));
        LottoWinner winner = LottoWinner.create(lottoNumbers, 7);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.NOTHING);
    }
}
