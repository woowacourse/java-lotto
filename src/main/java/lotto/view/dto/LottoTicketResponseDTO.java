package lotto.view.dto;

import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.ball.LottoBall;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoTicketResponseDTO {
    private final List<Integer> numbers;

    LottoTicketResponseDTO(LottoTicket lottoTicket) {
        this.numbers = lottoTicket.getLottoBalls().stream()
                .map(LottoBall::getNumber)
                .collect(Collectors.toList());
    }

    public static List<LottoTicketResponseDTO> getLottoTicketResponseDTOS(LottoTicketBundle lottoTicketBundle) {
        List<LottoTicketResponseDTO> lottoTicketResponseDTOS = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTicketBundle.getLottoTickets()) {
            lottoTicketResponseDTOS.add(new LottoTicketResponseDTO(lottoTicket));
        }
        return lottoTicketResponseDTOS;
    }

    public Integer[] getNumbers() {
        return this.numbers.toArray(new Integer[6]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicketResponseDTO that = (LottoTicketResponseDTO) o;
        return Objects.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
