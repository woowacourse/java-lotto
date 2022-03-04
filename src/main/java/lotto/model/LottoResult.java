package lotto.model;

import static java.util.stream.Collectors.*;
import static lotto.ValidationUtils.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import lotto.model.lottos.AutoLottos;
import lotto.model.lottos.Lottos;

public class LottoResult {
    private static final String ERROR_DUPLICATION_BONUS_NUMBER = "보너스 볼 번호가 당첨 번호와 중복입니다.";

    private final Map<Rank, Long> result;

    public LottoResult(Lottos manualLottos, AutoLottos autoLottos, List<Integer> integers, int bonusNumber) {
        List<Integer> winningNumbers = new ArrayList<>(integers);
        validateEmptyCollection(winningNumbers);
        validateDuplicateBonusNumber(winningNumbers, bonusNumber);
        this.result = generateLottoResult(manualLottos, autoLottos, new Lotto(winningNumbers), new LottoNumber(bonusNumber));
    }

    private void validateDuplicateBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_BONUS_NUMBER);
        }
    }

    private Map<Rank, Long> generateLottoResult(Lottos manualLottos, AutoLottos autoLottos, Lotto winningNumbers,
        LottoNumber bonusNumber) {
        return Map.copyOf(manualLottos.getTotalLottos(autoLottos).stream()
            .map(lotto -> lotto.match(winningNumbers, bonusNumber))
            .collect(groupingBy(rank -> rank, () -> new EnumMap<>(Rank.class), counting())));
    }

    public long getRankCount(Rank rank) {
        return result.getOrDefault(rank, 0L);
    }

    long getTotalWinningMoney() {
        return result.entrySet().stream()
            .map(entry -> entry.getKey().getMoney() * entry.getValue())
            .mapToLong(i -> i)
            .sum();
    }

    Yield calculateYield(LottoMoney lottoMoney) {
        return new Yield(lottoMoney, getTotalWinningMoney());
    }
}