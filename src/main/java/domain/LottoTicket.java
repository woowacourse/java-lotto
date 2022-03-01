package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.LottoNumberGenerator;

public class LottoTicket {

    private final List<Lotto> lottos;
    private int autoLottoCount;

    public LottoTicket(int autoLottoCount, List<Lotto> passiveLottos, LottoNumberGenerator generatorPolicy) {
        this.autoLottoCount = autoLottoCount;
        this.lottos = new ArrayList<>(passiveLottos);
        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(new AutoLotto(generatorPolicy));
        }
    }

    public int getAutoLottoCount() {
        return this.autoLottoCount;
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }

    public LottoResult getLottoResult(WinningLotto winningLotto, LottoResult lottoResult) {
        for (Lotto lotto : lottos) {
            lottoResult.putLottoRank(winningLotto.countLottoRank(lotto));
        }

        return lottoResult;
    }
}
