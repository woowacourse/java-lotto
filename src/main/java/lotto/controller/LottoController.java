package lotto.controller;

import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.Price;
import lotto.domain.generate.LottosFactory;
import lotto.utils.ViewUtils;
import spark.Route;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoController {
    private static final String SELF_LOTTO_COUNT = "selfCount";
    private static final String LOTTOS_FACTORY = "lottosFactory";
    private static final String ERROR_MESSAGE = "message";
    private static final String SELF_LOTTOS = "selfLottos";
    private static final String LOTTOS = "lottos";
    private static final String DELIMITER = "\r\n";
    static final String NUMBERS = "numbers";
    static final String PRICE = "price";

    public static Route makeSelfLottoPage = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        Price price = new Price(Integer.parseInt(req.queryParams(PRICE)));
        LottosFactory lottosFactory = new LottosFactory(price, Integer.parseInt(req.queryParams(SELF_LOTTO_COUNT)));
        model.put(SELF_LOTTO_COUNT, lottosFactory.getCountOfSelf());
        req.session().attribute(PRICE, price);
        req.session().attribute(LOTTOS_FACTORY, lottosFactory);
        List<Integer> numbers = IntStream.rangeClosed(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER).boxed().collect(Collectors.toList());
        model.put(NUMBERS, numbers);
        req.session().attribute(NUMBERS, numbers);
        model.put(ERROR_MESSAGE, req.session().attribute(ERROR_MESSAGE));
        return ViewUtils.render(model, "lotto.html");
    };


    public static Route makeUserLottosPage = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        List<String> selfInput = Arrays.asList(req.queryParams(SELF_LOTTOS).split(DELIMITER));
        LottosFactory lottosFactory = req.session().attribute(LOTTOS_FACTORY);
        Lottos lottos = lottosFactory.generateLottos(selfInput, req.session().attribute(PRICE));
        model.put(LOTTOS, lottos.getLottos());
        req.session().attribute(LOTTOS, lottos);

        return ViewUtils.render(model, "lottos.html");
    };

}
