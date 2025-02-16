package domain;

import dto.LottoDto;
import dto.LottosDto;
import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    public void addLotto(Lotto lotto) {
        lottos.add(lotto);
    }

    public void rankAll(WinningLotto winningLotto) {
        lottos.forEach(lotto -> lotto.rankTier(winningLotto));
    }

    public int countTiers(PrizeTier prizeTier) {
        return (int) lottos.stream()
            .filter(lotto -> lotto.isTierMatched(prizeTier))
            .count();
    }

    public long calculateTotalPrize() {
        return lottos.stream()
            .map(Lotto::getPrizeTier)
            .mapToInt(PrizeTier::getPrize)
            .sum();
    }

    public LottosDto toDto() {
        List<LottoDto> lottoDtos = lottos.stream()
            .map(Lotto::toDto)
            .toList();
        return new LottosDto(lottoDtos);
    }
}
