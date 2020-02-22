package domain.lotto;

import domain.result.Rank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.lotto.LottoNumberTest.getLottoNumberOneFixture;
import static domain.lotto.LottoNumberTest.getLottoNumberSevenFixture;
import static domain.lotto.LottoTicketTest.getLottoTicketFromFiveToTenFixture;
import static domain.lotto.LottoTicketTest.getLottoTicketFromFourToNineFixture;
import static domain.lotto.LottoTicketTest.getLottoTicketFromOneToSevenWithoutSixFixture;
import static domain.lotto.LottoTicketTest.getLottoTicketFromOneToSixFixture;
import static domain.lotto.LottoTicketTest.getLottoTicketFromThreeToEightFixture;
import static domain.lotto.LottoTicketTest.getLottoTicketFromTwoToEightWithoutSevenFixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {

    @Test
    void testWinningLotto() {
        LottoTicket lottoTicket = getLottoTicketFromOneToSixFixture();
        LottoNumber bonusNumber = getLottoNumberSevenFixture();
        WinningLotto winningLotto = new WinningLotto(lottoTicket, bonusNumber);
        assertThat(winningLotto);
    }

    @Test
    void createWinningLottoThrowException() {
        LottoTicket lottoTicket = getLottoTicketFromOneToSixFixture();
        LottoNumber bonusNumber = getLottoNumberOneFixture();
        assertThatThrownBy(() -> new WinningLotto(lottoTicket, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("createRank")
    void testWinningLottoReturnRank(WinningLotto winningLotto, LottoTicket lottoTicket, Rank rank) {
        assertThat(winningLotto.getRank(lottoTicket)).isEqualTo(rank);
    }

    private static Stream<Arguments> createRank() {
        LottoTicket lottoTicket = getLottoTicketFromOneToSixFixture();
        LottoNumber bonusNumber = getLottoNumberSevenFixture();
        WinningLotto winningLotto = new WinningLotto(lottoTicket, bonusNumber);

        LottoTicket secondLottoTicket = getLottoTicketFromOneToSevenWithoutSixFixture();
        LottoTicket thirdLottoTicket = getLottoTicketFromTwoToEightWithoutSevenFixture();
        LottoTicket fourthLottoTicket = getLottoTicketFromThreeToEightFixture();
        LottoTicket fifthLottoTicket = getLottoTicketFromFourToNineFixture();
        LottoTicket missLottoTicket = getLottoTicketFromFiveToTenFixture();

        return Stream.of(
                Arguments.of(winningLotto, missLottoTicket, Rank.MISS),
                Arguments.of(winningLotto, fifthLottoTicket, Rank.FIFTH),
                Arguments.of(winningLotto, fourthLottoTicket, Rank.FOURTH),
                Arguments.of(winningLotto, thirdLottoTicket, Rank.THIRD),
                Arguments.of(winningLotto, secondLottoTicket, Rank.SECOND),
                Arguments.of(winningLotto, lottoTicket, Rank.FIRST)
        );
    }

    public static WinningLotto getWinningLottoFixture() {
        LottoTicket lottoTicket = getLottoTicketFromOneToSixFixture();
        LottoNumber bonusNumber = getLottoNumberSevenFixture();
        return new WinningLotto(lottoTicket, bonusNumber);
    }
}
