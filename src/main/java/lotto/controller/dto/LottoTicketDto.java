package lotto.controller.dto;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

public class LottoTicketDto {

    private final List<Integer> lottoNumbers;

    private LottoTicketDto(LottoTicket lottoTicket) {
        this.lottoNumbers = lottoTicket.getLottoNumbers()
                .stream()
                .map(LottoNumber::getLottoNumber)
                .sorted()
                .collect(toList());
    }

    public static LottoTicketDto from(LottoTicket lottoTicket) {
        return new LottoTicketDto(lottoTicket);
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
