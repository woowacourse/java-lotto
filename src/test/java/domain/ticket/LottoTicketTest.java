package domain.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static domain.ticket.Ticket.LOTTO_TICKET_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class LottoTicketTest {
    @DisplayName("유효한 값이면 객체 생성 성공")
    @Test
    void create_validInput_success() {
        final List<Integer> lottoNumbers = getValidNumbers();

        assertThatCode(() -> new LottoTicket(lottoNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("반자동 티켓 객체 생성 성공")
    @Test
    void create_semiAutomatic_success() {
        final List<Integer> fewerLottoNumbers = getFewerNumbers();

        assertThatCode(() -> new LottoTicket(fewerLottoNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("객체 생성 실패 : 중복된 번호")
    @Test
    void create_duplicateNumber_exceptionThrown() {
        final List<Integer> lottoNumbers = getDuplicateNumbers();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new LottoTicket(lottoNumbers))
                .withMessageContaining("중복");
    }

    @DisplayName("객체 생성 실패 : 숫자가 6개 보다 많은 경우")
    @Test
    void create_incorrectSize_exceptionThrown() {
        final List<Integer> moreLottoNumbers = getMoreNumbers();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new LottoTicket(moreLottoNumbers))
                .withMessageContaining(
                        String.format("개수가 %d이 아닙니다", LOTTO_TICKET_SIZE)
                );
    }

    @DisplayName("객체 생성 실패 : 선택한 번호에 중복이 있는 경우")
    @Test
    void create_duplicateSelection_exceptionThrown() {
        final List<Integer> lottoNumbers = getFewerAndDuplicateNumbers();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new LottoTicket(lottoNumbers))
                .withMessageContaining("선택한 번호 중에 중복 숫자가 존재합니다");
    }

    @DisplayName("동등 비교")
    @Test
    void equals() {
        final LottoTicket lottoTicket = new LottoTicket(getValidNumbers());
        assertThat(lottoTicket)
                .isEqualTo(new LottoTicket(getValidNumbers()));
    }

    @DisplayName("당첨 티켓과 동등 비교")
    @Test
    void equals_lottoTicket() {
        final LottoTicket lottoTicket = new LottoTicket(getValidNumbers());
        final WinningTicket winningTicket = new WinningTicket(getValidNumbers());

        assertThat(lottoTicket.isSameNumbers(winningTicket))
                .isEqualTo(true);
    }

    @DisplayName("오름차순 정렬 되어 저장되면 성공")
    @Test
    void shuffled_equals() {
        final List<Integer> shuffledValidNumbers = getValidNumbers();
        Collections.shuffle(shuffledValidNumbers);
        final LottoTicket lottoTicket = new LottoTicket(shuffledValidNumbers);

        assertThat(lottoTicket)
                .isEqualTo(new LottoTicket(getValidNumbers()));
    }

    private List<Integer> getValidNumbers() {
        return Arrays.asList(1, 6, 23, 40, 30, 35);
    }

    private List<Integer> getDuplicateNumbers() {
        return Arrays.asList(1, 1, 5, 10, 13, 29);
    }

    private List<Integer> getFewerNumbers() {
        return Arrays.asList(1, 5, 10, 13, 20);
    }

    private List<Integer> getMoreNumbers() {
        return Arrays.asList(1, 3, 5, 10, 13, 20, 30);
    }

    private List<Integer> getFewerAndDuplicateNumbers() {
        return Arrays.asList(1, 1);
    }
}
