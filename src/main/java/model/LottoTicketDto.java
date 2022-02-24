package model;

import java.util.List;

public class LottoTicketDto {
    private final List<Integer> lottoNumbers;

    public LottoTicketDto(final List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
