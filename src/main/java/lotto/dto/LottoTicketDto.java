package lotto.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

public class LottoTicketDto {

    private final List<Integer> numbers;

    public LottoTicketDto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static LottoTicketDto from(LottoTicket lottoTicket) {
        return new LottoTicketDto(lottoTicket.getNumbers().stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList()));
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }
}
