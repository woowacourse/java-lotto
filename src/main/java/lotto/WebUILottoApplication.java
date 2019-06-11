package lotto;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lotto.domain.machine.Money;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");
        post("/api/money", (req, res) -> {
            String moneyAmount= req.queryParams("money");

        });
    }

}
