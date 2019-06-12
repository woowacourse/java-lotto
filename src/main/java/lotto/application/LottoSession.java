package lotto.application;

import lotto.domain.lottoresult.WinningLotto;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTickets;

public class LottoSession {
    private static long numOfLotto;
    private static LottoTickets lottoTickets = new LottoTickets();
    private static WinningLotto winningLotto;

    public static void setNumOfLotto(long numOfLotto) {
        LottoSession.numOfLotto = numOfLotto;
    }

    public static long getNumOfLotto() {
        return numOfLotto;
    }

    public static void addLottoTicket(LottoTicket lottoTicket) {
        lottoTickets.add(lottoTicket);
    }

    public static LottoTicket getLottoTicket(int index) {
        return lottoTickets.getTicket(index);
    }

    public static void joinLottoTickets(LottoTickets newLottoTickets) {
        lottoTickets = LottoTickets.join(lottoTickets, newLottoTickets);
    }

    public static WinningLotto getWinningLotto() {
        return winningLotto;
    }

    public static void setWinningLotto(WinningLotto winningLotto) {
        LottoSession.winningLotto = winningLotto;
    }
}
