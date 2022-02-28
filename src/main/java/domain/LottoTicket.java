package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.LottoNumberGenerator;

public class LottoTicket {

    private final List<Lotto> passiveLottos;
    private final List<Lotto> autoLottos = new ArrayList<>();

    public LottoTicket(int autoLottoCount, List<Lotto> passiveLottos, LottoNumberGenerator generatorPolicy) {
        this.passiveLottos = new ArrayList<>(passiveLottos);
        for (int i = 0; i < autoLottoCount; i++) {
            autoLottos.add(new Lotto(generatorPolicy.generate()));
        }
    }

    public List<Lotto> getPassiveLottos() {
        return List.copyOf(passiveLottos);
    }

    public List<Lotto> getAutoLottos() {
        return List.copyOf(autoLottos);
    }

    public LottoResult getLottoResult(WinningLotto winningLotto, LottoResult lottoResult) {
        List<Lotto> totalLottos = new ArrayList<>(passiveLottos);
        totalLottos.addAll(autoLottos);

        for (Lotto lotto : totalLottos) {
            lottoResult.putLottoRank(winningLotto.countLottoRank(lotto));
        }

        return lottoResult;
    }
}
