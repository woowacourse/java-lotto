package lotto.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private List<Lotto> lottos = new ArrayList<>();

    public Lottos(List<String> manualNumbers, int countOfPurchase) {
        createLottosManually(manualNumbers);
        createLottosAutomatically(
                (countOfPurchase - manualNumbers.size()) > 0
                        ? (countOfPurchase - manualNumbers.size())
                        : 0);
    }

    private void createLottosManually(List<String> manualNumbers) {
        for (String manualNumber : manualNumbers) {
            lottos.add(LottoFactory.createLottoManually(manualNumber));
        }
    }

    private void createLottosAutomatically(int countAutoLottos) {
        while (lottos.size() < countAutoLottos) {
            Lotto lotto = LottoFactory.createLottoAutomatically();
            if (!lottos.contains(lotto)) {
                lottos.add(lotto);
            }
        }
    }

    public WinningResult match(WinningLotto winningLotto) {
        Map<LottoRank, Integer> result = initializeResult();

        for (Lotto lotto : lottos) {
            LottoRank rank = winningLotto.calculateRank(lotto);
            result.put(rank, result.get(rank) + 1);
        }
        result.remove(LottoRank.MISS);
        return new WinningResult(result);
    }

    private Map<LottoRank, Integer> initializeResult() {
        Map<LottoRank, Integer> result = new LinkedHashMap<>();
        for (LottoRank lottoRank : LottoRank.values()) {
            result.put(lottoRank, 0);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Lotto lotto : lottos) {
            sb.append(lotto);
            sb.append("\n");
        }
        return sb.toString();
    }
}
