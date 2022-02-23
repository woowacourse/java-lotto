package model;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketDto {
    private final List<Integer> lottoNumbers;

    public LottoTicketDto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
