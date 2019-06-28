package lotto.view;

import lotto.domain.*;

import java.util.List;

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

    public static String printLottosAsBall(List<Lotto> lottos) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Lotto lotto : lottos) {
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

    public static String printResultSelectBox(int round, int end) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<select name=\"round\">\n");
        for (int i = 1; i <= end; i++) {
            if (i == round) {
                stringBuilder.append("<option value=\"" + round + "\" selected=\"selected\">" + round + "회차" + "</option>\n");
                continue;
            }
            stringBuilder.append("<option value=\"" + i + "\">" + i + "회차" + "</option>\n");
        }
        stringBuilder.append("</select>");
        return stringBuilder.toString();
    }
}
