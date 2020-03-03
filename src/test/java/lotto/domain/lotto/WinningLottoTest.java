package lotto.domain.lotto;

import lotto.domain.result.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    @DisplayName("winningLotto는 LottoTicket과 LottoNumber로 생성")
    void createWinningLotto() {
        Set<LottoNumber> numbers = IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet());
        LottoTicket lottoTicket = LottoTicket.from(numbers);
        LottoNumber bonusNumber = LottoNumber.from(7);
        WinningLotto winningLotto = WinningLotto.from(lottoTicket, bonusNumber);

        assertThat(winningLotto).isNotNull()
                .isInstanceOf(WinningLotto.class);
    }

    @Test
    @DisplayName("winningLotto 생성시 bonus 번호가 lottoTicket에 있으면 예외 발생")
    void lottoTicketHasBonusNumberThrowsExeption() {
        Set<LottoNumber> numbers = IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet());
        LottoTicket lottoTicket = LottoTicket.from(numbers);
        LottoNumber bonusNumber = LottoNumber.from(6);

        assertThatThrownBy(() -> WinningLotto.from(lottoTicket, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("createRank")
    @DisplayName("winningLotto는 lottoTicket을 받아서 rank를 반환")
    void testWinningLottoReturnRank(WinningLotto winningLotto, LottoTicket lottoTicket, Rank rank) {
        assertThat(winningLotto.getRank(lottoTicket)).isEqualTo(rank);
    }

    private static Stream<Arguments> createRank() {
        Set<LottoNumber> numbers = IntStream.rangeClosed(2, 7)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet());
        LottoTicket lottoTicket = LottoTicket.from(numbers);
        LottoNumber bonusNumber = LottoNumber.from(1);
        WinningLotto winningLotto = WinningLotto.from(lottoTicket, bonusNumber);

        Set<LottoNumber> secondNumbers = IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet());
        LottoTicket secondLottoTicket = LottoTicket.from(secondNumbers);

        Set<LottoNumber> thirdNumbers = IntStream.rangeClosed(3, 8)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet());
        LottoTicket thirdLottoTicket = LottoTicket.from(thirdNumbers);

        Set<LottoNumber> fourthNumbers = IntStream.rangeClosed(4, 9)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet());
        LottoTicket fourthLottoTicket = LottoTicket.from(fourthNumbers);

        Set<LottoNumber> fifthNumbers = IntStream.rangeClosed(5, 10)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet());
        LottoTicket fifthLottoTicket = LottoTicket.from(fifthNumbers);

        Set<LottoNumber> missNumbers = IntStream.rangeClosed(6, 11)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet());
        LottoTicket missLottoTicket = LottoTicket.from(missNumbers);

        return Stream.of(
                Arguments.of(winningLotto, missLottoTicket, Rank.MISS),
                Arguments.of(winningLotto, fifthLottoTicket, Rank.FIFTH),
                Arguments.of(winningLotto, fourthLottoTicket, Rank.FOURTH),
                Arguments.of(winningLotto, thirdLottoTicket, Rank.THIRD),
                Arguments.of(winningLotto, secondLottoTicket, Rank.SECOND),
                Arguments.of(winningLotto, lottoTicket, Rank.FIRST)
        );
    }
}
