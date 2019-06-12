package lotto.application;

import lotto.domain.lottoresult.WinningLotto;
import lotto.domain.lottoticket.LottoTicket;

import java.util.ArrayList;
import java.util.List;

public class LottoSession {
    private static long numOfLotto;
    private static List<LottoTicket> manualLottoTickets = new ArrayList<>();
    private static WinningLotto winningLotto;

    public static void setNumOfLotto(long numOfLotto) {
        LottoSession.numOfLotto = numOfLotto;
    }

    public static long getNumOfLotto() {
        return numOfLotto;
    }

    public static void addManualLottoTicket(LottoTicket lottoTicket) {
        manualLottoTickets.add(lottoTicket);
    }

    public static LottoTicket getManualLottoTicket(int index) {
        return manualLottoTickets.get(index);
    }

    public static WinningLotto getWinningLotto() {
        return winningLotto;
    }

    public static void setWinningLotto(WinningLotto winningLotto) {
        LottoSession.winningLotto = winningLotto;
    }
}
