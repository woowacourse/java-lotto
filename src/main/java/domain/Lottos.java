package domain;

import domain.strategy.LottoNumberGenerateStrategy;
import java.util.ArrayList;
import java.util.List;
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

    public WinningResult getWinningResultByWinningLotto(WinningLotto winningLotto) {
        return new WinningResult.Builder(new LottoQuantity(lottos.size()))
                .first(getWinningCountByRank(winningLotto, Rank.FIRST))
                .second(getWinningCountByRank(winningLotto, Rank.SECOND))
                .third(getWinningCountByRank(winningLotto, Rank.THIRD))
                .fourth(getWinningCountByRank(winningLotto, Rank.FOURTH))
                .fifth(getWinningCountByRank(winningLotto, Rank.FIFTH))
                .noMatch(getWinningCountByRank(winningLotto, Rank.NO_MATCH))
                .build();
    }

    private WinningCount getWinningCountByRank(WinningLotto winningLotto, Rank rank) {
        int winningCount = (int) lottos.stream()
                .filter(lotto -> winningLotto.getRankByLotto(lotto).equals(rank))
                .count();

        return new WinningCount(winningCount);
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
