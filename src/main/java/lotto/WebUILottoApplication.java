package lotto;

import lotto.domain.*;
import lotto.domain.generate.LottosFactory;
import lotto.utils.Converter;
import lotto.utils.ResultMessage;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

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

    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}