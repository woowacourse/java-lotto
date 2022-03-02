package lotto.model;

import java.util.Map;
import lotto.model.generator.LottoGenerator;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

public class LottoMachine {

    public static final String BUYING_LOTTO_COUNT_EXCESS_ERROR_MESSAGE = "[ERROR] 구매할 수 있는 수량을 넘었습니다.";
    private static final String LOTTO_MACHINE_ERROR_MESSAGE = "[ERROR] 오류가 발생했습니다.";

    private final Money money;
    private final Lottos lottos;

    private Map<Rank, Integer> rankCount;

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
        return Lottos.combineLottos(manualLottos, autoLottos);
    }

    public void calculateResult(WinningLotto winningLotto) {
        this.rankCount = winningLotto.checkRank(lottos);
    }

    public Lottos getLottos() {
        return lottos;
    }

    public double getRevenue() {
        int sum = 0;
        for (Rank rank : Rank.values()) {
            sum += rank.getPrice() * getEachRankCount(rank);
        }
        return ((double) sum / money.getMoney());
    }

    public Integer getEachRankCount(final Rank rank) {
        return rankCount.get(rank);
    }
}
