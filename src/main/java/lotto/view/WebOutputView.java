package lotto.view;

import lotto.domain.*;

public class WebOutputView {
    private static final int LOTTO_SIZE = 6;

    public static String printLottoTicketsAsBall(LottoTickets lottoTickets) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lottoTickets.getLottoTicketsSize(); i++) {
            Lotto lotto = lottoTickets.getLotto(i);
            stringBuilder.append(printLottoAsBall(lotto)).append("<br>");
        }
        return stringBuilder.toString();
    }

    private static String printLottoAsBall(Lotto lotto) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < LOTTO_SIZE; i++) {
            stringBuilder.append(printNumberAsBall(lotto.getLottoNumber(i)));
        }
        return stringBuilder.toString();
    }

    private static String printNumberAsBall(int number) {
        return "<span class=\"ball ball" + (number / 10 + 1) + "\">" + number + "</span>";
    }

    public static String printWinningLottoAsBall(WinningLotto winningLotto) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < LOTTO_SIZE; i++) {
            stringBuilder.append(printNumberAsBall(winningLotto.getLottoNumber(i)));
        }
        stringBuilder.append(" + ").append(printNumberAsBall(winningLotto.getBonusNumber()));
        return stringBuilder.toString();
    }

    public static String printLottoResult(LottoResult lottoResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Rank rank : Rank.values()) {
            stringBuilder.append("<span class=\"rank\">").append(rank).append(" : ").append(lottoResult.getResultByRank(rank)).append("</span><br>");
        }
        stringBuilder.append("수익률은 ").append(lottoResult.getRateOfReturn()).append("%");
        return stringBuilder.toString();
    }
}
