package domain;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import util.Validator;

public class LottoGame {

    private static final int LOTTO_PRICE = 1000;
    private static final int EMPTY = 0;
    private static final int NO_YIELD = 0;
    private static final int INCREASE_COUNT = 1;
    private static final int BASE_COUNT = 0;
    private static final int START_INDEX = 0;
    private static final int LOTTO_NUMBER_DIGIT = 6;
    private static final long BASE_LONG_SUM = 0L;
    private static final String LOTTO_NUMBER_DUPLICATED_EXCEPTION = "[ERROR] 로또번호와 보너스번호는 중복일 수 없습니다.";
    private static final String LOTTO_NUMBER_DIGIT_EXCEPTION = "[ERROR] 로또는 6자리의 숫자로 이루어져 있습니다.";

    private Lotto winningLotto;
    private LottoNumber bonusNumber;
    private final Lottos lottos;

    public LottoGame(Money money, List<List<Integer>> quickPickLottoNumbers) {
        int totalLottoNumber = money.money() / LOTTO_PRICE;
        quickPickLottoNumbers.forEach(this::validateLength);
        lottos = Lottos.buyLottos(LottoNumberGenerator.build(totalLottoNumber, quickPickLottoNumbers), totalLottoNumber);
    }

    public static LottoGame startLottoGame(Money money, List<List<Integer>> quickPickLottoNumbers) {
        Validator.checkArgumentIsNull(money, quickPickLottoNumbers);
        return new LottoGame(money, quickPickLottoNumbers);
    }

    public void enterWinningLottoNumbersAndBonusNumber(List<Integer> notVerifiedWinningLottoNumbers
            , int notVerifiedBonusNumber) {
        Validator.checkArgumentIsNull(notVerifiedWinningLottoNumbers);
        validateLottoInput(notVerifiedWinningLottoNumbers, notVerifiedBonusNumber);
        Set<LottoNumber> winningLottoNumbers = notVerifiedWinningLottoNumbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toSet());
        this.winningLotto = new Lotto(winningLottoNumbers);
        this.bonusNumber = LottoNumber.valueOf(notVerifiedBonusNumber);
    }

    public Map<Rewards, Integer> produceResults() {
        List<Rewards> ranks = convertLottoResultsToRanks();
        Map<Rewards, Integer> results = new EnumMap<>(Rewards.class);
        ranks.forEach(rank -> results.put(rank, results.getOrDefault(rank, BASE_COUNT) + INCREASE_COUNT));
        return results;
    }

    public float calculateYield() {
        if (lottos.numberOfLottery() == EMPTY) {
            return NO_YIELD;
        }
        List<Rewards> ranks = convertLottoResultsToRanks();
        long prizeSum = ranks.stream()
                .map(Rewards::getPrize)
                .reduce(BASE_LONG_SUM, Long::sum);
        return (float) prizeSum / lottos.numberOfLottery() / LOTTO_PRICE;
    }

    public Lottos getLottos() {
        return lottos;
    }

    private void validateLottoInput(List<Integer> notVerifiedWinningLottoNumbers
            , int notVerifiedBonusNumber) {
        validateLength(notVerifiedWinningLottoNumbers);
        validateNoDuplication(notVerifiedWinningLottoNumbers, notVerifiedBonusNumber);
    }

    private void validateLength(List<Integer> target) {
        if (target.isEmpty() || target.size() != LOTTO_NUMBER_DIGIT) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DIGIT_EXCEPTION);
        }
    }

    private void validateNoDuplication(List<Integer> targets, int additionalTarget) {
        targets.add(additionalTarget);
        Set<Integer> noDuplicatedTargets = new HashSet<>(targets);
        if (noDuplicatedTargets.size() != targets.size()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATED_EXCEPTION);
        }
    }

    private List<Rewards> convertLottoResultsToRanks() {
        List<Integer> matchNumbers = lottos.compareAllLottosWithWinningLotto(winningLotto);
        List<Boolean> isBonusMatchs = lottos.checkAllLottosContainNumber(bonusNumber);
        return IntStream.range(START_INDEX, matchNumbers.size())
                .boxed()
                .map(index -> Ranks.of(matchNumbers.get(index), isBonusMatchs.get(index)))
                .map(Ranks::getReward)
                .collect(Collectors.toList());
    }
}
