package domain;

import domain.strategy.LottoNumberGenerateStrategy;
import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Lottos(LottoQuantity lottoQuantity, LottoNumberGenerateStrategy lottoNumberGenerator) {
        this.lottos = generateLottos(lottoQuantity, lottoNumberGenerator);
    }

    public static Lottos concat(Lottos lottos1, Lottos lottos2) {
        List<Lotto> newLottoValues = new ArrayList<>(lottos1.lottos);
        newLottoValues.addAll(lottos2.lottos);

        return new Lottos(newLottoValues);
    }

    private List<Lotto> generateLottos(LottoQuantity lottoQuantity, LottoNumberGenerateStrategy lottoNumberGenerator) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoQuantity.getLottoQuantity(); i++) {
            lottos.add(new Lotto(lottoNumberGenerator.generateLottoNumberValues()));
        }

        return lottos;
    }

    public WinningResult getWinningResultByWinningLotto(WinningLotto winningLotto) {
        LottoQuantity lottoQuantity = new LottoQuantity(lottos.size());
        WinningResult.Builder builder = new WinningResult.Builder(lottoQuantity);

        for (Rank rank : Rank.values()) {
            builder.setWinningCountByRank(rank, getWinningCountByRank(winningLotto, rank));
        }

        return builder.build();
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
