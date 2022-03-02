package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {

    private static List<List<Integer>> generateLottoTickets() {
        return List.of(
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44)
        );
    }

    private static List<Integer> generateNumbers() {
        return List.of(8, 21, 23, 41, 42, 43);
    }

    @DisplayName("로또 번호 리스트들을 받아 로또 티켓 리스트를 생성한다.")
    @Test
    void 로또_티켓_발급() {
        // given
        List<List<Integer>> numbers = generateLottoTickets();

        // when & then
        assertDoesNotThrow(() -> new LottoTickets(numbers));
    }


    @DisplayName("로또 티켓 여러장을 합쳐 함께 관리할 수 있다.")
    @Test
    void 로또_티켓_합침() {
        // given
        LottoTickets lottoTickets = new LottoTickets(generateLottoTickets());

        // when
        lottoTickets.combine(new LottoTickets(generateLottoTickets()));

        // then
        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(6);
    }

    @DisplayName("당첨 번호를 전달 받아 판별하여 로또 결과를 반환한다.")
    @Test
    void 당첨_번호_판별() {
        // given
        LottoTickets lottoTickets = new LottoTickets(generateLottoTickets());
        WinningNumber winningNumber = new WinningNumber(new LottoTicket(generateNumbers()), new LottoNumber(7));

        // when
        LottoResult lottoResult = lottoTickets.determine(winningNumber);

        // then
        assertThat(lottoResult.getRanks().get(Rank.FIRST)).isEqualTo(1);
    }
}