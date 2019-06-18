package lotto.controller;

import lotto.domain.Lottos;
import lotto.domain.Price;
import lotto.domain.generate.LottosFactory;
import lotto.service.LottoService;
import lotto.service.RoundService;
import lotto.utils.ViewUtils;
import spark.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoController {
    private static final String SELF_LOTTO_COUNT = "selfCount";
    private static final String LOTTOS_FACTORY = "lottosFactory";
    private static final String ERROR_MESSAGE = "message";
    private static final String SELF_LOTTOS = "selfLottos";
    private static final String LOTTOS = "lottos";
    static final String NUMBERS = "numbers";
    static final String PRICE = "price";

    public static Route makeSelfLottoPage = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        RoundService roundService = new RoundService();
        LottoService lottoService = new LottoService();

        Price price = roundService.getPrice(req.queryParams(PRICE));
        int selfCount = lottoService.getSelfCount(req.queryParams(SELF_LOTTO_COUNT));
        LottosFactory lottosFactory = lottoService.getLottosFactory(price,selfCount);
        model.put(SELF_LOTTO_COUNT, selfCount);
        req.session().attribute(PRICE, price);
        req.session().attribute(LOTTOS_FACTORY, lottosFactory);

        List<Integer> numbers = lottoService.getNumbers();
        model.put(NUMBERS, numbers);
        req.session().attribute(NUMBERS, numbers);

        model.put(ERROR_MESSAGE, req.session().attribute(ERROR_MESSAGE));

        return ViewUtils.render(model, "lotto.html");
    };

    public static Route makeUserLottosPage = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        LottoService lottoService = new LottoService();

        LottosFactory lottosFactory = req.session().attribute(LOTTOS_FACTORY);
        Lottos lottos = lottosFactory.generateLottos(lottoService.splitSelfInputs(req.queryParams(SELF_LOTTOS)), req.session().attribute(PRICE));
        model.put(LOTTOS, lottos.getLottos());
        req.session().attribute(LOTTOS, lottos);

        return ViewUtils.render(model, "lottos.html");
    };

}
