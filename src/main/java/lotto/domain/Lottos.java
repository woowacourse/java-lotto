package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lottos {
    private List<Lotto> lottos;

    public Lottos(List<List<Integer>> manualNumbers, int autoCount) {
        this.lottos = createLottos(manualNumbers);
        addAutoLottos(autoCount);
    }

    private List<Lotto> createLottos(List<List<Integer>> manualNumbers) {
        return manualNumbers.stream()
                .map(manualNumber -> new Lotto(manualNumber))
                .collect(Collectors.toList());
    }

    private void addAutoLottos(int autoCount) {
        for (int i = 0; i < autoCount; i++) {
            this.lottos.add(new Lotto());
        }
    }

    public LottoResult createLottoResult(WinningNumber winningNumber) {
        Map<LottoRank, Integer> RankMap = LottoRank.initLottoRankMap();
        lottos.stream()
                .map(winningNumber::findLottoRank)
                .forEach(lottoRank -> addRank(RankMap, lottoRank));
        return new LottoResult(RankMap);
    }

    private Integer addRank(Map<LottoRank, Integer> RankMap, LottoRank lottoRank) {
        return RankMap.put(lottoRank, RankMap.get(lottoRank) + 1);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
