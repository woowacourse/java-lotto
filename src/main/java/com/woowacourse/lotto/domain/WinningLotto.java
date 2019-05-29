package com.woowacourse.lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class WinningLotto {
    private Lotto lotto;
    private LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        checkDuplicateBonusNum(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void checkDuplicateBonusNum(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("중복된 보너스 볼");
        }
    }

    public Lotto getLotto() {
        return lotto;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }


    public LottoResult getResult(LottoBuyList lottoBuyList) {
        Map<Rank, Integer> map = initResultMap();

        for (Lotto lotto: lottoBuyList.getLottoBuyList()) {
            int matchCount = this.lotto.matchCount(lotto);
            boolean isBonusMatch = lotto.isBonusMatch(bonusNumber);
            Rank rank = Rank.getRank(matchCount, isBonusMatch);
            map.put(rank, map.get(rank) + 1);
        }

        return new LottoResult(map);
    }

    private Map<Rank, Integer> initResultMap() {
        Map<Rank, Integer> map = new HashMap<>();
        map.put(Rank.FIRST, 0);
        map.put(Rank.SECOND, 0);
        map.put(Rank.THIRD, 0);
        map.put(Rank.FOURTH, 0);
        map.put(Rank.FIFTH, 0);
        map.put(Rank.NONE, 0);
        return map;
    }
}
