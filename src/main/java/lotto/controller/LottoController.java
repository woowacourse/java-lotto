package lotto.controller;

import lotto.service.dto.ResultDto;
import lotto.domain.lotto.*;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
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
    private static final PaymentInfoService PAYMENT_INFO_SERVICE = PaymentInfoService.getInstance();
    private static final LottoService LOTTO_SERVICE = LottoService.getInstance();
    private static final LottoResultService LOTTO_RESULT_SERVICE = LottoResultService.getInstance();

    private LottoController() {
        throw new AssertionError();
    }

    public static String goSelectLotto(Request request, Response response) throws SQLDataException {
        int payment = Integer.parseInt(nullable(request.queryParams("payment")));
        int countOfManualLotto = Integer.parseInt(nullable(request.queryParams("countOfManualLotto")));
        String name = nullable(request.queryParams("name"));

        int round = PAYMENT_INFO_SERVICE.insertPaymentInfoAndReturnKeyValue(payment, countOfManualLotto, name);

        List<String> manualLottoInputTag = LOTTO_SERVICE.createResponseInputTag(countOfManualLotto);

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

        LottoRepository lottoRepository = LOTTO_SERVICE
                .addLottos(request.queryParamsValues("lotto_number"), countOfLotto);

        int insertLottoTicket = LOTTO_SERVICE.insertLottoTicket(lottoRepository, round);

        WinningLotto winningLotto = createWinningLotto(
                nullable(request.queryParams("winning_lotto")),
                nullable(request.queryParams("bonus_ball")));

        int insertWinningLotto = LOTTO_SERVICE.insertWinningLotto(winningLotto, round);

        int insertResult = LOTTO_RESULT_SERVICE
                .insertLottoResult(createResultDto(winningLotto, lottoRepository, round, name));

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

    private static ResultDto createResultDto(WinningLotto winningLotto, LottoRepository lottoRepository, int round, String name) {
        Result result = winningLotto.match(new LottoTickets(lottoRepository));
        ResultDto resultDto = new ResultDto.Builder(round, name)
                .first(result.get(Rank.FIRST))
                .second(result.get(Rank.SECOND))
                .third(result.get(Rank.THIRD))
                .fourth(result.get(Rank.FOURTH))
                .fifth(result.get(Rank.FIFTH))
                .miss(result.get(Rank.MISS))
                .build();
        resultDto.setTotalWinningMoney(result.calculateTotalWinningMoney());
        return resultDto;
    }

}
