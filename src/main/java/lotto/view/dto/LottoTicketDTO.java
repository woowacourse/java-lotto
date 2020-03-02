package lotto.view.dto;

import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.ball.LottoBall;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketDTO {
    private final List<Integer> numbers;

    public LottoTicketDTO(LottoTicket lottoTicket) {
        this.numbers = lottoTicket.getLottoBalls().stream()
                .map(LottoBall::getNumber)
                .collect(Collectors.toList());
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }
}
