package lotto.Controller;

import lotto.dto.WinningLottoDTO;
import lotto.dto.WinningResultDTO;
import lotto.service.RoundService;
import lotto.service.WinningLottoService;
import spark.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class WinningLottoController {

    private static final WinningLottoService winningLottoService = new WinningLottoService();
    private static final RoundService roundService = new RoundService();

    public static final Route CREATE_WINNING_LOTTO = (request, response) -> {
        WinningLottoDTO.Create winningLottoDto = winningLottoService.createWinningLotto(
                request.queryParams("winningLottoNumbers"),
                request.queryParams("bonusNumber"));
        List<Integer> rounds = roundService.findAllRounds();

        Map<String, Object> model = new HashMap<>();
        model.put("winningLottoDto", winningLottoDto);
        model.put("rounds", rounds);
        return render(model, "winningLotto.html");
    };

    public static final Route GET_RESULT = (request, response) -> {
        int round = Integer.parseInt(request.queryParams("round"));
        WinningResultDTO.Create result = winningLottoService.calculateResult(round);
        Map<String, Object> model = new HashMap<>();
        model.put("result", result);
        return render(model, "result.html");
    };
}
