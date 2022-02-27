package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketDTO {
    private final List<Integer> lottoNumbers;

    public LottoTicketDTO(LottoTicket lottoTicket) {
        lottoNumbers = new ArrayList<>();
        for (LottoNumber lottoNumber : lottoTicket.getNumbers()) {
            this.lottoNumbers.add(lottoNumber.getNumber());
        }
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
