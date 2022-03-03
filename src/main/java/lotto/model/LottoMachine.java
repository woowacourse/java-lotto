package lotto.model;

import lotto.model.generator.LottoGenerator;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

public class LottoMachine {

    public static final String BUYING_LOTTO_COUNT_EXCESS_ERROR_MESSAGE = "[ERROR] 구매할 수 있는 수량을 넘었습니다.";
    private static final String LOTTO_MACHINE_ERROR_MESSAGE = "[ERROR] 오류가 발생했습니다.";

    private final Money money;
    private final Lottos lottos;
    private RankCount score;

    public LottoMachine(final LottoGenerator lottoGenerator, final Money money, final LottoCount lottoCount,
                        final Lottos manualLottos) {
        this.money = money;
        validateLottoMachine(lottoCount, manualLottos);
        this.lottos = generateLottos(lottoGenerator, lottoCount.getAutoLottoCount(), manualLottos);
    }

    private void validateLottoMachine(final LottoCount lottoCount, final Lottos manualLottos) {
        if (manualLottos.size() != lottoCount.getManualLottoCount()) {
            throw new IllegalArgumentException(LOTTO_MACHINE_ERROR_MESSAGE);
        }
    }

    private Lottos generateLottos(final LottoGenerator lottoGenerator, final int autoLottoCount,
                                  final Lottos manualLottos) {
        Lottos autoLottos = lottoGenerator.generateLottos(autoLottoCount, LottoNumber.LOTTO_NUMBER_MINIMUM_RANGE,
                LottoNumber.LOTTO_NUMBER_MAXIMUM_RANGE,
                LottoNumbers.LOTTO_LENGTH);
        manualLottos.plusLottos(autoLottos);
        return manualLottos;
    }

    public void calculateResult(final WinningLotto winningLotto) {
        this.score = new RankCount(winningLotto.checkRank(lottos));
    }

    public Lottos getLottos() {
        return lottos;
    }

    public double getRevenue() {
        return ((double) score.gatherTotalMoney() / money.getMoney());
    }

    public Integer getEachRankCount(Rank rank) {
        return score.getEachRankCount(rank);
    }
}
