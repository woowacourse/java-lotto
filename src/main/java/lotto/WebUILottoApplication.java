package lotto;

import lotto.domain.CountOfLotto;
import lotto.domain.Payment;
import lotto.domain.Rank;
import lotto.domain.Result;
import lotto.domain.lotto.*;
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
    private static WinningLotto winningLotto;

    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("lottoPrice", Payment.LOTTO_PRICE);
            model.put("paymentMessage", InputView.PAYMENT_MESSAGE);
            model.put("countOfManualLottoMessage", InputView.COUNT_OF_MANUAL_LOTTO_MESSAGE);

            return render(model, "index.html");
        });

        post("/inputStep1.html", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("lottoNumberMessage", InputView.LOTTO_NUMBER_MESSAGE);
            model.put("lottoNumberMinBoundary", LottoNumber.MIN_BOUNDARY);
            model.put("lottoNumberMaxBoundary", LottoNumber.MAX_BOUNDARY);
            model.put("lottoNumberSize", Lotto.LOTTO_NUMBER_SIZE);

            payment = new Payment(req.queryParams("payment"));
            countOfLotto = new CountOfLotto(payment, req.queryParams("countOfManualLotto"));
            model.put("countOfLotto", countOfLotto);
            model.put("countOfTotalLotto", payment.calculateCountOfLotto());

            return render(model, "inputStep1.html");
        });

        post("/inputStep2.html", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("winningLottoNumberMessage", InputView.WINNING_LOTTO_NUMBER_MESSAGE);
            model.put("bonusBallMessage", InputView.BONUS_BALL_MESSAGE);

            LottoRepository lottoRepository = new LottoRepository();
            int countOfManualLotto = countOfLotto.getCountOfManualLotto();
            int countOfRandomLotto = countOfLotto.getCountOfRandomLotto();

            for (int i = 1; i <= countOfManualLotto; i++) {
                List<Integer> splitLottoNumbers = splitInputLottoNumbers(req.queryParams("inputManualLottoNumber1"));
                lottoRepository.register(new ManualLottoGeneratingStrategy(splitLottoNumbers));
            }

            for (int i = 1; i <= countOfRandomLotto; i++) {
                lottoRepository.register(new RandomLottoGeneratingStrategy());
            }
            lottoTickets = lottoRepository.createLottoTickets();

            return render(model, "inputStep2.html");
        });

        post("result.html", ((req, res) -> {
            Map<String, Object> model = new HashMap<>();
            winningLotto = inputWinningLotto(req.queryParams("winningLottoNumber"), req.queryParams("bonusNumber"));
            Result result = lottoTickets.match(winningLotto);

            List<LottoDto> lottoDTOs = createLottoDTOs(lottoTickets);
            model.put("lottos", lottoDTOs);

            model.put("rankFirst", Rank.FIRST);
            model.put("rankSecond", Rank.SECOND);
            model.put("rankThird", Rank.THIRD);
            model.put("rankFourth", Rank.FOURTH);
            model.put("rankFifth", Rank.FIFTH);
            model.put("countOfRankFirst", result.get(Rank.FIRST));
            model.put("countOfRankSecond", result.get(Rank.SECOND));
            model.put("countOfRankThird", result.get(Rank.THIRD));
            model.put("countOfRankFourth", result.get(Rank.FOURTH));
            model.put("countOfRankFifth", result.get(Rank.FIFTH));
            model.put("earningsRate", result.calculateEarningsRate(payment));

            return render(model, "result.html");
        }));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static List<LottoDto> createLottoDTOs(LottoTickets lottoTickets) {
        List<LottoDto> lottoDTOs = new ArrayList<>();
        for (Lotto lotto : lottoTickets.getLottoTickets()) {
            LottoDto lottoDto = new LottoDto();
            lottoDto.setLotto(lotto.toString());
            lottoDTOs.add(lottoDto);
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
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
