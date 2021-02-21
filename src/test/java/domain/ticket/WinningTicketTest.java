package domain.ticket;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static domain.ticket.Ticket.LOTTO_TICKET_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class WinningTicketTest {
    private List<Integer> winningNumbers;
    private List<Integer> duplicateNumber;
    private List<Integer> moreNumbers;

    @BeforeEach
    public void setUp() {
        winningNumbers = Arrays.asList(1, 3, 5, 7, 9, 11);
        duplicateNumber = Arrays.asList(1, 1, 2, 3, 4, 5);
        moreNumbers = Arrays.asList(1, 2, 3, 4 ,5 , 6, 7);
    }

    @DisplayName("당첨 티켓 객체 생성 성공")
    @Test
    void create_success() {
        assertThatCode(() -> new WinningTicket(winningNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("객체 생성 실패 : 중복된 번호")
    @Test
    void create_duplicateNumber_exceptionThrown() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new WinningTicket(duplicateNumber))
                .withMessageContaining("중복");
    }

    @DisplayName("객체 생성 실패 : 숫자가 6개 보다 많은 경우")
    @Test
    void create_incorrectSize_exceptionThrown() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new WinningTicket(moreNumbers))
                .withMessageContaining(
                        String.format("개수가 %d이 아닙니다", LOTTO_TICKET_SIZE)
                );
    }

    @DisplayName("동등 비교")
    @Test
    void equals() {
        final WinningTicket winningTicket = new WinningTicket(winningNumbers);
        assertThat(winningTicket)
                .isEqualTo(new WinningTicket(new ArrayList<>(winningNumbers)));
    }

    @DisplayName("로또 티켓과 동등 비교")
    @Test
    void equals_lottoTicket() {
        final WinningTicket winningTicket = new WinningTicket(winningNumbers);
        final LottoTicket lottoTicket = new LottoTicket(new ArrayList<>(winningNumbers));

        assertThat(winningTicket.isSameNumbers(lottoTicket))
                .isEqualTo(true);
    }
}
