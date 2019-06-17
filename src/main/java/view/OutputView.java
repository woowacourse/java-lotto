package view;

import model.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public static String index(List<Timestamp> dates) {
        Map<String, Object> model = new HashMap<String, Object>() {{
            put("price", Lotto.PRICE);
            put("priceFormatted", NumberFormat.getInstance().format(Lotto.PRICE));
            put("history", WebView.historySelect(dates));
            put("recentRoundMenu", WebView.roundSelect());
        }};
        return render(model, "app.html");
    }

    public static void printPurchaseAmount(LottoPurchaseAmount purchaseAmount) {
        System.out.println(
                "\n수동으로 " + purchaseAmount.manual() + "장, 자동으로 " + purchaseAmount.auto() + "개를 구매했습니다."
        );
    }

    public static void printLottos(Lottos lottos) {
        lottos.forEach(lotto -> System.out.println(lotto));
    }

    public static void printWinningNumbers(WinningNumbers winningNumbers) {
        System.out.println(
            "\n금주의 당첨 번호 : "
            + winningNumbers.mainNumbers()
            + " + 보너스 번호 "
            + winningNumbers.bonusNumber()
        );
    }

    public static void printResult(LottoResult result) {
        System.out.println("\n당첨 통계\n---------");
        result.forEach(x -> {
            System.out.println(
                    x.rank().numberOfMatches()
                    + ((x.rank().equals(LottoRank.SECOND)) ? "개 일치, 보너스 볼 일치 (" : "개 일치 (")
                    + NumberFormat.getInstance().format(x.rank().prize())
                    + "원) - "
                    + x.number()
                    + "개"
            );
        });
        System.out.format("총 수익률은 %d%%입니다.", Math.round(result.earningRate()));
    }
}
