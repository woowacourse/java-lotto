package lotto;

import lotto.domain.dto.RankingDTO;
import lotto.domain.dto.ResultDTO;
import lotto.domain.lotto.*;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.domain.paymentinfo.CountOfLotto;
import lotto.domain.paymentinfo.Payment;
import lotto.service.LottoResultService;
import lotto.service.LottoService;
import lotto.service.PaymentInfoService;
import lotto.exception.IllegalParameterException;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {

        setUp();

        // connect -> index page
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<ResultDTO> lottoGames = LottoResultService.getInstance().selectAllLottoResult();
            List<RankingDTO> ranking = LottoResultService.getInstance().createUserRanking(lottoGames);

            model.put("lottoGames", lottoGames);
            model.put("ranking", ranking);
            return render(model, "index.html");
        });

        // index -> payment page
        post("/payment", (req, res) -> {
            String userName = nullable(req.queryParams("user_name"));

            // TODO: 2019-06-13 How to process insert result? void?
            int userInsertResult = PaymentInfoService.getInstance().insertUser(userName);

            Map<String, Object> model = new HashMap<>();
            model.put("name", userName);
            return render(model, "lottoPaymentInfo.html");
        });

        // payment -> input lotto numbers page
        post("/lotto", (req, res) -> {
            Payment payment = new Payment(Integer.parseInt(nullable(req.queryParams("payment"))));
            int countOfManualLotto = Integer.parseInt(nullable(req.queryParams("countOfManualLotto")));
            CountOfLotto countOfLotto = new CountOfLotto(payment, countOfManualLotto);
            String name = nullable(req.queryParams("name"));

            int round = PaymentInfoService.getInstance().insertPaymentInfoAndReturnKeyValue(payment, countOfLotto, name);

            List<String> manualLottoInputTag = LottoService.getInstance().createResponseInputTag(countOfManualLotto);

            Map<String, Object> model = new HashMap<>();
            model.put("lottoName", manualLottoInputTag);
            model.put("name", name);
            model.put("countOfLotto", countOfLotto.getTotalCountOfLotto());
            model.put("round", round);
            return render(model, "lottoNumbers.html");
        });

        // input lotto -> lotto result page
        post("/result", (req, res) -> {
            int countOfLotto = Integer.parseInt(nullable(req.queryParams("countOfLotto")));
            int round = Integer.parseInt(nullable(req.queryParams("round")));
            String name = nullable(req.queryParams("name"));

            LottoRepository lottoRepository = LottoService.getInstance().addLottos(req.queryParamsValues("lotto_number"), countOfLotto);

            int insertLottoTicket = LottoService.getInstance().insertLottoTicket(lottoRepository, round);

            WinningLotto winningLotto = createWinningLotto(
                    nullable(req.queryParams("winning_lotto")),
                    nullable(req.queryParams("bonus_ball")));

            int insertWinningLotto = LottoService.getInstance().insertWinningLotto(winningLotto, round);

            int insertResult = LottoResultService.getInstance()
                    .insertLottoResult(createResultDTO(winningLotto, lottoRepository, round, name));

            res.redirect("/result/" + round);

            // TODO How to process return?
            return render(null, "lottoResult.html");
        });

        get("/result/:round", (req, res) -> {
            int round = Integer.parseInt(nullable(req.params(":round")));

            ResultDTO resultDTO = LottoResultService.getInstance().selectLottoResult(round);
            List<Lotto> lottoTicket = LottoService.getInstance().selectAllLotto(round);

            long countOfAutoLotto = LottoService.getInstance().calculateCountOfAutoLotto(lottoTicket);

            Map<String, Object> model = new HashMap<>();
            model.put("result", resultDTO);
            model.put("winningLotto", LottoService.getInstance().selectWinningLotto(round));
            model.put("lottoTicket", lottoTicket);
            model.put("auto", countOfAutoLotto);
            model.put("manual", lottoTicket.size() - countOfAutoLotto);
            return render(model, "lottoResult.html");
        });

        exception(IllegalArgumentException.class, (exception, req, res) -> {
            res.status(500);
            res.body("잘못 입력했습니다.\n" + exception.getMessage());
        });
    }

    private static void setUp() {
        staticFileLocation("/");
        port(80);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static ResultDTO createResultDTO(WinningLotto winningLotto, LottoRepository lottoRepository, int round, String name) {
        Result result = winningLotto.match(new LottoTickets(lottoRepository));
        ResultDTO resultDTO = new ResultDTO.Builder(round, name)
                .first(result.get(Rank.FIRST))
                .second(result.get(Rank.SECOND))
                .third(result.get(Rank.THIRD))
                .fourth(result.get(Rank.FOURTH))
                .fifth(result.get(Rank.FIFTH))
                .miss(result.get(Rank.MISS))
                .build();
        resultDTO.setTotalWinningMoney(result.calculateTotalWinningMoney());
        return resultDTO;
    }

    private static WinningLotto createWinningLotto(String winningLotto, String bonusBall) {
        List<Integer> inputWinningLotto = splitInputLottoNumbers(winningLotto);
        Lotto lotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(inputWinningLotto));
        return new WinningLotto(lotto, Integer.parseInt(bonusBall));
    }

    private static List<Integer> splitInputLottoNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(toList());
    }

    private static String nullable(String param) {
        return Optional.ofNullable(param).orElseThrow(IllegalParameterException::new);
    }
}
