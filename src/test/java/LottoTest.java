import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Lotto;
import domain.WinningNumbers;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("로또 생성 성공")
    void lotto_generate_success() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.getLottoNumbers()).containsExactly(1, 2, 3, 4, 5, 6);

    }

    @Test
    @DisplayName("로또 번호에 null 값이 들어오면 예외 발생 ")
    void check_null_fail() {
        assertThatThrownBy(
            () -> new Lotto(null)
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 번호로 null 값이 올 수 없습니다.");
    }

//
//    @Test
//    @DisplayName("로또 숫자 1~45 사이 아닐 시 예외 발생")
//    void check_range_fail() {
//
//        assertThatThrownBy(
//            () -> new Lotto(List.of(1, 2, 3, 4, 5, 46))
//        ).isInstanceOf(IllegalArgumentException.class)
//            .hasMessageContaining("[ERROR] 로또 번호는 1~45 사이 정수만 가능합니다.");
//
//    }

    @Test
    @DisplayName("로또 숫자가 6개가 아닐 시 예외 발생")
    void check_size_fail() {
        assertThatThrownBy(
            () -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7))
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 번호는 6개만 입력 가능합니다.");
    }

    @Test
    @DisplayName("로또 번호에 중복이 있을 시 예외 발생")
    void check_unique_fail() {
        assertThatThrownBy(
            () -> new Lotto(List.of(1, 2, 3, 4, 5, 5))
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 번호는 중복될 수 없습니다.");
    }

//    @Test
//    @DisplayName("로또 당첨 번호 3개 미만 시 winningCount 0으로 변환")
//    void convert_winningCount() {
//
//        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
//        WinningNumbers winningNumbers = new WinningNumbers(List.of(5, 6, 7, 8, 9, 10), 11);
//
//        List<Integer> counts = lotto.getWinningAndBonusCount(winningNumbers);
//
//        assertThat(counts.get(0)).isEqualTo(0);
//
//    }
//
//    @Test
//    @DisplayName("로또 당첨 번호 5개 아닐 시 bonusCount 0으로 변환")
//    void convert_bonusCount() {
//
//        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
//        WinningNumbers winningNumbers = new WinningNumbers(List.of(5, 6, 7, 8, 9, 10), 1);
//
//        List<Integer> counts = lotto.getWinningAndBonusCount(winningNumbers);
//
//        assertThat(counts.get(1)).isEqualTo(0);
//
//    }

}
