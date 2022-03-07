import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Lotto;
import domain.LottoNumber;
import domain.WinningNumbers;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {

    @Test
    @DisplayName("로또 당첨 번호에 null 값이 올 시 실패")
    void check_winning_null_fail() {

        assertThatThrownBy(
            () -> new WinningNumbers(null, new LottoNumber(6))
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 당첨번호에 null 값이 올 수 없습니다.");

    }

    @Test
    @DisplayName("로또 보너스 번호에 null 값이 올 시 실패")
    void check_bonus_null_fail() {

        assertThatThrownBy(
            () -> new WinningNumbers(new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList())), null)
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 보너스볼에 null 값이 올 수 없습니다.");

    }

    @Test
    @DisplayName("로또 당첨 번호에 empty 값이 올 시 실패")
    void check_bonus_empty_fail() {

        assertThatThrownBy(
            () -> new WinningNumbers(new Lotto(new ArrayList<>()), new LottoNumber(6))
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 번호는 6개만 입력 가능합니다.");

    }


    @Test
    @DisplayName("로또 당첨 번호 중복 시 실패")
    void check_unique_fail() {

        assertThatThrownBy(
            () -> new WinningNumbers(new Lotto(Stream.of(1, 2, 3, 4, 5, 5)
                .map(LottoNumber::new)
                .collect(Collectors.toList())), new LottoNumber(6))
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 번호는 중복될 수 없습니다.");

    }

    @Test
    @DisplayName("로또 당첨 번호와 보너스 볼 중복 시 실패")
    void check_bonus_unique_fail() {

        assertThatThrownBy(
            () -> new WinningNumbers(new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList())), new LottoNumber(6))
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 당첨번호와 보너스볼은 중복될 수 없습니다.");

    }

    @Test
    @DisplayName("로또 당첨 번호에 1~45가 아닌 값이 들어올 경우 예외 발생")
    void check_winning_number_range_fail() {

        assertThatThrownBy(
            () -> new WinningNumbers(new Lotto(Stream.of(1, 2, 3, 4, 5, 46)
                .map(LottoNumber::new)
                .collect(Collectors.toList())), new LottoNumber(6))
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 번호는 1~45 사이 정수만 가능합니다.");

    }

    @Test
    @DisplayName("로또 보너스 번호에 1~45가 아닌 값이 들어올 경우 예외 발생")
    void check_bonus_number_range_fail() {

        assertThatThrownBy(
            () -> new WinningNumbers(new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList())), new LottoNumber(46))
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 번호는 1~45 사이 정수만 가능합니다.");

    }

}
