package lotto.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lotto.domain.Lotto.Lotto;
import lotto.domain.Lotto.LottoGenerator;
import lotto.domain.LottoMoney.LottoMoney;
import lotto.domain.LottoNumber.LottoNumber;
import lotto.domain.LottoRank;

public class LottoController {
    private static final int SUM_UNIT = 1;
    private static final int INIT_COUNT = 0;

    public List<Lotto> purchaseLotto(int numberOfLotto) {
        List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < numberOfLotto; i++) {
            lottos.add(LottoGenerator.generate());
        }
        return lottos;
    }

    public Map<LottoRank, Integer> getLottoRankCount(List<Lotto> lottos, Lotto winningLotto,
                                                     LottoNumber bonusLottoNumber) {
        Map<LottoRank, Integer> lottoRankCount = new TreeMap<>(Collections.reverseOrder());

        for (LottoRank lottoRank : LottoRank.values()) {
            lottoRankCount.put(lottoRank, INIT_COUNT);
        }

        for (Lotto lotto : lottos) {
            LottoRank lottoRank = LottoRank.of(lotto.getMatchCount(winningLotto), lotto.isContains(bonusLottoNumber));
            lottoRankCount.replace(lottoRank, lottoRankCount.get(lottoRank) + SUM_UNIT);
        }
        return lottoRankCount;
    }

    public int getWinningRatio(Map<LottoRank, Integer> lottoRankCount, LottoMoney inputLottoMoney) {
        LottoMoney initLottoMoney = LottoMoney.MISS_PRIZE;
        for (Map.Entry<LottoRank, Integer> lottoEntry : lottoRankCount.entrySet()) {
            initLottoMoney = initLottoMoney.add(lottoEntry.getKey().getWinningMoney().multiply(lottoEntry.getValue()));
        }
        return initLottoMoney.getWinningRate(inputLottoMoney);
    }
}
