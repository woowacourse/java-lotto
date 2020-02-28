package domain;

import java.util.List;

public class ManualLottoTickets {
    private List<Lotto> manualLottoTickets;

    public ManualLottoTickets(List<List<String>> lottoNumbers) {
        for (int i = 0; i < lottoNumbers.size(); i++) {
            System.out.println(lottoNumbers.get(i));
            this.manualLottoTickets.add(LottoFactory.createManualLotto(lottoNumbers.get(i)));
        }
    }
}
