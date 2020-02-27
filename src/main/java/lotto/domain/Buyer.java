package lotto.domain;

import lotto.domain.lottoTicket.Lotto;
import lotto.domain.lottoTicket.LottoAmount;
import lotto.domain.lottoTicket.LottoNumberFactory;
import lotto.util.ConvertInput;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Buyer {
    private List<Lotto> lottos = new ArrayList<>();

    public Buyer(List<String> manualLottos, LottoAmount lottoAmount) {
        createManualLottos(manualLottos);
        createAutoLottos(lottoAmount.getAutoLottoAmount());
    }

    private void createManualLottos(List<String> manualLottos) {
        if (null == manualLottos) return;
        lottos.addAll(createManualLotto(manualLottos));
    }

    private List<Lotto> createManualLotto(List<String> manualLottos) {
        return manualLottos.stream()
                .map(ConvertInput::convertLottoNumbers)
                .map(Lotto::new)
                .collect(Collectors.toList());
    }

    private void createAutoLottos(int lottoTicketCount) {
        for (int i = 0; i < lottoTicketCount; i++) {
            this.lottos.add(new Lotto(LottoNumberFactory.createLottoNumbers()));
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
