package lotto.domain.number;

import lotto.domain.result.Money;

import java.util.List;

public class ManualLottoRoundsGenertor implements LottoRoundsGenerable {
    private final List<LottoRound> manualLottoRounds;

    public ManualLottoRoundsGenertor(List<LottoRound> manualLottoRounds) {
        this.manualLottoRounds = manualLottoRounds;
    }

    @Override
    public LottoRounds generate(Money money) {
        int manualLottoPrice = LottoRoundsGenerator.LOTTO_PRICE * manualLottoRounds.size();
        if (money.isSubtractable(manualLottoPrice)) {
            money.subtract(manualLottoPrice);
        }
        return new LottoRounds(manualLottoRounds);
    }
}
