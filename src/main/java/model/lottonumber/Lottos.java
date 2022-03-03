package model.lottonumber;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.generator.Generator;
import model.generator.LottoNumberGenerator;
import model.rank.Rank;
import model.winningresult.WinningResult;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> manualLottos, final int autoPurchaseCount) {
        Generator generator = new LottoNumberGenerator();

        lottos = IntStream
                .rangeClosed(1, autoPurchaseCount)
                .mapToObj(index -> new Lotto(generator))
                .collect(Collectors.toList());
        lottos.addAll(manualLottos);
    }

    public WinningResult makeWinningResult(final WinningNumbers winningNumbers) {
        Map<Rank, Integer> result = new LinkedHashMap<>();
        Arrays.stream(Rank.values()).forEach(rank -> result.put(rank, 0));

        for (Lotto lotto : lottos) {
            Rank foundRank = lotto.findWinningRank(winningNumbers);
            result.put(foundRank, result.get(foundRank) + 1);
        }
        return new WinningResult(result);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
