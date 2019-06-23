package lotto.domain;

import java.util.*;

public class WinningLotto extends Lotto {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private final LottoNumber bonusBall;

    public WinningLotto(Set<LottoNumber> winningLotto, LottoNumber bonusBall) {
        super(winningLotto);
        if (winningLotto.contains(bonusBall)) {
            throw new InvalidBonusBallException("보너스 볼이 당첨 번호와 중복입니다.");
        }
        this.bonusBall = bonusBall;
    }

    public LottoNumber getBonusBall() {
        return bonusBall;
    }

    public String getNumbers(){
        return super.toString();
    }

    public boolean isBonusContain(Lotto userLotto) {
        return userLotto.isContains(bonusBall);
    }

    public Map<Rank, Integer> calculateCountOfRank(List<Lotto> userLottos) {
        Map<Rank, Integer> countOfRank = new TreeMap<>(Collections.reverseOrder());
        Arrays.stream(Rank.values()).forEach(rank -> countOfRank.put(rank, ZERO));
        for (Lotto userLotto : userLottos) {
            int countOfMatch = userLotto.calculateCountOfMatch(this);
            boolean matchBonus = isBonusContain(userLotto);
            Rank thisRank = Rank.valueOf(countOfMatch, matchBonus);
            int countOfThisRank = countOfRank.get(thisRank);

            countOfRank.put(thisRank, countOfThisRank + ONE);
        }
        countOfRank.remove(Rank.MISS);
        return countOfRank;
    }

    @Override
    public String toString(){
        return super.toString()+"  보너스볼 : "+bonusBall.toString();
    }
}
