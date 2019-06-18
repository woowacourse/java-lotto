package lotto;

import lotto.dao.LottoDAO;
import lotto.dao.WinningLottoDAO;
import lotto.domain.CountOfLotto;
import lotto.domain.Payment;
import lotto.domain.Rank;
import lotto.domain.Result;
import lotto.domain.lotto.*;
import lotto.domain.lotto.dto.LottoDTO;
import lotto.domain.lotto.dto.ResultDTO;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.domain.lottogenerator.RandomLottoGeneratingStrategy;
import lotto.view.InputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;
import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    private static Payment payment;
    private static CountOfLotto countOfLotto;
    private static LottoTickets lottoTickets;

    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/selectPage.html", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "selectPage.html");
        });

        get("/selectResult.html", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            LottoDAO lottoDAO = LottoDAO.getInstance();
            List<Lotto> lottos = new ArrayList<>();

            try {
                lottos = lottoDAO.selectByRound(Integer.parseInt(req.queryParams("inputRound")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<LottoDTO> lottoDTOs = lottos.stream()
                    .map(LottoDTO::new)
                    .collect(Collectors.toList());

            model.put("lottos", lottoDTOs);

            return render(model, "selectResult.html");
        });

        get("/inputStep1.html", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("lottoPrice", Payment.LOTTO_PRICE);
            model.put("paymentMessage", InputView.PAYMENT_MESSAGE);
            model.put("countOfManualLottoMessage", InputView.COUNT_OF_MANUAL_LOTTO_MESSAGE);

            return render(model, "inputStep1.html");
        });

        post("/inputStep2.html", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("lottoNumberMessage", InputView.LOTTO_NUMBER_MESSAGE);
            model.put("lottoNumberMinBoundary", LottoNumber.MIN_BOUNDARY);
            model.put("lottoNumberMaxBoundary", LottoNumber.MAX_BOUNDARY);
            model.put("lottoNumberSize", Lotto.LOTTO_NUMBER_SIZE);

            payment = new Payment(req.queryParams("payment"));
            countOfLotto = new CountOfLotto(payment, req.queryParams("countOfManualLotto"));
            model.put("countOfLotto", countOfLotto);
            model.put("countOfTotalLotto", payment.calculateCountOfLotto());

            return render(model, "inputStep2.html");
        });

        post("/inputStep3.html", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("winningLottoNumberMessage", InputView.WINNING_LOTTO_NUMBER_MESSAGE);
            model.put("bonusBallMessage", InputView.BONUS_BALL_MESSAGE);

            LottoRepository lottoRepository = new LottoRepository();
            int countOfManualLotto = countOfLotto.getCountOfManualLotto();
            int countOfRandomLotto = countOfLotto.getCountOfRandomLotto();

            for (int i = 1; i <= countOfManualLotto; i++) {
                List<Integer> splitLottoNumbers = splitInputLottoNumbers(req.queryParams("inputManualLottoNumber" + i));
                lottoRepository.register(new ManualLottoGeneratingStrategy(splitLottoNumbers));
            }

            for (int i = 1; i <= countOfRandomLotto; i++) {
                lottoRepository.register(new RandomLottoGeneratingStrategy());
            }
            lottoTickets = lottoRepository.createLottoTickets();

            return render(model, "inputStep3.html");
        });

        post("result.html", ((req, res) -> {
            Map<String, Object> model = new HashMap<>();
            WinningLotto winningLotto = inputWinningLotto(req.queryParams("winningLottoNumber"), req.queryParams("bonusNumber"));
            Result result = lottoTickets.match(winningLotto);

            WinningLottoDAO winningLottoDAO = WinningLottoDAO.getInstance();
            LottoDAO lottoDAO = LottoDAO.getInstance();

            try {
                int round = winningLottoDAO.addWinningLotto(winningLotto);
                for (Lotto lotto : lottoTickets.getLottoTickets()) {
                    lottoDAO.addLotto(lotto, round);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<LottoDTO> lottoDTOs = createLottoDTOs(lottoTickets);
            model.put("lottos", lottoDTOs);

            List<ResultDTO> resultDTOs = Arrays.stream(Rank.values())
                    .map(rank -> new ResultDTO(rank, result.get(rank)))
                    .collect(Collectors.toList());
            resultDTOs.remove(resultDTOs.size() - 1); // MISS 제거

            model.put("result", resultDTOs);
            model.put("earningsRate", result.calculateEarningsRate(payment));

            return render(model, "result.html");
        }));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static List<LottoDTO> createLottoDTOs(LottoTickets lottoTickets) {
        List<LottoDTO> lottoDTOs = new ArrayList<>();
        for (Lotto lotto : lottoTickets.getLottoTickets()) {
            lottoDTOs.add(new LottoDTO(lotto));
        }

        return lottoDTOs;
    }

    private static WinningLotto inputWinningLotto(String winningLottoNumber, String bonusNumber) {
        List<Integer> winningLottoNumbers = splitInputLottoNumbers(winningLottoNumber);
        Lotto lotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(winningLottoNumbers));
        return new WinningLotto(lotto, LottoNumber.getNumber(Integer.parseInt(bonusNumber)));
    }

    private static List<Integer> splitInputLottoNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
