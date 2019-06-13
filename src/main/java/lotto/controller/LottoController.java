package lotto.controller;

import lotto.domain.Lotto;
import lotto.service.LottoService;
import lotto.util.LottoParser;
import org.apache.commons.lang3.StringUtils;
import spark.Request;

import java.util.*;
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
        List<Lotto> lottos = makeLottos(req);
        int manualCount = lottoService.assignManualCount(lottos);
        int autoCount = lottoService.assignAutoPurchaseCount();

        model.put("manualCount", manualCount);
        model.put("autoCount", autoCount);
        model.put("lottos", lottoService.getLottos());
        return model;
    }

    private List<Lotto> makeLottos(Request req) {
        String origin = req.queryParams("numbers");
        List<String> numbers = splitSentences(origin);
        return parseLottos(numbers);
    }

    private List<String> splitSentences(final String origin) {
        if (StringUtils.isBlank(origin)) {
            return new ArrayList<>();
        }
        return Arrays.asList(origin.split(SENTENCE_DELIMITER));
    }

    private List<Lotto> parseLottos(List<String> numbers) {
        return numbers.stream()
                .map(str -> parseLotto(str))
                .collect(Collectors.toList());
    }

    private Lotto parseLotto(final String lotto) {
        return new LottoParser().parseLotto(lotto);
    }
}
