package domain;

import view.InputConvertor;

import java.util.List;

public class LottoGame {
    private final LottoMachine lottoMachine;
    private final LottoOrder lottoOrder;

    public LottoGame(LottoMachine lottoMachine, LottoOrder lottoOrder) {
        this.lottoMachine = lottoMachine;
        this.lottoOrder = lottoOrder;
    }

    public Lottos createLottos() {
        List<Lotto> lottos = InputConvertor.createManualLottos(getManualTicketCount());
        Lottos manualLottos = lottoMachine.createManualLottos(lottos);
        Lottos autoLottos = lottoMachine.createAutoLottos(getAutoTicketCount());
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
