package old.dto;

import java.util.List;
import java.util.stream.Collectors;

import old.domain.LottoNumber;
import old.domain.LottoTicket;

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
