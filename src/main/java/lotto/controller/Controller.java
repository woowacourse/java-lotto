package lotto.controller;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public interface Controller {
    default String render(Object model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
