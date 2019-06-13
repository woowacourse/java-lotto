package lotto.application.lottomoney;

import lotto.domain.lottomoney.MoneyForLotto;
import lotto.domain.lottomoney.dto.LottoMoneyDTO;

class LottoMoneyAssembler {
    private LottoMoneyAssembler() {
    }

    static LottoMoneyDTO makeLottoMoneyDto(MoneyForLotto moneyForLotto) {
        return new LottoMoneyDTO(moneyForLotto.getNumOfLotto(), moneyForLotto.getChange());
    }
}
