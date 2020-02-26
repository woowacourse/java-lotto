package domain.lottonumbers;

import domain.lottonumbers.lottonumber.LottoNumber;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class LottoTicketDto {

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicketDto(Set<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::of)
                .collect(toSet());
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
