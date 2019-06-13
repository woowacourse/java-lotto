package lotto.service;

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

public class LottoService {

    public static Route makeSelfLottoPage = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        Price price = new Price(Integer.parseInt(req.queryParams("price")));
        LottosFactory lottosFactory = new LottosFactory(price, Integer.parseInt(req.queryParams("selfCount")));
        model.put("countOfSelf", lottosFactory.getCountOfSelf());
        req.session().attribute("price", price);
        req.session().attribute("lottosFactory", lottosFactory);
        List<Integer> numbers = IntStream.rangeClosed(1, 45).boxed().collect(Collectors.toList());
        model.put("numbers", numbers);
        req.session().attribute("numbers", numbers);
        model.put("message", req.session().attribute("message"));
        model.put("countOfSelf", req.queryParams("selfCount"));
        return ViewUtils.render(model, "lotto.html");
    };


    public static Route makeUserLottosPage = (req,res) -> {
        Map<String, Object> model = new HashMap<>();
        List<String> selfInput = Arrays.asList(req.queryParams("selfLottos").split("\r\n"));
        LottosFactory lottosFactory = req.session().attribute("lottosFactory");
        Lottos lottos = lottosFactory.generateLottos(selfInput, req.session().attribute("price"));
        model.put("lottos", lottos.getLottos());
        req.session().attribute("lottos", lottos);

        return ViewUtils.render(model, "lottos.html");
    };

}
