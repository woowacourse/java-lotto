package model;

import dto.LottoDto;
import dto.LottosDto;
import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public void rankAll(WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            lotto.rankTier(winningLotto);
        }
    }

    public int countTiers(PrizeTier prizeTier) {
        return (int) lottos.stream()
                .filter(lotto -> lotto.isTierMatched(prizeTier))
                .count();
    }

    public long calculateTotalPrize() {
        long totalPrize = 0;
        for (Lotto lotto : lottos) {
            totalPrize += lotto.extractPrize();
        }
        return totalPrize;
    }

    public LottosDto toDto() {
        List<LottoDto> lottoDtos = new ArrayList<>();
        for (Lotto lotto : lottos) {
            lottoDtos.add(lotto.toDto());
        }
        return new LottosDto(lottoDtos);
    }
}
