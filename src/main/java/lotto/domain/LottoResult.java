package lotto.domain;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static lotto.domain.LottoRule.*;
import static lotto.domain.Rank.*;

public class LottoResult {

    private static final int RATE = 100;

    private final WinningLotto winningLotto;
    private final Lottos lottos;
    private Map<Rank, Integer> rankResult = new HashMap<>();

    public LottoResult(WinningLotto winningLotto, Lottos lottos) {
        this.winningLotto = winningLotto;
        this.lottos = lottos;
        setMap();
        countRank();
    }

    private void setMap() {
        rankResult.put(FIRST, 0);
        rankResult.put(SECOND, 0);
        rankResult.put(THIRD, 0);
        rankResult.put(FOURTH, 0);
        rankResult.put(FIFTH, 0);
        rankResult.put(MISS, 0);
    }

    private void countRank() {
        for (Lotto lotto : lottos.getLottos()) {
            Rank key = winningLotto.getWinning(lotto);
            rankResult.put(key, rankResult.get(key) + 1);
        }
    }

    public Integer getCountOfRank(Rank rank) {
        return rankResult.get(rank);
    }

    public BigInteger getEarningsRate() {
        return getEarning()
                .divide(BigInteger.valueOf(lottos.getSize() * MONEY_PER_LOTTO.get()))
                .multiply(BigInteger.valueOf(RATE));
    }

    public BigInteger getEarning() {
        BigInteger earning = BigInteger.ZERO;
        for (Map.Entry<Rank, Integer> entry : rankResult.entrySet()) {
            BigInteger temp = BigInteger.valueOf(entry.getKey().getWinningMoney());
            temp = temp.multiply(BigInteger.valueOf(entry.getValue()));
            earning = earning.add(temp);
        }
        return earning;
    }
}