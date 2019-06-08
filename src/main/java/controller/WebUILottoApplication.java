package controller;

import model.Lotto;
import model.Lottos;
import model.LottoPurchaseAmount;
import model.Money;
import spark.Filter;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        port(4567);
        staticFiles.location("/");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("priceFormatted", NumberFormat.getInstance().format(Lotto.PRICE));
            model.put("price", Lotto.PRICE);
            return render(model, "index.html");
        });
        post("/lotto", (req, res) -> {
            try {
                final int numberOfManualLottos = req.queryParams().size() - 1;
                final LottoPurchaseAmount purchaseAmount = new LottoPurchaseAmount(
                        new Money(Integer.parseInt(req.queryParams("investment").trim())),
                        numberOfManualLottos
                );
                final Lottos lottos = new Lottos(parseManualLottos(req, numberOfManualLottos), purchaseAmount);
                Map<String, Object> model = new HashMap<>();
                model.put("result", lottosToText(lottos));
                return render(model, "result.html");
            } catch (IllegalArgumentException e) {
                return "<script>alert(\"잘못된 입력입니다.\"); history.back()</script>";
            }
        });

        get("*", (req, res) -> "404!");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static List<Lotto> parseManualLottos(Request req, int number) {
        return IntStream.range(0, number).boxed()
                .map(i -> new Lotto(req.queryParams("manualNumbers[" + i + "]")))
                .collect(Collectors.toList());
    }

    private static String lottosToText(Lottos lottos) {
        return lottos.toString().replace("[[", "[").replace("]]", "]")
                .replace("[", "<span class=\"lotto\">").replace("]", "</span>").replace(">, <", "><br /><");
    }
}