package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoService;
import lotto.util.LottoParser;
import spark.Request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {
    private static final String SENTENCE_DELIMITER = "\\n";
    private static final LottoController INSTANCE = new LottoController();

    private final LottoService lottoService;

    private LottoController() {
        lottoService = LottoService.getInstance();
    }

    public static LottoController getInstance() {
        return INSTANCE;
    }

    public Map<String, Object> processLottoShopping(final Request req) {
        Map<String, Object> model = new HashMap<>();
        int money = Integer.parseInt(req.queryParams("money"));
        lottoService.charge(money);
        return model;
    }

    public Map<String, Object> processLottos(final Request req) {
        Map<String, Object> model = new HashMap<>();

        String origin = req.queryParams("numbers");
        List<String> numbers = splitSentences(origin);      //controller의 일 ok
        List<Lotto> lottos = numbers.stream()               //컨트롤러의 일 ok
                .map(lotto -> parseLotto(lotto))
                .collect(Collectors.toList());

        int manualCount = lottoService.assignManualCount(lottos);
        int autoCount = lottoService.assignAutoPurchaseCount();

        model.put("manualCount", manualCount);
        model.put("autoCount", autoCount);

        model.put("lottos", lottoService.getLottos());
        return model;
    }

    private List<String> splitSentences(String origin) {
        return Arrays.asList(origin.split(SENTENCE_DELIMITER));
    }

    private Lotto parseLotto(String winninglotto) {
        return new LottoParser().parseLotto(winninglotto);
    }
}
