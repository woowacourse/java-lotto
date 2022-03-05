package model.lottonumber;

import dto.LottoDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.generator.Generator;
import model.generator.LottoGenerator;
import model.generator.LottoNumberGenerator;
import model.rank.Rank;
import model.winningresult.WinningResult;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(final List<LottoDto> manualLottoNumberGroups, final int autoLottoCount) {
        this.lottos = LottoGenerator.makeLottos(manualLottoNumberGroups, autoLottoCount);
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
