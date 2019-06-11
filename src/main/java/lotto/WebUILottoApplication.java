package lotto;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lotto.creator.LottosFactory;
import lotto.creator.ManualLottoCreator;
import lotto.dao.LottosDao;
import lotto.dao.MoneyDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.*;
import lotto.domain.Number;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.nio.charset.Charset;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static DataBase dataBase = new DataBase();
    private static MoneyDao moneyDao = new MoneyDao(dataBase);
    private static LottosDao lottosDao = new LottosDao(dataBase);
    private static WinningLottoDao winningLottoDao = new WinningLottoDao(dataBase);

    public static void main(String[] args) {
        get("/buyLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/lookupLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "lookupPage.html");
        });

        post("/lotto", (req, res) -> {
            List<NameValuePair> pairs = URLEncodedUtils.parse(req.body(), Charset.defaultCharset());

            Money money = getMoney(pairs);
            List<String> manuals = getLottos(pairs);

            Lottos lottos = LottosFactory.create(manuals, money);
            int times = winningLottoDao.countWinningLottoTimes();

            moneyDao.addMoney(money, times);
            lottosDao.addLottos(lottos, times);

            try {
                return lottosDao.findByTimes(times + 1).getLottos();
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

            winningLottoDao.addWinningLotto(winningLotto);

            int times = winningLottoDao.countWinningLottoTimes();
            Lottos lottos = lottosDao.findByTimes(times);
            LottoResult lottoResult  = new LottoResult(lottos.getLottos(), winningLotto);

            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(lottoResult.getLottoResult());

            return json;
        });

        get("/lottoYield", (req, res) -> {
            int latelyTimes = winningLottoDao.countWinningLottoTimes();
            LottoResult lottoResult = createLottoResult(latelyTimes);
            Money money = moneyDao.findByTimes(latelyTimes);

            double result = ((double) lottoResult.getRewardAll() / money.getMoney()) * 100;

            return result;
        });

        get("/lottoNextTimes", (req, res) -> {
            int latelyTimes = winningLottoDao.countWinningLottoTimes();

            return latelyTimes + 1;
        });

        get("/lotto/money/:Times", (req, res) -> {
            int times = Integer.parseInt(req.params(":Times"));
            Money money = moneyDao.findByTimes(times);
            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(money);

            return json;
        });

        get("/lotto/lottos/:Times", (req, res) -> {
            int times = Integer.parseInt(req.params(":Times"));
            Lottos lottos = lottosDao.findByTimes(times);
            Gson gson = new GsonBuilder().create();

            return lottos.getLottos();
        });

        get("/lotto/winningLotto/:Times", (req, res) -> {
            int times = Integer.parseInt(req.params(":Times"));
            WinningLotto winningLotto = winningLottoDao.findByTimes(times);
            Gson gson = new GsonBuilder().create();

            return gson.toJson(winningLotto);
        });

        get("/lotto/lottoResult/:Times", (req, res) -> {
            int times = Integer.parseInt(req.params(":Times"));
            LottoResult lottoResult = createLottoResult(times);
            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(lottoResult.getLottoResult());

            return json;
        });

        get("/lotto/lottoYield/:Times", (req, res) -> {
            int times = Integer.parseInt(req.params(":Times"));
            LottoResult lottoResult = createLottoResult(times);
            Money money = moneyDao.findByTimes(times);

            double result = ((double) lottoResult.getRewardAll() / money.getMoney()) * 100;
            return result;
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

    private static LottoResult createLottoResult(int times) throws Exception {
        Lottos lottos = lottosDao.findByTimes(times);
        WinningLotto winningLotto = winningLottoDao.findByTimes(times);
        return new LottoResult(lottos.getLottos(), winningLotto);
    }
}
