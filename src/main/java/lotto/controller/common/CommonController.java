package lotto.controller.common;

import lotto.controller.exception.IllegalParameterException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;
import java.util.Optional;

public class CommonController {
    private CommonController() {
        throw new AssertionError();
    }

    public static <T extends Exception> void handlingException(T exception, Request request, Response response) {
        response.status(500);
        response.body("잘못 입력했습니다.\n" + exception.getMessage());
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public static String nullable(String param) {
        return Optional.ofNullable(param).orElseThrow(IllegalParameterException::new);
    }
}
