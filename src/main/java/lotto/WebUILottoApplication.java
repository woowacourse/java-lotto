package lotto;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class WebUILottoApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");
        get("/api/greeting/:name", (req, res) -> {
            return "eee"+req.params(":name");
        });
    }

}
