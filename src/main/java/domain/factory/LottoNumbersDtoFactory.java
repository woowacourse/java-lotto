package domain.factory;

import domain.numberscontainer.LottoNumbersDto;

public interface LottoNumbersDtoFactory {
    LottoNumbersDto generate(boolean containsBonus);
}
