package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private static final int LOTTO_NUMBER_SIZE = 6;

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(int lottoCount) {
        this.lottoTickets = new ArrayList<>();
        createLottoTickets(lottoCount);
    }

    public List<Rank> getRanksWithWinningNumbers(WinningNumbers winningNumbers) {
        List<Rank> ranks = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            ranks.add(lottoTicket.compareNumbers(winningNumbers.getWinningNumbers(), winningNumbers.getBonusNumber()));
        }
        return ranks;
    }

    private void createLottoTickets(int lottoCount) {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        for (int i = 0; i < lottoCount; i++) {
            lottoTickets.add(new LottoTicket(lottoNumberGenerator.getLottoNumbers(LOTTO_NUMBER_SIZE)));
        }
    }

    public List<LottoTicket> getLottoTickets() {
        return new ArrayList<>(lottoTickets);
    }
}
