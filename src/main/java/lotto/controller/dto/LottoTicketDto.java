package lotto.controller.dto;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
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

    private LottoTicketDto(List<Integer> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public static LottoTicketDto from(LottoTicket lottoTicket) {
        return new LottoTicketDto(lottoTicket);
    }

    public static LottoTicketDto from(List<Integer> lottoNumbers) {
        return new LottoTicketDto(lottoNumbers);
    }

    public LottoTicket toLottoTicket() {
        return new LottoTicket(lottoNumbers);
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
