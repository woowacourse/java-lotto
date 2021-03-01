package domain.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import domain.Wallet;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {

    @DisplayName("객체 생성 성공")
    @Test
    void create() {
        assertThatCode(LottoTickets::new).doesNotThrowAnyException();
    }

    @DisplayName("로또 티켓 자동 생성 성공")
    @Test
    void generateAuto_successful() {
        LottoTickets lottoTickets = new LottoTickets();
        Wallet wallet = new Wallet(14000);
        lottoTickets.generateAuto(wallet);
        assertThat(lottoTickets.toList().size()).isEqualTo(14);
    }

    @DisplayName("로또 티켓 수동 생성 성공")
    @Test
    void generateManual_successful() {

        List<List<Integer>> numbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(1, 2, 4, 5, 6, 7)
        );
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.generateManual(numbers);
        assertThat(lottoTickets.toList().size()).isEqualTo(2);
    }
}