package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManualLottoTickets {
    private List<Lotto> manualLottoTickets = new ArrayList<>();

    public ManualLottoTickets(List<List<String>> lottoNumbers) {
        for (int i = 0; i < lottoNumbers.size(); i++) {
            this.manualLottoTickets.add(LottoFactory.createManualLotto(lottoNumbers.get(i)));
        }
    }

    public void addManualLottoTickets(List<Lotto> lottoTickets) {
        lottoTickets.addAll(Collections.unmodifiableList(manualLottoTickets));
    }
}
