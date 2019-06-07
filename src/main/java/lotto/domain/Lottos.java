package lotto.domain;

import java.util.*;

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
            lottos.add(LottoFactory.createLottoManually(Arrays.asList(manualNumber.split(","))));
        }
    }

    private void createLottosAutomatically(int countAutoLottos) {
        while (lottos.size() < countAutoLottos) {
            lottos.add(LottoFactory.createLottoAutomatically());
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
