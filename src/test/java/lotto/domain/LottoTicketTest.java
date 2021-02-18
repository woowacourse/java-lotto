package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class LottoTicketTest {

    private static Stream<Arguments> getInvalidLottoNumbers() {
        return Stream.of(Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(1, 3, 3, 4, 5, 6)));
    }

    private static Stream<Arguments> getLottoNumbers() {
        return Stream.of(Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 6),
                Arguments.of(Arrays.asList(4, 5, 6, 7, 8, 9), 3));
    }

    @DisplayName("로또 티켓 발급에 필요한 로또 번호는 중복되지 않은 6자리 숫자이다")
    @ParameterizedTest
    @MethodSource("getInvalidLottoNumbers")
    void wrongLottoNumberCounts(List<Integer> numbers) {
        assertThatCode(() -> {
            LottoTicket.from(numbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 티켓은 중복되지 않은 6자리의 숫자로 구성되어야 합니다.");
    }

    @DisplayName("로또 티켓이 정상적으로 발급될 경우")
    @Test
    void makeLottoTicket() {
        LottoTicket lottoTicket = LottoTicket.from(Arrays.asList(1, 2, 3, 4, 5, 6));

        List<LottoNumber> lottoNumbers = lottoTicket.getLottoNumbers();

        assertThat(lottoNumbers).contains(LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3),
                LottoNumber.from(4),LottoNumber.from(5), LottoNumber.from(6));
    }

    @DisplayName("로또 티켓과 당첨 번호를 비교하여 일치하는 개수를 반환한다.")
    @ParameterizedTest
    @MethodSource("getLottoNumbers")
    void compareLottoTickets(List<Integer> numbers, int matchCounts) {
        LottoTicket lottoTicket = LottoTicket.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoTicket winningTicket = LottoTicket.from(numbers);

        int targetMatchCounts = lottoTicket.getMatchCounts(winningTicket);

        assertThat(targetMatchCounts).isEqualTo(matchCounts);
    }

    @DisplayName("로또 티켓이 특정 볼 번호를 가지고 있는지 확인")
    @ParameterizedTest
    @CsvSource({"1,true", "2,false"})
    void contains(int ballNumber, boolean target) {
        LottoTicket lottoTicket = LottoTicket.from(Arrays.asList(1, 9, 3, 4, 5, 6));

        boolean isContainingBonusNumber = lottoTicket.contains(LottoNumber.from(ballNumber));

        assertThat(isContainingBonusNumber).isEqualTo(target);
    }
}
