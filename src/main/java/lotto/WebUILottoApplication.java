package lotto;

import lotto.domain.*;
import lotto.domain.generate.LottosFactory;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

        post("/lottos", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<String> selfInput = Arrays.asList(req.queryParams("selfLottos").split("\r\n"));
            LottosFactory lottosFactory = req.session().attribute("lottosFactory");
            Lottos lottos = lottosFactory.generateLottos(selfInput, req.session().attribute("price"));
            model.put("lottos", lottos.getLottos());
            req.session().attribute("lottos", lottos);

            return render(model, "lottos.html");
        });






        exception(Exception.class, (exception, req, res) -> {
            String message = null;
            try {
                message = encodeUTF8(exception.getMessage());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            res.redirect("/?message=" + message);

        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static String encodeUTF8(final String message) throws UnsupportedEncodingException {
        return URLEncoder.encode(message, "UTF-8");
    }

}