package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.LottoLine;
import lotto.domain.LottoNumber;

public class LottoTicketResponse {

    private List<Integer> numbers;

    private LottoTicketResponse(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static LottoTicketResponse from(LottoLine lottoLine) {
        return new LottoTicketResponse(lottoLine.getNumbers()
            .stream()
            .map(LottoNumber::getNumber)
            .collect(Collectors.toList()));
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
