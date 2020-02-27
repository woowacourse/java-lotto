package lotto.domain.lotto;

import lotto.domain.result.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    @DisplayName("winningLotto는 LottoTicket과 LottoNumber로 생성")
    void createWinningLotto() {
        Set<Integer> numbers = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6}).boxed().collect(Collectors.toSet());
        LottoTicket lottoTicket = LottoTicketFactory.publishLottoTicketFrom(numbers);
        LottoNumber bonusNumber = LottoTicketFactory.publishLottoNumberFrom(7);
        WinningLotto winningLotto = new WinningLotto(lottoTicket, bonusNumber);

        assertThat(winningLotto);
    }

    @Test
    @DisplayName("winningLotto 생성시 bonus 번호가 lottoTicket에 있으면 예외 발생")
    void lottoTicketHasBonusNumberThrowsExeption() {
        Set<Integer> numbers = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6}).boxed().collect(Collectors.toSet());
        LottoTicket lottoTicket = LottoTicketFactory.publishLottoTicketFrom(numbers);
        LottoNumber bonusNumber = LottoTicketFactory.publishLottoNumberFrom(6);

        assertThatThrownBy(() -> new WinningLotto(lottoTicket, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("createRank")
    @DisplayName("winningLotto는 lottoTicket을 받아서 rank를 반환")
    void testWinningLottoReturnRank(WinningLotto winningLotto, LottoTicket lottoTicket, Rank rank) {
        assertThat(winningLotto.getRank(lottoTicket)).isEqualTo(rank);
    }

    private static Stream<Arguments> createRank() {
        Set<Integer> numbers = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6}).boxed().collect(Collectors.toSet());
        LottoTicket lottoTicket = LottoTicketFactory.publishLottoTicketFrom(numbers);
        LottoNumber bonusNumber = LottoTicketFactory.publishLottoNumberFrom(7);
        WinningLotto winningLotto = new WinningLotto(lottoTicket, bonusNumber);

        Set<Integer> secondNumbers = Arrays.stream(new int[]{1, 2, 3, 4, 5, 7}).boxed().collect(Collectors.toSet());
        LottoTicket secondLottoTicket = LottoTicketFactory.publishLottoTicketFrom(secondNumbers);

        Set<Integer> thirdNumbers = Arrays.stream(new int[]{2, 3, 4, 5, 6, 8}).boxed().collect(Collectors.toSet());
        LottoTicket thirdLottoTicket = LottoTicketFactory.publishLottoTicketFrom(thirdNumbers);

        Set<Integer> fourthNumbers = Arrays.stream(new int[]{3, 4, 5, 6, 7, 8}).boxed().collect(Collectors.toSet());
        LottoTicket fourthLottoTicket = LottoTicketFactory.publishLottoTicketFrom(fourthNumbers);

        Set<Integer> fifthNumbers = Arrays.stream(new int[]{4, 5, 6, 7, 8, 9}).boxed().collect(Collectors.toSet());
        LottoTicket fifthLottoTicket = LottoTicketFactory.publishLottoTicketFrom(fifthNumbers);

        Set<Integer> missNumbers = Arrays.stream(new int[]{5, 6, 7, 8, 9, 10}).boxed().collect(Collectors.toSet());
        LottoTicket missLottoTicket = LottoTicketFactory.publishLottoTicketFrom(missNumbers);

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
