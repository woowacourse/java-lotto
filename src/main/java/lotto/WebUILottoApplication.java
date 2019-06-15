package lotto;

import lotto.domain.dao.LottoDao;
import lotto.domain.dao.LottoResultDao;
import lotto.domain.dao.PaymentInfoDao;
import lotto.domain.dto.PaymentInfoDTO;
import lotto.domain.dto.RankingDTO;
import lotto.domain.dto.ResultDTO;
import lotto.domain.lotto.Result;
import lotto.domain.paymentinfo.CountOfLotto;
import lotto.domain.paymentinfo.Payment;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLDataException;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;
import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {

        setUp();

        // connect -> index page
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<ResultDTO> lottoGames = selectAllLottoResult();

            List<RankingDTO> ranking = createUserRanking(lottoGames);

            model.put("lottoGames", lottoGames);
            model.put("ranking", ranking);
            return render(model, "index.html");
        });

        // index -> payment page
        post("/inputPayment", (req, res) -> {
            String userName = nullable(req.queryParams("user_name"));

            // TODO: 2019-06-13 How to process insert result? void?
            int userInsertResult = LottoDao.getInstance().insertUser(userName);

            Map<String, Object> model = new HashMap<>();
            model.put("name", userName);
            return render(model, "lottoPaymentInfo.html");
        });

        // payment -> input lotto numbers page
        post("/buyLotto", (req, res) -> {
            Payment payment = new Payment(Integer.parseInt(nullable(req.queryParams("payment"))));
            int countOfManualLotto = Integer.parseInt(nullable(req.queryParams("countOfManualLotto")));
            CountOfLotto countOfLotto = new CountOfLotto(payment, countOfManualLotto);

            String name = nullable(req.queryParams("name"));
            PaymentInfoDTO paymentInfoDTO = createPaymentInfoDTO(payment, countOfLotto, name);
            int round = PaymentInfoDao.getInstance().insertPayment(paymentInfoDTO);

            List<String> manualLottoInputTag = createResponseInputTag(countOfManualLotto);

            Map<String, Object> model = new HashMap<>();
            model.put("lottoName", manualLottoInputTag);
            model.put("name", name);
            model.put("countOfLotto", countOfLotto.getTotalCountOfLotto());
            model.put("round", round);
            return render(model, "lottoNumbers.html");
        });

    }

    private static void setUp() {
        staticFileLocation("/");
        port(80);
    }

    private static List<RankingDTO> createUserRanking(List<ResultDTO> lottoGames) {
        Map<String, Long> ranking = lottoGames.stream()
                .collect(groupingBy(ResultDTO::getName,
                        summingLong(ResultDTO::getTotalWinningMoney)));

        return ranking.keySet().stream()
                .map(key -> new RankingDTO(key, ranking.get(key))).sorted(Comparator.reverseOrder())
                .collect(toList());
    }

    private static List<String> createResponseInputTag(int countOfManualLotto) {
        return IntStream.rangeClosed(1, countOfManualLotto)
                .mapToObj(i -> "로또 번호 " + i)
                .collect(toList());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static List<ResultDTO> selectAllLottoResult() throws SQLDataException {
        List<ResultDTO> lottoGames = LottoResultDao.getInstance().selectAllLottoResult();

        lottoGames.forEach(resultDTO -> {
            resultDTO.setTotalWinningMoney(new Result(resultDTO.getLottoScore()).calculateTotalWinningMoney());
            resultDTO.setEarningRate(resultDTO.getTotalWinningMoney() / resultDTO.getPayment());
        });
        return lottoGames;
    }

    private static PaymentInfoDTO createPaymentInfoDTO(Payment payment, CountOfLotto countOfLotto, String name) {
        PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO();
        paymentInfoDTO.setPayment(payment.getPayment());
        paymentInfoDTO.setManual(countOfLotto.getCountOfManualLotto());
        paymentInfoDTO.setAuto(countOfLotto.getCountOfRandomLotto());
        paymentInfoDTO.setName(name);
        return paymentInfoDTO;
    }

    private static String nullable(String param) {
        return Optional.ofNullable(param).orElseThrow(IllegalArgumentException::new);
    }
}
