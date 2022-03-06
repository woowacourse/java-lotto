package lotto.model;

import java.util.ArrayList;
import java.util.List;
import lotto.model.generator.LottoGenerator;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

public class LottoMachine {

    public static final String BUYING_LOTTO_COUNT_EXCESS_ERROR_MESSAGE = "[ERROR] 구매할 수 있는 수량을 넘었습니다.";
    private static final String LOTTO_MACHINE_ERROR_MESSAGE = "[ERROR] 오류가 발생했습니다.";

    private final LottoGenerator lottoGenerator;
    private Money money;
    private Lottos lottos;
    private RankCount rankCount;

    public LottoMachine(final LottoGenerator lottoGenerator, Money money) {
        this.lottoGenerator = lottoGenerator;
        this.money = money;
        this.lottos = new Lottos(new ArrayList<>());
    }

    public Lottos buy(final LottoCount lottoCount, final List<Lotto> lottos) {
        Lottos manualLottos = generateManualLottos(lottoCount, lottos);
        Lottos autoLottos = generateAutoLottos(lottoCount);
        this.lottos = this.lottos.plusLottos(manualLottos);
        this.lottos = this.lottos.plusLottos(autoLottos);
        return this.lottos;
    }

    private Lottos generateManualLottos(final LottoCount lottoCount, final List<Lotto> lottos) {
        validateManualLottos(lottoCount, lottos);
        return new Lottos(lottos);
    }

    private void validateManualLottos(final LottoCount lottoCount, final List<Lotto> lottos) {
        if (lottos.size() != lottoCount.getManualLottoCount()) {
            throw new IllegalArgumentException(LOTTO_MACHINE_ERROR_MESSAGE);
        }
    }

    private Lottos generateAutoLottos(final LottoCount lottoCount) {
        Lottos autoLottos = lottoGenerator.generateLottos(lottoCount.getAutoLottoCount(),
                LottoNumber.LOTTO_NUMBER_MINIMUM_RANGE,
                LottoNumber.LOTTO_NUMBER_MAXIMUM_RANGE,
                LottoNumbers.LOTTO_LENGTH);
        return autoLottos;
    }

    public void calculateResult(final WinningLotto winningLotto) {
        this.rankCount = new RankCount(winningLotto.checkRank(lottos));
    }

    public Lottos getLottos() {
        return lottos;
    }

    public double getRevenue() {
        return ((double) rankCount.calculateTotalMoney() / money.getMoney());
    }

    public Integer getEachRankCount(Rank rank) {
        return rankCount.getEachRankCount(rank);
    }
}
