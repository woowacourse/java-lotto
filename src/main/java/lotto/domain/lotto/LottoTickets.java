package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private final List<Lotto> manualLottoTickets;
    private final List<Lotto> autoLottoTickets;

    public LottoTickets(LottoRepository lottoRepository) {
        this.manualLottoTickets = new ArrayList<>(lottoRepository.getManualLottos());
        this.autoLottoTickets = new ArrayList<>(lottoRepository.getAutoLottos());
    }

    public List<Lotto> getAllLottoTickets() {
        List<Lotto> allLottoTickets = new ArrayList<>(manualLottoTickets);
        allLottoTickets.addAll(autoLottoTickets);
        return Collections.unmodifiableList(allLottoTickets);
    }

    public List<Lotto> getManualLottoTickets() {
        return Collections.unmodifiableList(manualLottoTickets);
    }

    public List<Lotto> getAutoLottoTickets() {
        return Collections.unmodifiableList(autoLottoTickets);
    }

    public int getCountOfManualLotto() {
        return manualLottoTickets.size();
    }

    public int getCountOfAutoLotto() {
        return autoLottoTickets.size();
    }
}
