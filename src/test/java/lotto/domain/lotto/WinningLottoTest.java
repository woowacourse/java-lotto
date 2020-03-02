package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.result.Rank;

class WinningLottoTest {

    @Test
    @DisplayName("WinningLotto는 LottoTicket과 LottoNumber로 생성")
    void createWinningLotto() {
        Set<Integer> lottoTicketNumbers = IntStream.of(1,2,3,4,5,6).boxed().collect(Collectors.toSet());
        int bonusNumber = 7;
        assertThat(new WinningLotto(lottoTicketNumbers, bonusNumber)).isNotNull();

    }

    @Test
    @DisplayName("WinningLotto 생성시 bonus 번호가 lottoTicket에 있으면 예외 발생")
    void lottoTicketHasBonusNumberThrowsExeption() {
        Set<Integer> lottoTicketNumbers = IntStream.of(1,2,3,4,5,6).boxed().collect(Collectors.toSet());
        int bonusNumber = 6;
        assertThatThrownBy(() -> new WinningLotto(lottoTicketNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("createRank")
    @DisplayName("WinningLotto는 lottoTicket을 받아서 rank를 반환")
    void testWinningLottoReturnRank(WinningLotto winningLotto, LottoTicket lottoTicket, Rank rank) {
        assertThat(winningLotto.checkOutRank(lottoTicket)).isEqualTo(rank);
    }

    private static Stream<Arguments> createRank() {
        Set<Integer> firstLottoNumbers = IntStream.of(1,2,3,4,5,6).boxed().collect(Collectors.toSet());
        Set<Integer> secondLottoNumbers = IntStream.of(1,2,3,4,5,7).boxed().collect(Collectors.toSet());
        Set<Integer> thirdLottoNumbers = IntStream.of(2,3,4,5,6,8).boxed().collect(Collectors.toSet());
        Set<Integer> fourthLottoNumbers = IntStream.of(3,4,5,6,7,8).boxed().collect(Collectors.toSet());
        Set<Integer> fifthLottoNumbers = IntStream.of(4,5,6,7,8,9).boxed().collect(Collectors.toSet());
        Set<Integer> missLottoNumbers = IntStream.of(5,6,7,8,9,10).boxed().collect(Collectors.toSet());
        LottoTicket firstLottoTicket = new LottoTicket(firstLottoNumbers);
        LottoTicket secondLottoTicket = new LottoTicket(secondLottoNumbers);
        LottoTicket thirdLottoTicket = new LottoTicket(thirdLottoNumbers);
        LottoTicket fourthLottoTicket = new LottoTicket(fourthLottoNumbers);
        LottoTicket fifthLottoTicket = new LottoTicket(fifthLottoNumbers);
        LottoTicket missLottoTicket = new LottoTicket(missLottoNumbers);
        WinningLotto winningLotto = new WinningLotto(firstLottoNumbers, 7);

        return Stream.of(
                Arguments.of(winningLotto, missLottoTicket, Rank.MISS),
                Arguments.of(winningLotto, fifthLottoTicket, Rank.FIFTH),
                Arguments.of(winningLotto, fourthLottoTicket, Rank.FOURTH),
                Arguments.of(winningLotto, thirdLottoTicket, Rank.THIRD),
                Arguments.of(winningLotto, secondLottoTicket, Rank.SECOND),
                Arguments.of(winningLotto, firstLottoTicket, Rank.FIRST)
        );
    }
}
