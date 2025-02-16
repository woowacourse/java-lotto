package domain;


import static domain.Lotto.LOTTO_PRICE;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {

    private static final int DEFAULT_VALUE = 0;
    private static final int INCREMENT_UNIT = 1;
    
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos issueByMoney(int money) {
        int quantity = money / LOTTO_PRICE;
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

        List<Lotto> generatedLottos = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            Lotto issuedLotto = Lotto.issueByNumberGenerator(randomNumberGenerator);
            generatedLottos.add(issuedLotto);
        }

        return new Lottos(generatedLottos);
    }

    public int getQuantity() {
        return lottos.size();
    }

    public Map<Rank, Integer> getRankCount(WinningNumbers winningNumbers) {
        Map<Rank, Integer> rankCount = new EnumMap<>(Rank.class);
        lottos.forEach(lotto ->
                rankCount.put(
                        winningNumbers.getRank(lotto),
                        rankCount.getOrDefault(winningNumbers.getRank(lotto), DEFAULT_VALUE) + INCREMENT_UNIT));

        return rankCount;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
