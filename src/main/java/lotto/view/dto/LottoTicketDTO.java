package lotto.view.dto;

import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.ball.LottoBall;

import java.util.stream.Collectors;

public class LottoTicketDTO {
    private static final int NUMBER_OF_LOTTO_BALLS = 6;

    private final Integer[] numbers;

    public LottoTicketDTO(LottoTicket lottoTicket) {
        this.numbers = lottoTicket.getLottoBalls().stream()
                .map(LottoBall::getNumber)
                .collect(Collectors.toList())
                .toArray(new Integer[NUMBER_OF_LOTTO_BALLS]);
    }

    public Integer[] getNumbers() {
        return this.numbers;
    }
}
