package lotto;

import java.util.*;

import lotto.domain.Money;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.nio.charset.Charset;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/lotto", (req, res) -> {
            List<NameValuePair> pairs = URLEncodedUtils.parse(req.body(), Charset.defaultCharset());

            Money money = getMoney(pairs);
            List<String> params = getLottos(pairs);

            try {
                return 0;
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static List<String> getLottos(List<NameValuePair> pairs) {
        List<String> lottos = new ArrayList<>();

        for (NameValuePair pair : pairs) {
            lottos.add(pair.getValue());
        }

        return lottos;
    }

    private static Money getMoney(List<NameValuePair> pairs) {
        NameValuePair firstPair = pairs.get(0);
        Money money = new Money(Integer.parseInt(firstPair.getValue()));
        pairs.remove(0);

        return money;
    }

}
