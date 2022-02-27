package domain;

import domain.strategy.LottoNumberGenerateStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(LottoQuantity lottoQuantity, LottoNumberGenerateStrategy lottoNumberGenerator) {
        this.lottos = generateLottos(lottoQuantity, lottoNumberGenerator);
    }

    private List<Lotto> generateLottos(LottoQuantity lottoQuantity, LottoNumberGenerateStrategy lottoNumberGenerator) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoQuantity.getLottoQuantity(); i++) {
            lottos.add(new Lotto(generateLottoNumbers(lottoNumberGenerator)));
        }
        return lottos;
    }

    private Set<LottoNumber> generateLottoNumbers(LottoNumberGenerateStrategy lottoNumberGenerator) {
        return lottoNumberGenerator.generateLottoNumbers()
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
    }

    public Map<Rank, WinningCount> getResultByWinningLotto(WinningLotto winningLotto) {
        Map<Rank, WinningCount> lottoResult = setupLottoResult();

        lottos.stream()
                .map(winningLotto::getRankByLotto)
                .forEach(rank -> increaseCountByRank(lottoResult, rank));

        return lottoResult;
    }

    private void increaseCountByRank(Map<Rank, WinningCount> lottoResult, Rank rank) {
        int count = lottoResult.get(rank).getCount() + 1;
        lottoResult.put(rank, new WinningCount(count));
    }

    private Map<Rank, WinningCount> setupLottoResult() {
        Map<Rank, WinningCount> lottoResult = new HashMap<>();
        lottoResult.put(Rank.FIRST, new WinningCount(0));
        lottoResult.put(Rank.SECOND, new WinningCount(0));
        lottoResult.put(Rank.THIRD, new WinningCount(0));
        lottoResult.put(Rank.FOURTH, new WinningCount(0));
        lottoResult.put(Rank.FIFTH, new WinningCount(0));
        lottoResult.put(Rank.NO_MATCH, new WinningCount(0));

        return lottoResult;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    @Override
    public String toString() {
        return "Lottos{" +
                "lottos=" + lottos +
                '}';
    }
}
