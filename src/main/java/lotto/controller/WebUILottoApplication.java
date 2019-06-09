package lotto.controller;

import lotto.domain.lotto.LottoCount;
import lotto.domain.lotto.LottoMachine;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.dto.LottoCountDTO;
import lotto.domain.lotto.dto.LottoNumbersDTO;
import lotto.domain.lotto.dto.MoneyDTO;
import lotto.domain.money.Money;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    public static void main(String[] args) {
        MoneyDTO moneyDTO = new MoneyDTO();
        LottoCountDTO manualLottoCountDTO = new LottoCountDTO();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/manual", (req, res) -> {
            System.out.println(req.queryParams());
            moneyDTO.set(req.queryParams("money"));
            manualLottoCountDTO.set(req.queryParams("manualLottoCount"));

            Money money = Money.create(moneyDTO.getMoney());
            LottoCount manualLottoCount = LottoCount.create(
                    manualLottoCountDTO.getManualLottoCount(), money);
            List<String> manualLottoNames = new ArrayList<>();
            for (int i = 0; i < manualLottoCount.size(); i++) {
                manualLottoNames.add("manualLotto" + i);
            }

            Map<String, Object> model = new HashMap<>();
            model.put("manualLottoNames", manualLottoNames);

            return render(model, "manual.html");
        });

        post("/lottos", (req, res) -> {
            Money money = Money.create(moneyDTO.getMoney());

            List<List<Integer>> manualLottos = new ArrayList<>();
            for (int i = 0; i < manualLottoCountDTO.getManualLottoCount(); i++) {
                LottoNumbersDTO lottoNumbersDTO = new LottoNumbersDTO();
                lottoNumbersDTO.set(req.queryParams("manualLotto" + i));
                manualLottos.add(lottoNumbersDTO.getLottoNumbers());
            }
            Lottos lottos = LottoMachine.generateLottos(manualLottos, money);
            Map<String, Object> model = new HashMap<>();
            model.put("lottos", lottos.getLottos());

            return render(model, "lottos.html");
        });


        post("/result", (req, res) -> {

            //return render(model, "result.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
