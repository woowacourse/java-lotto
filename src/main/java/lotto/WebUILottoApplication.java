package lotto;

import lotto.domain.*;
import lotto.domain.generate.LottosFactory;
import lotto.utils.Converter;
import lotto.utils.ResultMessage;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static spark.Spark.*;

public class WebUILottoApplication {

    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");
        staticFiles.location("/static");

        get("/", (req, res) -> {
                    Map<String, Object> model = new HashMap<>();
                    model.put("message", req.queryParams("message"));
                    return render(model, "home.html");
                }
        );

        post("/make/lotto", (req, res) -> {
                    Map<String, Object> model = new HashMap<>();
                    Price price = new Price(Integer.parseInt(req.queryParams("price")));
                    LottosFactory lottosFactory = new LottosFactory(price, Integer.parseInt(req.queryParams("selfCount")));
                    model.put("countOfSelf", lottosFactory.getCountOfSelf());
                    req.session().attribute("price", price);
                    req.session().attribute("lottosFactory", lottosFactory);
                    List<Integer> numbers = IntStream.rangeClosed(1, 45).boxed().collect(Collectors.toList());
                    model.put("numbers", numbers);
                    req.session().attribute("numbers", numbers);
                    model.put("message", req.session().attribute("message"));
                    model.put("countOfSelf", req.queryParams("selfCount"));
                    return render(model, "lotto.html");
                }
        );

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}