package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinTicketTest {
    private Ticket ticket;

    @BeforeEach
    void initialize() {
        ticket = new Ticket(() -> Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)));
    }
    
    @Test
    @DisplayName("당첨번호와 보너스번호가 중복일때 예외가 발생해야 합니다.")
    void winTicketInvalidTest() {
        Set<LottoNumber> winNumbers = Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6));
        assertThatThrownBy(() -> new WinTicket(new Ticket(winNumbers), new LottoNumber(6)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨번호와 보너스번호 정상 입력일때 예외를 반환하지 않아야 합니다.")
    void winTicketValidTest() {
        Ticket winTicket = new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)));
        assertThatCode(() -> new WinTicket(winTicket, new LottoNumber(7)))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{index}: {8}")
    @MethodSource("parameters")
    @DisplayName("로또 등수 확인 테스트")
    void lottoRankTest(int n1, int n2, int n3, int n4, int n5, int n6, int bonus, Rank rank, String testName) {
        Ticket winTicket = new Ticket(Set.of(new LottoNumber(n1),
            new LottoNumber(n2),
            new LottoNumber(n3),
            new LottoNumber(n4),
            new LottoNumber(n5),
            new LottoNumber(n6)));
        LottoNumber bonusNumber = new LottoNumber(bonus);
        WinTicket winningTicket = new WinTicket(winTicket, bonusNumber);
        Rank ticketRank = winningTicket.getTicketRank(ticket);
        assertThat(ticketRank).isEqualTo(rank);
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
            Arguments.of(1, 2, 3, 4, 5, 6, 7, Rank.FIRST, "1등당첨"),
            Arguments.of(1, 2, 3, 4, 5, 16, 6, Rank.SECOND, "2등당첨"),
            Arguments.of(1, 2, 3, 4, 5, 16, 17, Rank.THIRD, "3등당첨"),
            Arguments.of(1, 2, 3, 4, 15, 16, 17, Rank.FOURTH, "4등당첨"),
            Arguments.of(1, 2, 3, 14, 15, 16, 17, Rank.FIFTH, "5등당첨"),
            Arguments.of(11, 12, 13, 14, 15, 16, 17, Rank.OTHER, "6등당첨")
        );
    }
}
