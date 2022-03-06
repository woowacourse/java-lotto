package lotto.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoBuyMoney;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinnerLotto;
import lotto.domain.generator.AutoLottoGenerator;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.generator.ManualLottoGenerator;
import lotto.domain.vo.LottoNumber;

public class LottoService {

    public Lottos createLottos(LottoBuyMoney lottoBuyMoney, int manualAmount, List<List<Integer>> manualNumbers) {
        int autoLottoAmount = lottoBuyMoney.countAutoAmountByManualAmount(manualAmount);
        List<Lotto> lottos = createManulLottos(manualNumbers);
        lottos.addAll(createAutoLottos(autoLottoAmount));
        return new Lottos(lottos);
    }

    public Map<Rank, Integer> match(Lottos lottos, List<Integer> winnerNumbers, int bonusNumber) {
        WinnerLotto winnerLotto = new WinnerLotto(
            Lotto.of(numbersToLottoNumbers(winnerNumbers)),
            LottoNumber.of(bonusNumber)
        );
        return createRankResults(lottos.match(winnerLotto));
    }

    public double calculateProfitRate(Map<Rank, Integer> statistics, double inputMoney) {
        return sumReward(statistics) / inputMoney;
    }

    private List<Lotto> createManulLottos(List<List<Integer>> manualNumbers) {
        LottoGenerator lottoGenerator = new ManualLottoGenerator(collectNumbersToCollectLottoNumbers(manualNumbers));
        return lottoGenerator.generateLottos();
    }

    private List<Lotto> createAutoLottos(int autoLottosAmount) {
        LottoGenerator lottoGenerator = new AutoLottoGenerator(autoLottosAmount);
        return lottoGenerator.generateLottos();
    }

    private List<List<LottoNumber>> collectNumbersToCollectLottoNumbers(List<List<Integer>> collectNumbers) {
        return collectNumbers.stream()
            .map(this::numbersToLottoNumbers)
            .collect(Collectors.toList());
    }

    private List<LottoNumber> numbersToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
            .map(LottoNumber::of)
            .collect(Collectors.toList());
    }

    private Map<Rank, Integer> createRankResults(List<Rank> ranks) {
        Map<Rank, Integer> rankResults = new EnumMap<>(Rank.class);
        for (Rank value : Rank.values()) {
            rankResults.put(value, value.findRewardCount(ranks));
        }
        return rankResults;
    }

    private long sumReward(Map<Rank, Integer> statistics) {
        return statistics.keySet().stream()
            .mapToLong(rank -> rank.getReward() * statistics.get(rank))
            .sum();
    }
}
