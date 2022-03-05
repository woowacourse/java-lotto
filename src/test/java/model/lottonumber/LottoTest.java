package model.lottonumber;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import model.generator.Generator;
import model.rank.Rank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("생성된 난수를 바탕으로 로또 번호를 생성한다.")
    void generateLottoNumbers_GeneratorTest() {
        final Generator generator = () -> Arrays.asList(1, 2, 13, 4, 25, 39);
        final Lotto lotto = new Lotto(generator);

        assertThat(lotto.getNumbers()).isEqualTo(Arrays.asList(1, 2, 13, 4, 25, 39));
    }

    @Test
    @DisplayName("매개변수 List의 숫자를 바탕으로 로또 번호를 생성한다.")
    void generateLottoNumbers_ListTest() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final Lotto lotto = new Lotto(numbers);

        assertThat(lotto.getNumbers()).isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("입력된 숫자의 개수가 6보다 작으면 오류를 발생한다.")
    void checkValidNumbers_UnderSize() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력한 수동 로또 번호가 6개가 아닙니다.");
    }

    @Test
    @DisplayName("입력된 숫자의 개수가 6보다 크면 오류를 발생한다.")
    void checkValidNumbers_OverSize() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 15);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력한 수동 로또 번호가 6개가 아닙니다.");
    }

    @Test
    @DisplayName("입력된 숫자에 중복이 있으면 오류를 발생한다..")
    void checkValidNumbers_Duplicate() {
        final List<Integer> numbers = Arrays.asList(1, 2, 15, 4, 5, 15);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력한 수동 로또티켓 중 번호가 중복되는 티켓이 있습니다.");
    }

    @Test
    @DisplayName("당첨 번호와 3개 미만으로 일치시 NONE을 반환한다.")
    void findWinningRank_None() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int number = 7;
        final WinningNumbers winningNumbers = new WinningNumbers(numbers, number);

        final Generator generator = () -> Arrays.asList(1, 32, 13, 4, 25, 39);
        final Lotto lotto = new Lotto(generator);

        assertThat(lotto.findRank(winningNumbers)).isEqualTo(Rank.NONE);
    }

    @Test
    @DisplayName("당첨 번호와 3개 일치시 5등을 반환한다.")
    void findWinningRank_Fifth() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int number = 7;
        final WinningNumbers winningNumbers = new WinningNumbers(numbers, number);

        final Generator generator = () -> Arrays.asList(1, 2, 13, 4, 25, 39);
        final Lotto lotto = new Lotto(generator);

        assertThat(lotto.findRank(winningNumbers)).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("당첨 번호와 4개 일치시 4등을 반환한다.")
    void findWinningRank_Fourth() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int number = 7;
        final WinningNumbers winningNumbers = new WinningNumbers(numbers, number);

        final Generator generator = () -> Arrays.asList(1, 2, 3, 4, 25, 39);
        final Lotto lotto = new Lotto(generator);

        assertThat(lotto.findRank(winningNumbers)).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("당첨 번호와 5개 일치, 보너스 볼 불일치한 경우 3등을 반환한다.")
    void findWinningRank_Third() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int number = 7;
        final WinningNumbers winningNumbers = new WinningNumbers(numbers, number);

        final Generator generator = () -> Arrays.asList(1, 2, 3, 4, 39, 6);
        final Lotto lotto = new Lotto(generator);

        assertThat(lotto.findRank(winningNumbers)).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("당첨 번호와 5개 일치, 보너스 볼 일치한 경우 2등을 반환한다.")
    void findWinningRank_Second() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int number = 7;
        final WinningNumbers winningNumbers = new WinningNumbers(numbers, number);

        final Generator generator = () -> Arrays.asList(1, 2, 3, 4, 7, 6);
        final Lotto lotto = new Lotto(generator);

        assertThat(lotto.findRank(winningNumbers)).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("당첨 번호와 전부 일치할 시 1등을 반환한다.")
    void findWinningRank_First() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int number = 7;
        final WinningNumbers winningNumbers = new WinningNumbers(numbers, number);

        final Generator generator = () -> Arrays.asList(1, 2, 4, 3, 6, 5);
        final Lotto lotto = new Lotto(generator);

        assertThat(lotto.findRank(winningNumbers)).isEqualTo(Rank.FIRST);
    }
}