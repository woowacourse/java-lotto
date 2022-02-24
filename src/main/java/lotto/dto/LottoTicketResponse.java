package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

public class LottoTicketResponse {

    private List<Integer> numbers;

    private LottoTicketResponse(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static LottoTicketResponse from(LottoTicket lottoTicket) {
        return new LottoTicketResponse(lottoTicket.getNumbers()
            .stream()
            .map(LottoNumber::getNumber)
            .collect(Collectors.toList()));
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
