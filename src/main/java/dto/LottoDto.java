package dto;

import java.util.List;
import model.lottonumber.Lotto;

public class LottoDto {
    private List<Integer> lottoNumbers;

    private LottoDto(final List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoDto of(final Lotto lotto) {
        return new LottoDto(lotto.getNumbers());
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
