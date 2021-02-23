package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<List<LottoNumber>> lottoNumbersGroup) {
        this.lottoTickets = new ArrayList<>(createLottoTickets(lottoNumbersGroup));
    }

    public List<LottoTicket> lottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    private List<LottoTicket> createLottoTickets(List<List<LottoNumber>> lottoNumbersGroup) {
        return lottoNumbersGroup.stream()
                .map(LottoTicket::new)
                .collect(Collectors.toList());
    }
}
