package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {
    private final List<Lotto> lottoTickets;

    public LottoTickets(LottoRepository lottoRepository) {
        this.lottoTickets = new ArrayList<>(lottoRepository.getLottos());
    }

    public List<Lotto> getAllLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public List<Lotto> getManualLottoTickets() {
        List<Lotto> manualLottoTickets = lottoTickets.stream()
                .filter(lotto -> !lotto.getIsAuto())
                .collect(Collectors.toList());
        return Collections.unmodifiableList(manualLottoTickets);
    }

    public List<Lotto> getAutoLottoTickets() {
        List<Lotto> autoLottoTickets = lottoTickets.stream()
                .filter(Lotto::getIsAuto)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(autoLottoTickets);
    }

    public int getCountOfManualLotto() {
        return getManualLottoTickets().size();
    }

    public int getCountOfAutoLotto() {
        return getAutoLottoTickets().size();
    }
}
