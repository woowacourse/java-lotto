package lotto.domain.number;

import lotto.domain.result.Money;

import java.util.List;

// 로또 가격을 알고, Money를 차감시키고, 로또를 발행하는 클래스
public class LottoRoundsGenerator implements LottoRoundsGenerable {
    public static final int LOTTO_PRICE = 1_000;
    private final ManualLottoRoundsGenertor manualLottoRoundsGenertor;
    private final AutoLottoRoundsGenerator autoLottoRoundsGenerator;

    private LottoRoundsGenerator(List<LottoRound> manualLottoRounds) {
        this.manualLottoRoundsGenertor = new ManualLottoRoundsGenertor(manualLottoRounds);
        this.autoLottoRoundsGenerator = new AutoLottoRoundsGenerator();
    }

    public static LottoRoundsGenerator createConfiguratedLottoRoundsGenerator(List<LottoRound> manualLottoRounds) {
        return new LottoRoundsGenerator(manualLottoRounds);
    }

    @Override
    public LottoRounds generate(Money money) {
        LottoRounds manualLottoRounds = manualLottoRoundsGenertor.generate(money);
        LottoRounds autoLottoRounds = autoLottoRoundsGenerator.generate(money);
        return manualLottoRounds.combine(autoLottoRounds);
    }
}
