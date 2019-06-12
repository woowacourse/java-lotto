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
            try {
                List<NameValuePair> pairs = URLEncodedUtils.parse(req.body(), Charset.defaultCharset());

                Money money = getMoney(pairs);
                List<String> manuals = getLottos(pairs);

                Lottos lottos = LottosFactory.create(manuals, money);
                int times = winningLottoDao.nextWinningLottoTimes();
                moneyDao.addMoney(money, times);
                addLottos(lottos, times);

                return lottos.getLottos();
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });

        get("/lottoResult", (req, res) -> {
            try {
                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapterFactory(new MyEnumAdapterFactory());
                Gson gson = builder.create();
                String json = gson.toJson(WinningType.values());
                return json;
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });

        post("/lottoResult", (req, res) -> {
            try {
                List<NameValuePair> pairs = URLEncodedUtils.parse(req.body(), Charset.defaultCharset());
                WinningLotto winningLotto = createWinningLotto(pairs);
                int times = winningLottoDao.nextWinningLottoTimes();
                winningLottoDao.addWinningLotto(winningLotto, times);

                Lottos lottos = lottosDao.findByTimes(times);
                LottoResult lottoResult = new LottoResult(lottos.getLottos(), winningLotto);

                Gson gson = new GsonBuilder().create();
                String json = gson.toJson(lottoResult.getLottoResult());

                return json;
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });

        get("/lottoYield", (req, res) -> {
            try {
                int latelyTimes = winningLottoDao.nextWinningLottoTimes();
                LottoResult lottoResult = createLottoResult(latelyTimes);
                Money money = moneyDao.findByTimes(latelyTimes);

                double result = ((double) lottoResult.getRewardAll() / money.getMoney()) * 100;

                return result;
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }

        });

        get("/lottoNextTimes", (req, res) -> {
            int latelyTimes = winningLottoDao.nextWinningLottoTimes();
            return latelyTimes;
        });

        get("/lotto/money/:Times", (req, res) -> {
            try {
                int times = Integer.parseInt(req.params(":Times"));
                Money money = moneyDao.findByTimes(times);
                Gson gson = new GsonBuilder().create();
                String json = gson.toJson(money);

                return json;
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });

        get("/lotto/lottos/:Times", (req, res) -> {
            try {
                int times = Integer.parseInt(req.params(":Times"));
                Lottos lottos = lottosDao.findByTimes(times);
                Gson gson = new GsonBuilder().create();

                return lottos.getLottos();
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });

        get("/lotto/winningLotto/:Times", (req, res) -> {
            try {
                int times = Integer.parseInt(req.params(":Times"));
                WinningLotto winningLotto = winningLottoDao.findByTimes(times);
                Gson gson = new GsonBuilder().create();

                return gson.toJson(winningLotto);
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });

        get("/lotto/lottoResult/:Times", (req, res) -> {
            try {
                int times = Integer.parseInt(req.params(":Times"));
                LottoResult lottoResult = createLottoResult(times);
                Gson gson = new GsonBuilder().create();
                String json = gson.toJson(lottoResult.getLottoResult());

                return json;
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });

        get("/lotto/lottoYield/:Times", (req, res) -> {
            try {
                int times = Integer.parseInt(req.params(":Times"));
                LottoResult lottoResult = createLottoResult(times);
                Money money = moneyDao.findByTimes(times);

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

    private static void addLottos(Lottos lottos, int times) throws Exception {
        lottosDao.deleteLottos(times);
        lottosDao.addLottos(lottos, times);
    }
}
