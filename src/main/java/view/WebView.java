package view;

import model.*;

import java.text.NumberFormat;
public class WebView {
    public static String roundSelect() {
        int recentRound = Lotto.recentRound();
        StringBuilder menu = new StringBuilder();
        menu.append("<option value=\"" + recentRound + "\" selected>" + recentRound + "</option>");
        for (int i = recentRound - 1; i > 0; i--) {
            menu.append("<option value=\"" + i + "\">" + i + "</option>");
        }
        return menu.toString();
    }

    public static String formatWinningNumbers(WinningNumbers numbers) {
        StringBuilder formatted = new StringBuilder();
        numbers.mainNumbers().forEach(x -> formatted.append(
                "<span class=\"ball color" + Integer.parseInt(x.toString()) / 10 + "\">" + x.toString() + "</span>"
        ));
        formatted.append("<span> + </span>");
        formatted.append(
                "<span class=\"ball color5\">"
                + numbers.bonusNumber().toString()
                + "</span>"
        );
        return formatted.toString();
    }

    public static String formatLottos(Lottos lottos) {
        return lottos.toString().replace("[[", "[").replace("]]", "]")
                .replace("[", "<span class=\"lotto\">").replace("]", "</span>").replace(">, <", "><br /><");
    }

    public static String formatResult(LottoResult result) {
        StringBuilder formatted = new StringBuilder("<br />");
        result.forEach(x ->
                formatted.append(
                        x.rank().numberOfMatches()
                        + ((x.rank().equals(LottoRank.SECOND)) ? "개 일치, 보너스 볼 일치 (" : "개 일치 (")
                        + NumberFormat.getInstance().format(x.rank().prize().amount())
                        + "원) - "
                        + x.number()
                        + "개<br />"
                )
        );
        formatted.append("수익률 : " + Math.round(result.earningRate() * 100.0) / 100.0 + "%");
        return formatted.toString();
    }

    public static String error() {
        return "<script>alert(\"잘못된 입력입니다.\"); history.back()</script>";
    }
}