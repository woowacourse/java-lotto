package dto;

import domain.Lotto;
import domain.LottoNumber;
import java.util.List;
import java.util.stream.Collectors;

public class LottoDto {
    private final List<Integer> lottoNumbers;

    public LottoDto(Lotto lotto) {
        lottoNumbers = toIntegers(lotto);
    }

    private List<Integer> toIntegers(Lotto lotto) {
        return lotto.getSortedLottoNumbers()
                .stream()
                .map(LottoNumber::toInt)
                .collect(Collectors.toList());
    }

    public List<Integer> getLottoNumbers() {
        return List.copyOf(lottoNumbers);
    }

    @Override
    public String toString() {
        return "LottoDto{" +
                "lottoNumbers=" + lottoNumbers +
                '}';
    }
}
