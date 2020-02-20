package lotto.view.dto;

import lotto.domain.ticket.LottoTicket;
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

    public Integer[] getNumbers() {
        return this.numbers.toArray(new Integer[6]);
    }
}
