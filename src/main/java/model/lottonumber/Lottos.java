package model.lottonumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.rank.Rank;
import model.winningresult.WinningResult;

public class Lottos {

    private final List<Lotto> lottoGroup;

    public Lottos(final List<Lotto> manualLottoGroup, final List<Lotto> autoLottoGroup) {
        List<Lotto> lottoGroup = new ArrayList<>();
        lottoGroup.addAll(manualLottoGroup);
        lottoGroup.addAll(autoLottoGroup);

        this.lottoGroup = lottoGroup;
    }

    public WinningResult makeWinningResult(final WinningNumbers winningNumbers) {
        Map<Rank, Integer> result = new LinkedHashMap<>();
        Arrays.stream(Rank.values()).forEach(rank -> result.put(rank, 0));

        for (Lotto lotto : lottoGroup) {
            Rank foundRank = lotto.findRank(winningNumbers);
            result.put(foundRank, result.get(foundRank) + 1);
        }
        return new WinningResult(result);
    }

    public List<Lotto> getLottos() {
        return lottoGroup;
    }
}
