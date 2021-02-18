package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class WinningLottoTicketTest {

    private static Stream<Arguments> getInvalidLottoNumbers() {
        return Stream.of(Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(1, 3, 3, 4, 5, 6)));
    }

    @DisplayName("당첨 번호와 보너스 볼 번호 문자열을 입력받아 객체를 생성한다")
    @Test
    void make() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusBallNumber = 7;

        assertThatCode(() -> {
            WinningLottoTicket.of(winningNumbers, bonusBallNumber);
        }).doesNotThrowAnyException();
    }

    @DisplayName("보너스 볼 번호는 당첨 번호와 중복되면 안 된다.")
    @Test
    void wrongBonusBallNumber() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusBallNumber = 6;

        assertThatCode(() -> {
            WinningLottoTicket.of(winningNumbers, bonusBallNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 볼 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @DisplayName("당첨 번호는 중복되지 않은 6자리의 숫자이다.")
    @ParameterizedTest
    @MethodSource("getInvalidLottoNumbers")
    void wrong(List<Integer> winningNumbers) {
        assertThatCode(() -> {
            WinningLottoTicket.of(winningNumbers, 45);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 티켓은 중복되지 않은 6자리의 숫자로 구성되어야 합니다.");
    }

    @DisplayName("로또 티켓과 당첨 번호를 비교하여 순위를 반환한다.")
    @Test
    void compareLottoTicketNumbers() {
        LottoTicket lottoTicket = LottoTicket.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningLottoTicket winningLottoTicket = WinningLottoTicket.of(Arrays.asList(1, 2, 3, 4, 5, 7), 6);

        LottoRank result = winningLottoTicket.compareNumbers(lottoTicket);

        assertThat(result).isEqualTo(LottoRank.SECOND);
    }
}
