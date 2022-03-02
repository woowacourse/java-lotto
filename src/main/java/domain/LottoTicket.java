package domain;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.LottoNumberGenerator;

public class LottoTicket {

    private final List<Lotto> lottos;
    private int autoLottoCount;
    LottoFactory autoLottoFactory;
    LottoFactory passiveLottoFactory;

    public LottoTicket(int autoLottoCount, List<Lotto> passiveLottos, LottoNumberGenerator generatorPolicy) {
        this.autoLottoCount = autoLottoCount;
        this.autoLottoFactory = new AutoLottoFactory(generatorPolicy, autoLottoCount);
        this.passiveLottoFactory = new PassiveLottoFactory(passiveLottos);
        this.lottos = generateLotto();
    }

    private List<Lotto> generateLotto() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(passiveLottoFactory.create());
        lottos.addAll(autoLottoFactory.create());
        return lottos;
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
