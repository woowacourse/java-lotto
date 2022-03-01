package lotto.domain;

import static lotto.domain.LottoTicket.LOTTO_NUMBER_SIZE;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.lottonumbergenerator.LottoNumberAutoGenerator;
import lotto.domain.lottonumbergenerator.LottoNumberGenerator;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(int lottoCount) {
        this.lottoTickets = createLottoTickets(lottoCount);
    }

    public List<Rank> getRanksWithWinningNumbers(WinningNumbers winningNumbers) {
        List<Rank> ranks = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            ranks.add(lottoTicket.compareNumbers(winningNumbers.getWinningNumbers(), winningNumbers.getBonusNumber()));
        }
        return ranks;
    }

    private List<LottoTicket> createLottoTickets(int lottoCount) {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberAutoGenerator();
        ArrayList<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottoTickets.add(new LottoTicket(lottoNumberGenerator.getLottoNumbers(LOTTO_NUMBER_SIZE)));
        }
        return lottoTickets;
    }

    public List<LottoTicket> getLottoTickets() {
        return new ArrayList<>(lottoTickets);
    }
}
