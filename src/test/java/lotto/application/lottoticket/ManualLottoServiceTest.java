package lotto.application.lottoticket;

import lotto.domain.lottoticket.InvalidLottoTicketException;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.dto.LottoTicketDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ManualLottoServiceTest {
    @Test
    void 수동_로또_장_수를_반환하는_메소드() {
        assertThat(ManualLottoService.getNumOfManualLotto("3", 12)).isEqualTo(3);
        assertThrows(InvalidLottoTicketException.class, () -> {
            ManualLottoService.getNumOfManualLotto("3", 2);
        });
    }

    @Test
    void String_숫자들을_ticket으로_변환() {
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6");
        assertThat(ManualLottoService.getManualLotto(numbers)).isInstanceOf(LottoTicket.class);
    }

    @Test
    void ticket을_DTO로_변환() {
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6");
        LottoTicket lottoTicket = ManualLottoService.getManualLotto(numbers);
        assertThat(ManualLottoService.getManualLottoDto(lottoTicket)).isInstanceOf(LottoTicketDto.class);
    }
}