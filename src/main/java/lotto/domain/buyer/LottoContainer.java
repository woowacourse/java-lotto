package lotto.domain.buyer;

import lotto.domain.WinningResult;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoType;
import lotto.domain.lotto.WinningLotto;
import lotto.dto.LottoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoContainer {
    private List<Lotto> lottos;

    public LottoContainer() {
        lottos = new ArrayList<>();
    }

    public void addLotto(List<Lotto> lottos) {
        this.lottos.addAll(lottos);
    }

    public void addLotto(Lotto lotto) {
        this.lottos.add(lotto);
    }

    public List<String> showLottos() {
        return lottos.stream().map(Lotto::toString).collect(Collectors.toList());
    }

    public int getCountOfLottoMatch(LottoType type) {
        int count = 0;
        for (Lotto lotto : lottos) {
            count = (lotto.matchType(type) ? ++count : count);
        }
        return count;
    }

    public List<LottoDto> createLottoDto() {
        return lottos.stream().map(Lotto::createLottoDto)
                .collect(Collectors.toList());
    }

    public WinningResult createResult(WinningLotto winningLotto) {
        return new WinningResult(lottos, winningLotto);
    }
}