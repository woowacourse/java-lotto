package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    private final int totalTicketCount;
    private final int manualTicketCount;

    public LottoMachine(int totalTicketCount, int manualTicketCount) {
        validateTicketCount(totalTicketCount, manualTicketCount);
        this.totalTicketCount = totalTicketCount;
        this.manualTicketCount = manualTicketCount;
    }

    public List<Lotto> makeLottoTickets(List<Lotto> manualLottos) {
        ArrayList<Lotto> lottoTickets = new ArrayList<>();
        if (manualLottos.size() != 0) {
            lottoTickets.addAll(manualLottos);
        }

        int autoLottoCount = totalTicketCount - manualTicketCount;
        for (int i = 0; i < autoLottoCount; i++) {
            lottoTickets.add(makeLottoTicket());
        }
        return lottoTickets;
    }

    private void validateTicketCount(int totalTicketCount, int manualTicketCount) {
        if (totalTicketCount < manualTicketCount) {
            throw new IllegalArgumentException("[ERROR] 최대 구입 가능 개수를 넘었습니다. 최대 구입 가능 개수 = " + totalTicketCount);
        }
        if (manualTicketCount < 0) {
            throw new IllegalArgumentException("[ERROR] 최소 티켓 구매 입력값은 0입니다.");
        }
    }

    private Lotto makeLottoTicket() {
        return new Lotto(LottoNumber.getShuffledNumbers());
    }

    public int getManualTicketCount() {
        return manualTicketCount;
    }

    public int getAutoTicketCount() {
        return totalTicketCount - manualTicketCount;
    }
}
