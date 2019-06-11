package lotto.application.lottomoney;

import lotto.domain.lottomoney.MoneyForLotto;
import lotto.domain.lottomoney.dto.LottoMoneyDto;

class LottoMoneyAssembler {
    private LottoMoneyAssembler() {
    }

    static LottoMoneyDto makeLottoMoneyDto(MoneyForLotto moneyForLotto) {
        return new LottoMoneyDto(moneyForLotto.getNumOfLotto(), moneyForLotto.getChange());
    }
}
