package lotto.domain;

import java.util.List;

public class LottoDto {
    private final List<ChoiceNumber> lotto;

    public LottoDto(List<ChoiceNumber> lotto) {
        this.lotto = lotto;
    }

    public static LottoDto from(Lotto lotto) {
        return new LottoDto(lotto.getLotto());
    }

    public List<ChoiceNumber> getLotto() {
        return lotto;
    }

    public int getLottoSize() {
        return lotto.size();
    }
}
