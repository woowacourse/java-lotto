package lotto;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lotto.creator.LottosFactory;
import lotto.creator.ManualLottoCreator;
import lotto.domain.*;
import lotto.domain.Number;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.nio.charset.Charset;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    private static Lottos lottos;
    private static Money money;

    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/lotto", (req, res) -> {
            List<NameValuePair> pairs = URLEncodedUtils.parse(req.body(), Charset.defaultCharset());

            money = getMoney(pairs);
            List<String> manuals = getLottos(pairs);

            lottos = LottosFactory.create(manuals, money);
            System.out.println(lottos.getLottos());
            try {
                return lottos.getLottos();
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });

        get("/lottoResult", (req, res) -> {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapterFactory(new MyEnumAdapterFactory());
            Gson gson = builder.create();
            String json = gson.toJson(WinningType.values());
            return json;
        });

        post("/lottoResult", (req, res) -> {
            List<NameValuePair> pairs = URLEncodedUtils.parse(req.body(), Charset.defaultCharset());

            WinningLotto winningLotto = createWinningLotto(pairs);

            LottoResult lottoResult  = new LottoResult(lottos.getLottos(), winningLotto);

            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(lottoResult.getLottoResult());

            return json;
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

    private static WinningLotto createWinningLotto(List<NameValuePair> pairs) {
        ManualLottoCreator manualLottoCreator = new ManualLottoCreator();
        Lotto lotto = manualLottoCreator.createLotto(pairs.get(1).getValue());
        String bonus = pairs.get(0).getValue();
        Number number = Number.valueOf(Integer.parseInt(bonus));
        return new WinningLotto(lotto, number);
    }
}
