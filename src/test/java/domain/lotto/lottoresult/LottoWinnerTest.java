package domain.lotto.lottoresult;

import domain.lotto.LottoNumber;
import domain.lotto.LottoNumberFactory;
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
    @DisplayName("null 방어 로직")
    void test1() {
        Assertions.assertThatThrownBy(() -> new LottoWinner(null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null값이 입력되었습니다.");
        Assertions.assertThatThrownBy(() -> new LottoWinner(null, LottoNumberFactory.getInstance(1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null값이 입력되었습니다.");
        Assertions.assertThatThrownBy(() -> new LottoWinner(LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator()), null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null값이 입력되었습니다.");
    }

    @Test
    @DisplayName("보너스볼 중복 예외 확인")
    void test2() {
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoNumber bonus = LottoNumberFactory.getInstance(6);
        Assertions.assertThatThrownBy(() -> new LottoWinner(lottoNumbers, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 숫자가 중복되었습니다.");
    }

    @Test
    @DisplayName("등수 1등 반환 확인")
    void test3() {
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoNumber bonus = LottoNumberFactory.getInstance(7);
        LottoWinner winner = new LottoWinner(lottoNumbers, bonus);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.FIRST);
    }

    @Test
    @DisplayName("등수 2등 반환 확인")
    void test4() {
        List<Integer> value = Arrays.asList(1, 2, 3, 4, 6, 7);
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator(value));
        LottoNumber bonus = LottoNumberFactory.getInstance(7);
        LottoWinner winner = new LottoWinner(lottoNumbers, bonus);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.SECOND);
    }

    @Test
    @DisplayName("등수 3등 반환 확인")
    void test5() {
        List<Integer> value = Arrays.asList(1, 2, 3, 4, 6, 8);
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator(value));
        LottoNumber bonus = LottoNumberFactory.getInstance(7);
        LottoWinner winner = new LottoWinner(lottoNumbers, bonus);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.THIRD);
    }

    @Test
    @DisplayName("등수 4등 반환 확인")
    void test6() {
        List<Integer> value = Arrays.asList(1, 2, 3, 4, 7, 8);
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator(value));
        LottoNumber bonus = LottoNumberFactory.getInstance(7);
        LottoWinner winner = new LottoWinner(lottoNumbers, bonus);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.FOURTH);
    }

    @Test
    @DisplayName("등수 5등 반환 확인")
    void test7() {
        List<Integer> value = Arrays.asList(1, 2, 3, 8, 9, 10);
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator(value));
        LottoNumber bonus = LottoNumberFactory.getInstance(7);
        LottoWinner winner = new LottoWinner(lottoNumbers, bonus);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.FIFTH);
    }

    @Test
    @DisplayName("등수 없음 반환 확인")
    void test8() {
        List<Integer> value = Arrays.asList(11, 21, 13, 14, 16, 17);
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator(value));
        LottoNumber bonus = LottoNumberFactory.getInstance(7);
        LottoWinner winner = new LottoWinner(lottoNumbers, bonus);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.NOTHING);
    }
}
