package lotto;

import lotto.controller.Lotto;

import static spark.Spark.get;

public class WebUILottoApplication {
    public static void main(String[] args) {
        Lotto.lottoGame();
    }
//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            return render(model, "index.html");
//        });
//    }
//
//    private static String render(Map<String, Object> model, String templatePath) {
//        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
//    }
}
