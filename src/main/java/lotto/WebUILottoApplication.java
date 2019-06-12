package lotto;

import lotto.service.ErrorService;
import lotto.service.LottoService;
import lotto.service.RoundService;
import lotto.utils.Encoder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {

    public static void main(String[] args) {
        port(8080);

        externalStaticFileLocation("src/main/resources/templates");

        get("/", LottoService::main);

        get("/round", RoundService::round);

        post("/lottos", LottoService::addLottos);

        get("/lottos", LottoService::getLottos);

        post("/result", LottoService::doGetResult);

        get("/result", LottoService::doPostResult);

        get("/error", ErrorService::exception);

        exception(Exception.class, (exception, req, res) -> {
            String message = null;
            try {
                message = Encoder.encodeUTF8(exception.getMessage());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            res.redirect("/error?message=" + message);
        });
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
