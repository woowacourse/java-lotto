package lotto.controller;

import lotto.domain.dto.ResultDto;
import lotto.domain.lotto.*;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.domain.paymentinfo.CountOfLotto;
import lotto.domain.paymentinfo.Payment;
import lotto.service.LottoResultService;
import lotto.service.LottoService;
import lotto.service.PaymentInfoService;
import spark.Request;
import spark.Response;

import java.sql.SQLDataException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static lotto.controller.common.CommonController.nullable;
import static lotto.controller.common.CommonController.render;

public class LottoController {
    private LottoController() {
        throw new AssertionError();
    }

    public static String goSelectLotto(Request request, Response response) throws SQLDataException {
        Payment payment = new Payment(Integer.parseInt(nullable(request.queryParams("payment"))));
        int countOfManualLotto = Integer.parseInt(nullable(request.queryParams("countOfManualLotto")));
        CountOfLotto countOfLotto = new CountOfLotto(payment, countOfManualLotto);

        String name = nullable(request.queryParams("name"));

        int round = PaymentInfoService.getInstance()
                .insertPaymentInfoAndReturnKeyValue(payment, countOfLotto, name);

        List<String> manualLottoInputTag = LottoService.getInstance().createResponseInputTag(countOfManualLotto);

        Map<String, Object> model = new HashMap<>();
        model.put("lottoName", manualLottoInputTag);
        model.put("name", name);
        model.put("countOfLotto", countOfLotto.getTotalCountOfLotto());
        model.put("round", round);
        return render(model, "lottoNumbers.html");
    }

    public static String insertAndGoResult(Request request, Response response) throws SQLDataException {
        int countOfLotto = Integer.parseInt(nullable(request.queryParams("countOfLotto")));
        int round = Integer.parseInt(nullable(request.queryParams("round")));
        String name = nullable(request.queryParams("name"));

        LottoRepository lottoRepository = LottoService.getInstance().addLottos(request.queryParamsValues("lotto_number"), countOfLotto);

        int insertLottoTicket = LottoService.getInstance().insertLottoTicket(lottoRepository, round);

        WinningLotto winningLotto = createWinningLotto(
                nullable(request.queryParams("winning_lotto")),
                nullable(request.queryParams("bonus_ball")));

        int insertWinningLotto = LottoService.getInstance().insertWinningLotto(winningLotto, round);

        int insertResult = LottoResultService.getInstance()
                .insertLottoResult(createResultDTO(winningLotto, lottoRepository, round, name));

        response.redirect("/result/" + round);

        // TODO How to process return?
        return render(null, "lottoResult.html");
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

    private static ResultDto createResultDTO(WinningLotto winningLotto, LottoRepository lottoRepository, int round, String name) {
        Result result = winningLotto.match(new LottoTickets(lottoRepository));
        ResultDto resultDTO = new ResultDto.Builder(round, name)
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

}
