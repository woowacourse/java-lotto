package lotto;

import lotto.domain.dao.LottoDao;
import lotto.domain.dao.LottoResultDao;
import lotto.domain.dao.PaymentInfoDao;
import lotto.domain.dto.PaymentInfoDTO;
import lotto.domain.dto.RankingDTO;
import lotto.domain.dto.ResultDTO;
import lotto.domain.lotto.*;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.domain.lottogenerator.RandomLottoGeneratingStrategy;
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
        post("/payment", (req, res) -> {
            String userName = nullable(req.queryParams("user_name"));

            // TODO: 2019-06-13 How to process insert result? void?
            int userInsertResult = LottoDao.getInstance().insertUser(userName);

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

        // input lotto -> lotto result page
        post("/result", (req, res) -> {
            int countOfLotto = Integer.parseInt(nullable(req.queryParams("countOfLotto")));
            int round = Integer.parseInt(nullable(req.queryParams("round")));
            String name = nullable(req.queryParams("name"));

            LottoRepository lottoRepository = addLottos(req.queryParamsValues("lotto_number"), countOfLotto);

            int insertLottoTicket = LottoDao.getInstance().insertLottoTicket(
                    new LottoTickets(lottoRepository), round);

            WinningLotto winningLotto = createWinningLotto(
                    nullable(req.queryParams("winning_lotto")),
                    nullable(req.queryParams("bonus_ball")));

            int insertWinningLotto = LottoDao.getInstance().insertWinningLotto(winningLotto, round);

            ResultDTO resultDTO = createResultDTO(winningLotto, lottoRepository, round, name);
            int resultInsertResult = LottoResultDao.getInstance().insertLottoResult(resultDTO);


            res.redirect("/lottoResult/" + round);

            // TODO How to process return?
            return render(null, "lottoResult.html");
        });

        get("/result/:round", (req, res) -> {
            int round = Integer.parseInt(nullable(req.params(":round")));
            ResultDTO resultDTO = createResult(round);

            List<Lotto> lottoTicket = LottoDao.getInstance().selectAllLotto(round);

            Map<String, Object> model = new HashMap<>();
            model.put("result", resultDTO);
            model.put("winningLotto", LottoDao.getInstance().selectWinningLotto(round));
            model.put("lottoTicket", lottoTicket);
            model.put("name", resultDTO.getName());
            model.put("auto", lottoTicket.stream().filter(Lotto::isAuto).count());
            model.put("manual", lottoTicket.stream().filter(lotto -> !lotto.isAuto()).count());
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

    private static List<RankingDTO> createUserRanking(List<ResultDTO> lottoGames) {
        Map<String, Long> ranking = lottoGames.stream()
                .collect(groupingBy(ResultDTO::getName,
                        summingLong(ResultDTO::getTotalWinningMoney)));

        return ranking.keySet().stream()
                .map(key -> new RankingDTO(key, ranking.get(key))).sorted(Comparator.reverseOrder())
                .collect(toList());
    }


    private static ResultDTO createResult(int round) throws SQLDataException {
        ResultDTO resultDTO = LottoResultDao.getInstance().selectLottoResult(round);
        Result result = new Result(resultDTO.getLottoScore());
        resultDTO.setTotalWinningMoney(result.calculateTotalWinningMoney());
        resultDTO.setEarningRate(result.calculateEarningsRate(new Payment(resultDTO.getPayment())));
        return resultDTO;
    }

    private static List<String> createResponseInputTag(int countOfManualLotto) {
        return IntStream.rangeClosed(1, countOfManualLotto)
                .mapToObj(i -> "로또 번호 " + i)
                .collect(toList());
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

    private static LottoRepository addLottos(String[] inputLottos, int countOfLotto) {
        LottoRepository lottoRepository = new LottoRepository();
        List<Lotto> manualLotto = createManualLottos(inputLottos);
        lottoRepository.addAll(manualLotto);

        List<Lotto> randomLotto = createRandomLottos(countOfLotto - manualLotto.size());
        lottoRepository.addAll(randomLotto);
        return lottoRepository;
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

    private static List<Integer> splitInputLottoNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(toList());
    }

    private static List<Lotto> createManualLottos(String[] inputLottos) {
        return Arrays.stream(Optional.ofNullable(inputLottos).orElse(new String[]{}))
                .filter(WebUILottoApplication::hasContent)
                .map(WebUILottoApplication::splitInputLottoNumbers)
                .map(list -> LottoGenerator.create(new ManualLottoGeneratingStrategy(list)))
                .collect(toList());
    }

    private static List<Lotto> createRandomLottos(int countOfRandomLotto) {
        return IntStream.rangeClosed(1, countOfRandomLotto)
                .mapToObj(i -> LottoGenerator.create(new RandomLottoGeneratingStrategy()))
                .collect(toList());
    }

    private static boolean hasContent(String value) {
        return !Objects.isNull(value) && !value.isEmpty();
    }

    private static String nullable(String param) {
        return Optional.ofNullable(param).orElseThrow(IllegalArgumentException::new);
    }
}
