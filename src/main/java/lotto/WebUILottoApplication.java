package lotto;

//import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import lotto.model.*;
import lotto.model.dao.LottoResultDao;
import lotto.model.dao.LottosDao;
import lotto.model.dao.WinningLottoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.*;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static final String NEW_LINE = "\r\n";
    private static final String COMMA = ",";

    private static final Logger LOGGER = LoggerFactory.getLogger(WebUILottoApplication.class);
    private static final LottosDao LOTTOS_DAO = new LottosDao();
    private static final WinningLottoDao WINNINGLOTTOS_DAO = new WinningLottoDao();
    private static final LottoResultDao LOTTORESULT_DAO = new LottoResultDao();

    public static void main(String[] args) {
        port(8080);

        get("/", (request, respond) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/prepare_lottos", (request, response) -> {
            try {
                Money money = new Money(request.queryParams("money"));
                ManualLottoCount manualLottoCount = new ManualLottoCount(Integer.parseInt(request.queryParams("manual_count")), money);
                Lottos lottos = LottoService.produceLottos(money, prepareManualLottos(request.queryParams("manual_lotto_numbers")));

                request.session().attribute("lottos", lottos);
                request.session().attribute("money", money);

                Map<String, Object> model = new HashMap<>();
                model.put("manual_count", manualLottoCount.getCount());
                model.put("auto_count", money.calculateAutomaticLottoCount(manualLottoCount.getCount()));
                model.put("lottos", lottos);

                return render(model, "show_lottos.html");
            } catch (Exception e) {
                Map<String, Object> model = new HashMap<>();
                return render(model, "error.html");
            }
        });
        post("/produce_lotto_results", (request, response) ->
        {
            try {
                Lottos lottos = request.session().attribute("lottos");
                Money money = request.session().attribute("money");
                Lotto winningLottoTicket = new Lotto(convert(request.queryParams("winning_lotto_ticket")));
                LottoNumber bonusNumber = new LottoNumber(Integer.parseInt(request.queryParams("bonus_number")));
                WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusNumber);
                LottoResult lottoResult = new LottoResult(money, lottos.getPrizes(winningLotto));

                LOTTOS_DAO.saveLottos(lottos);
                WINNINGLOTTOS_DAO.saveWinningLotto(winningLottoTicket, bonusNumber);
                LOTTORESULT_DAO.saveResult(makeReadableLottoResult(lottoResult), money, lottoResult);

                Map<String, Object> model = new HashMap<>();
                model.put("button_identifiers", makeButtonIdentifiers());
                return render(model, "ask_round.html");
            } catch (Exception e) {
                return render(new HashMap<>(), "error.html");
            }
        });

        post("/get_winning_lotto_info", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "get_winning_lotto.html");
        });

        get("/show_final_results", (request, response) -> {
            int requestRound = Integer.parseInt(request.queryParams("result_number"));

            List<String> lottos = new ArrayList<>();
            LOTTOS_DAO.fetchRequestLottos(requestRound, lottos);
            for (String lotto : lottos) {
                LOGGER.info(lotto);
            }

            List<String> winningLotto = new ArrayList<>();
            WINNINGLOTTOS_DAO.fetchRequestWinningLotto(requestRound, winningLotto);

            List<String> results = new ArrayList<>();
            LOTTORESULT_DAO.fetchRequestResult(requestRound, results);

            Map<String, Object> model = new HashMap<>();
            model.put("final_lottos", lottos);
            model.put("final_winning", winningLotto);
            model.put("final_results", results);
            return render(model, "final.html");
        });

        get("/manual_count", ((request, response) -> {
            Lottos lottos = request.session().attribute("lottos");
            Money money = new Money(request.queryParams("money"));
            Map<String, Object> model = new HashMap<>();
            model.put("money", money);
            return render(model, "check_money.html");
        }));
    }

    private static List<Integer> makeButtonIdentifiers() throws SQLException {
        List<Integer> buttonIdentifiers = new ArrayList<>();
        int latestRound = WINNINGLOTTOS_DAO.getLatestRound();
        for (int i = 1; i <= latestRound - 1; i++) {
            buttonIdentifiers.add(i);
        }
        return buttonIdentifiers;
    }

    public static List<String> makeReadableLottoResult(LottoResult lottoResult) {
        List<String> readable = new ArrayList<>();
        for (Prize prize : Prize.values()) {
            StringBuilder sb = new StringBuilder();
            sb.append(prize.getMatchCount() + "개 일치 " + prize.getPrizeMoney() + "..." + lottoResult.getCount(prize));
            sb.append("\n");
            readable.add(sb.toString());
        }
        return readable;
    }

    private static List<String> prepareManualLottos(String manualLottoNumbers) {
        List<String> splitLottos = new ArrayList<>(Arrays.asList(manualLottoNumbers.split(NEW_LINE)));
        return splitLottos;
    }

    private static List<Integer> convert(String splitLotto) {
        List<Integer> convertedLotto = new ArrayList<>();
        List<String> lottoNumbers = new ArrayList<>(Arrays.asList(splitLotto.split(COMMA)));
        for (int i = 0, n = lottoNumbers.size(); i < n; i++) {
            convertedLotto.add(Integer.parseInt(lottoNumbers.get(i)));
        }
        return convertedLotto;
    }


    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }


}
