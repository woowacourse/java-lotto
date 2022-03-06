package domain;

import java.util.List;

public class LottoGame {
    private final LottoMachine lottoMachine;
    private final LottoOrder lottoOrder;
    private final List<Lotto> manualLottos;

    public LottoGame(LottoMachine lottoMachine, LottoOrder lottoOrder, List<Lotto> manualLottos) {
        this.lottoMachine = lottoMachine;
        this.lottoOrder = lottoOrder;
        this.manualLottos = manualLottos;
    }

    public Lottos createLottos() {
        Lottos manualLottos = lottoMachine.createManualLottos(this.manualLottos);
        Lottos autoLottos = lottoMachine.createAutoLottos(lottoOrder.getAutoTicketCount());
        return autoLottos.joinLottos(manualLottos);
    }

    public LottoResult createLottoResult(Lottos lottos, WinningLotto winningLotto) {
        return new LottoResult(lottos.calculateRank(winningLotto));
    }

    public int getAutoTicketCount() {
        return lottoOrder.getAutoTicketCount();
    }

    public int getManualTicketCount() {
        return lottoOrder.getManualTicketCount();
    }
}
