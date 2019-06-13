package lotto;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lotto.creator.LottosFactory;
import lotto.creator.ManualLottoCreator;
import lotto.domain.*;
import lotto.domain.Number;
import lotto.service.LottoService;
import lotto.service.MoneyService;
import lotto.service.WinningLottoService;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.nio.charset.Charset;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static DataBase dataBase = new DataBase();

    private static MoneyService moneyService = MoneyService.getInstance(dataBase);
    private static LottoService lottoService = LottoService.getInstance(dataBase);
    private static WinningLottoService winningLottoService = WinningLottoService.getInstance(dataBase);

    public static void main(String[] args) {
        get("/buylotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/lookuplotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "lookupPage.html");
        });

        post("/lotto", (req, res) -> {
            try {
                List<NameValuePair> pairs = URLEncodedUtils.parse(req.body(), Charset.defaultCharset());

                Money money = createMoney(pairs);
                List<String> manuals = getLottos(pairs);
                Lottos lottos = LottosFactory.create(manuals, money);

                int nextTimes = winningLottoService.nextWinningLottoTimes();

                moneyService.addMoney(money, nextTimes);
                lottoService.addLottos(lottos, nextTimes);

                return lottos.getLottos();
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });

        get("/lottoresult", (req, res) -> {
            try {
                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapterFactory(new WinningTypeEnumAdapterFactory());
                Gson gson = builder.create();

                return gson.toJson(WinningType.values());
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });

        post("/lottoresult", (req, res) -> {
            try {
                Gson gson = new GsonBuilder().create();
                List<NameValuePair> pairs = URLEncodedUtils.parse(req.body(), Charset.defaultCharset());

                int nextTimes = winningLottoService.nextWinningLottoTimes();

                WinningLotto winningLotto = createWinningLotto(pairs);

                Lottos lottos = lottoService.getLottos(nextTimes);

                winningLottoService.addWinningLotto(winningLotto, nextTimes);
                LottoResult lottoResult = new LottoResult(lottos.getLottos(), winningLotto);

                return gson.toJson(lottoResult.getLottoResult());
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });

        get("/lottoyield", (req, res) -> {
            try {
                int times = winningLottoService.nowWinningLottoTimes();

                LottoResult lottoResult = createLottoResult(times);
                Money money = moneyService.findByTimes(times);

                double result = ((double) lottoResult.getRewardAll() / money.getMoney()) * 100;

                return result;
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }

        });

        get("/lottonexttimes", (req, res) -> {
            int latelyTimes = winningLottoService.nextWinningLottoTimes();
            return latelyTimes;
        });

        get("/lotto/money/:times", (req, res) -> {
            try {
                int times = Integer.parseInt(req.params(":times"));
                Money money = moneyService.findByTimes(times);
                Gson gson = new GsonBuilder().create();

                return gson.toJson(money);
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });

        get("/lotto/lottos/:times", (req, res) -> {
            try {
                int times = Integer.parseInt(req.params(":times"));
                Lottos lottos = lottoService.getLottos(times);

                return lottos.getLottos();
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });

        get("/lotto/winninglotto/:times", (req, res) -> {
            try {
                int times = Integer.parseInt(req.params(":times"));
                WinningLotto winningLotto = winningLottoService.findByTimes(times);
                Gson gson = new GsonBuilder().create();

                return gson.toJson(winningLotto);
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });

        get("/lotto/lottoresult/:times", (req, res) -> {
            try {
                int times = Integer.parseInt(req.params(":times"));
                LottoResult lottoResult = createLottoResult(times);
                Gson gson = new GsonBuilder().create();
                String json = gson.toJson(lottoResult.getLottoResult());

                return json;
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });

        get("/lotto/lottoyield/:times", (req, res) -> {
            try {
                int times = Integer.parseInt(req.params(":times"));
                LottoResult lottoResult = createLottoResult(times);
                Money money = moneyService.findByTimes(times);

                double result = ((double) lottoResult.getRewardAll() / money.getMoney()) * 100;
                return result;
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

    private static Money createMoney(List<NameValuePair> pairs) {
        NameValuePair firstPair = pairs.get(0);
        pairs.remove(0);
        return new Money(Integer.parseInt(firstPair.getValue()));
    }

    private static WinningLotto createWinningLotto(List<NameValuePair> pairs) {
        ManualLottoCreator manualLottoCreator = new ManualLottoCreator();
        Lotto lotto = manualLottoCreator.createLotto(pairs.get(1).getValue());
        String bonus = pairs.get(0).getValue();
        Number number = Number.valueOf(Integer.parseInt(bonus));

        return new WinningLotto(lotto, number);
    }

    private static LottoResult createLottoResult(int times) throws Exception {
        Lottos lottos = lottoService.getLottos(times);
        WinningLotto winningLotto = winningLottoService.findByTimes(times);
        return new LottoResult(lottos.getLottos(), winningLotto);
    }
}
