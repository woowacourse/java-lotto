package lotto.controller;

import lotto.domain.lotto.LottoCount;
import lotto.domain.lotto.dto.LottoCountDTO;
import lotto.domain.lotto.dto.LottoDTO;
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
            moneyDTO.set(req.queryParams("money"));
            manualLottoCountDTO.set(req.queryParams("manualLottoCount"));

            Money money = Money.create(moneyDTO.getMoney());
            LottoCount lottoCount = new LottoCount(
                    manualLottoCountDTO.getManualLottoCount());
            List<String> manualLottoNames = new ArrayList<>();
            for (int i = 0; i < lottoCount.size(); i++) {
                manualLottoNames.add("manualLotto" + i);
            }

            Map<String, Object> model = new HashMap<>();
            model.put("manualLottoNames", manualLottoNames);

            return render(model, "manual.html");
        });

        post("/result", (req, res) -> {
            List<LottoDTO> lottoDTOs = new ArrayList<>();
            for (int i = 0; i < manualLottoCountDTO.getManualLottoCount(); i++) {
                LottoDTO lottoDTO = new LottoDTO();
                lottoDTO.set(req.queryParams("manualLotto" + i));
                lottoDTOs.add(lottoDTO);
            }

            Map<String, Object> model = new HashMap<>();
            model.put("money", req.queryParams("money"));
            model.put("manualLottoCount", req.queryParams("manualLottoCount"));
            model.put("manualLottoNumbers", req.queryParams("manualLottoNumbers"));
            return render(model, "result.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
