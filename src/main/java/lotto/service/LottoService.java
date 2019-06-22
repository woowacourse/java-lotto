package lotto.service;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.Numbers;
import lotto.domain.purchase.PurchaseCount;
import lotto.domain.result.LottoRank;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Winning;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class LottoService {
    public Lottos createLottos(PurchaseCount purchaseCount, String[] manualLotto) {
        List<Numbers> numbers = Arrays.stream(manualLotto)
                .map(lotto -> new Numbers(lotto))
                .collect(toList());

        return Lottos.of(purchaseCount, numbers);
    }

    public Winning createWinningLotto(String winningLotto, String bonusBall) {
        return Winning.of(Lotto.of(new Numbers(winningLotto)), Integer.parseInt(bonusBall));
    }

    public LottoResult createLottoResult(Winning winning, Lottos lottos) {
        return LottoResult.of(winning, lottos);
    }

    public List<String> createLottoMessage(Map<LottoRank, Integer> rankLinkedHashMap) {
        return rankLinkedHashMap.entrySet().stream()
                .map((rankEntry) -> outputRankMessage(rankEntry.getKey(), rankEntry.getValue()))
                .collect(toList());
    }

    private String outputRankMessage(LottoRank lottoRank, int count) {
        StringBuilder stringBuilder = new StringBuilder();
        if (lottoRank == LottoRank.MISS) {
            return "";
        }
        stringBuilder.append(lottoRank.getRank() + "개 일치");
        if (lottoRank == LottoRank.SECOND) {
            stringBuilder.append(", 보너스 볼 일치");
        }
        stringBuilder.append("(" + lottoRank.getMoney() + "원) - ");
        stringBuilder.append(count + "개");

        return stringBuilder.toString();
    }
}
