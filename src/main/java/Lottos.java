import java.util.*;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos() {
        lottos = new ArrayList<>();
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public void generateLottos(int count) {
        for (int i = 0; i < count; i++) {
            lottos.add(Lotto.generateNumber());
        }
        new Lottos(lottos);
    }

    public int size() {
        return lottos.size();
    }

    public Statistic getWinningStatistics(WinningNumber winningNumber, int bonusBall) {
        LinkedHashMap<Rank, Integer> result = new LinkedHashMap<>();
        Arrays.stream(Rank.values()).forEach(rank -> result.put(rank, 0));

        for (Lotto lotto : lottos) {
            int matchCount = lotto.match(winningNumber);
            boolean hasBonusBall = lotto.hasBonusBall(bonusBall);

            Rank key = Rank.valueOf(matchCount, hasBonusBall);
            result.put(key, result.get(key) + 1);
        }
        return new Statistic(result);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
