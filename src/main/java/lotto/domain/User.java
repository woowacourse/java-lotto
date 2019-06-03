package lotto.domain;

import java.util.*;

public class User {

    private final Money money;
    private final int countOfLotto;
    private List<Lotto> userLottos = new ArrayList<>();

    public User(Money money) {
        this.money = money;
        this.countOfLotto = money.calculateCountOfLotto();
        generateUserLottos();
    }

    private void generateUserLottos() {
        for (int i = 0; i < countOfLotto; i++) {
            userLottos.add(new UserLotto(LottoAutoGenerator.generateAutoLotto()));
        }
    }

    public List<Lotto> getUserLottos() {
        return this.userLottos;
    }

    public Map<Rank, Integer> calculateCountOfRank(WinningLotto winningLotto) {
        Map<Rank, Integer> countOfRank = new TreeMap<>();
        Arrays.stream(Rank.values()).forEach(rank -> countOfRank.put(rank, 0));
        for (Lotto userLotto : userLottos) {
            int countOfMatch = userLotto.calculateCountOfMatch(winningLotto);
            Rank thisRank = Rank.valueOf(countOfMatch, false);
            int countOfThisRank = countOfRank.get(thisRank);
            countOfRank.put(thisRank, countOfThisRank + 1);
        }
        return countOfRank;
    }

    public double calculateRateOfReturn(Prize calculatePrize) {
        return calculatePrize.divideByMoney(money) * 100;
    }

}
