package domain.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import domain.rank.Ranks;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {

    final LottoTickets lottoTickets = new LottoTickets(createListOfIntegerListSizeFive(), 3);

    @DisplayName("객체 생성 성공")
    @Test
    void create_successful() {
        assertThat(lottoTickets.size()).isEqualTo(8);
    }

    @DisplayName("checkMatch의 Ranks 생성 성공")
    @Test
    void checkMatch_successful() {
        final LottoTicket winningTicket = createWinningTicket();
        final LottoNumber bonusNumber = createBonusNumber();
        assertThatCode(() -> lottoTickets.checkMatch(winningTicket, bonusNumber))
                .doesNotThrowAnyException();
    }

    private List<List<Integer>> createListOfIntegerListSizeFive() {
        return Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(1, 2, 3, 4, 5, 7),
                Arrays.asList(1, 2, 3, 4, 5, 8),
                Arrays.asList(1, 2, 3, 4, 9, 10),
                Arrays.asList(2, 3, 4, 5, 6, 7)
        );
    }

    private LottoTicket createWinningTicket() {
        return LottoTicket.generateManual(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    private LottoNumber createBonusNumber() {
        return LottoNumber.of(7);
    }
}