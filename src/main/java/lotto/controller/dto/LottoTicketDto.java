package lotto.controller.dto;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

public class LottoTicketDto {

    private final List<Integer> lottoNumbers;

    public LottoTicketDto(LottoTicket lottoTicket) {
        this.lottoNumbers = lottoTicket.getLottoNumbers()
                .stream()
                .map(LottoNumber::getLottoNumber)
                .sorted()
                .collect(toList());
    }

    public LottoTicket toLottoTicket() {
        return new LottoTicket(lottoNumbers);
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
