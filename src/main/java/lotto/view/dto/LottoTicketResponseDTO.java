package lotto.view.dto;

import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.ball.LottoBall;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketResponseDTO {
    private final List<Integer> numbers;

    public LottoTicketResponseDTO(LottoTicket lottoTicket) {
        this.numbers = lottoTicket.getLottoBalls().stream()
                .map(LottoBall::getNumber)
                .collect(Collectors.toList());
    }

    public static List<LottoTicketResponseDTO> ofList(LottoTicketBundle lottoTicketBundle) {
        return lottoTicketBundle.getLottoTickets().stream()
                .map(LottoTicketResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Integer[] getNumbers() {
        Integer[] integerArray = new Integer[6];
        for (int index = 0; index < this.numbers.size(); index++) {
            integerArray[index] = this.numbers.get(index);
        }
        return integerArray;
    }
}
