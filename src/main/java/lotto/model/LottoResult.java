package lotto.model;

import static java.util.stream.Collectors.*;
import static lotto.ValidationUtils.*;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import lotto.model.lottofactory.Lotto;
import lotto.model.lottofactory.ManualLottoFactory;

public class LottoResult {
    private static final String ERROR_DUPLICATION_BONUS_NUMBER = "보너스 볼 번호가 당첨 번호와 중복입니다.";

    private final Map<Rank, Long> result;

    LottoResult(Map<LottoType, Lottos> lottosMap, List<Integer> rawWinningNumbers, int bonusNumber) {
        List<Integer> copyOfRawWinningNumbers = List.copyOf(rawWinningNumbers);
        validateEmptyCollection(copyOfRawWinningNumbers);
        validateDuplicateBonusNumber(copyOfRawWinningNumbers, bonusNumber);

        Lotto winningNumbers = new ManualLottoFactory(List.of(copyOfRawWinningNumbers)).generate();
        this.result = generateLottoResult(Map.copyOf(lottosMap), winningNumbers,
            new LottoNumber(bonusNumber));
    }

    private void validateDuplicateBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_BONUS_NUMBER);
        }
    }

    private Map<Rank, Long> generateLottoResult(Map<LottoType, Lottos> lottosMap, Lotto winningNumbers,
        LottoNumber bonusNumber) {
        return createTotalLottos(lottosMap).stream()
            .map(lotto -> lotto.match(winningNumbers, bonusNumber))
            .collect(groupingBy(rank -> rank, () -> new EnumMap<>(Rank.class), counting()));
    }

    private List<Lotto> createTotalLottos(Map<LottoType, Lottos> lottosMap) {
        return lottosMap.keySet()
            .stream()
            .flatMap(lottoType -> lottosMap.get(lottoType).getLottos().stream())
            .collect(toList());
    }

    public long getRankCount(Rank rank) {
        return result.getOrDefault(rank, 0L);
    }

    Yield calculateYield(LottoMoney lottoMoney) {
        return new Yield(lottoMoney, Map.copyOf(result));
    }
}