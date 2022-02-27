package domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGame {

    private static final int LOTTO_PRICE = 1000;
    private static final int EMPTY = 0;
    private static final int NO_YIELD = 0;
    private static final int INCREASE_COUNT = 1;
    private static final int BASE_COUNT = 0;
    private static final int BASE_SUM = 0;

    private Lotto winningLotto;
    private LottoNumber bonusNumber;
    private Lottos lottos;

    public LottoGame() {
    }

    public LottoGame(List<Lotto> lottos) {
        this.lottos = new Lottos(lottos);
    }

    public Lottos buyLotto(Money money) {
        int lottoAmount = money.money() / LOTTO_PRICE;
        lottos = Lottos.buyLottos(lottoAmount);
        return lottos;
    }

    public void enterWinningLottoNumbersAndBonusNumber(List<Integer> notVerifiedWinningLottoNumbers
            , int notVerifiedBonusNumber) {
        List<LottoNumber> winningLottoNumbers = notVerifiedWinningLottoNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        this.winningLotto = new Lotto(winningLottoNumbers);
        this.bonusNumber = new LottoNumber(notVerifiedBonusNumber);
    }

    public Map<Rewards, Integer> produceResults() {
        List<Rewards> ranks = convertLottoResultsToRanks();
        Map<Rewards, Integer> results = new EnumMap<>(Rewards.class);
        ranks.forEach(rank -> results.put(rank, results.getOrDefault(rank, BASE_COUNT) + INCREASE_COUNT));
        return results;
    }

    private List<Rewards> convertLottoResultsToRanks() {
        List<Integer> matchCounts = lottos.compareAllLottosWithWinningLotto(winningLotto);
        List<Boolean> bonusNumberContains = lottos.compareAllLottosWithBonusNumber(bonusNumber);
        return IntStream.range(0, matchCounts.size())
                .boxed()
                .map(index -> LottoRewardLogic.convertToRank(matchCounts.get(index), bonusNumberContains.get(index)))
                .collect(Collectors.toList());
    }

    public float calculateYield() {
        if (lottos.getSize() == EMPTY) {
            return NO_YIELD;
        }
        List<Rewards> ranks = convertLottoResultsToRanks();
        int prizeSum = ranks.stream()
                .map(Rewards::getPrize)
                .reduce(BASE_SUM, Integer::sum);
        return (float) prizeSum / lottos.getSize();
    }

    public Lottos getLottos() {
        return lottos;
    }
}
