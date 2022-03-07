import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.LottoNumber;
import domain.WinningNumbers;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {
//
//    @Test
//    @DisplayName("로또 당첨 번호는 1~45 사이가 아닐 시 실패")
//    void check_range_fail() {
//
//        assertThatThrownBy(
//            () -> new WinningNumbers(Stream.of(1, 2, 3, 4, 5, 46).map(LottoNumber::new).collect(
//                Collectors.toList()), new LottoNumber(6))
//        ).isInstanceOf(IllegalArgumentException.class)
//            .hasMessageContaining("[ERROR] 1이상 45이하 값을 입력해주세요.");
//
//    }
//
//    @Test
//    @DisplayName("로또 보너스 번호는 1~45 사이가 아닐 시 실패")
//    void check_bonus_range_fail() {
//
//        assertThatThrownBy(
//            () -> new WinningNumbers(Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).collect(
//                Collectors.toList()), new LottoNumber(46))
//        ).isInstanceOf(IllegalArgumentException.class)
//            .hasMessageContaining("[ERROR] 1이상 45이하 값을 입력해주세요.");
//
//    }
//
//    @Test
//    @DisplayName("로또 당첨 번호 중복 시 실패")
//    void check_unique_fail() {
//
//        assertThatThrownBy(
//            () -> new WinningNumbers(Stream.of(1, 2, 3, 4, 5, 5).map(LottoNumber::new).collect(
//                Collectors.toList()), new LottoNumber(6))
//        ).isInstanceOf(IllegalArgumentException.class)
//            .hasMessageContaining("[ERROR] 당첨번호와 보너스볼은 중복될 수 없습니다.");
//
//    }

    @Test
    @DisplayName("로또 당첨 번호와 보너스 볼 중복 시 실패")
    void check_bonus_unique_fail() {

        assertThatThrownBy(
            () -> new WinningNumbers(Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).collect(
                Collectors.toList()), new LottoNumber(6))
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 당첨번호와 보너스볼은 중복될 수 없습니다.");

    }

}
