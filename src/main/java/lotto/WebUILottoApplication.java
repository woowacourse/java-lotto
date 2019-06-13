package lotto;

import lotto.domain.dao.LottoDAO;
import lotto.domain.dao.MoneyDAO;
import lotto.domain.dao.WinningLottoDAO;
import lotto.domain.dto.MoneyDTO;
import lotto.domain.model.Lotto;
import lotto.domain.model.Money;
import lotto.domain.model.NumberSet;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "home.html");
        });

        get("/round", (req, res) -> {
            WinningLottoDAO winningLottoDAO = new WinningLottoDAO();
            return winningLottoDAO.getLatestRound();
        });

        // 구입 금액
        get("/money", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            MoneyDTO moneyDTO = new MoneyDTO();
            MoneyDAO moneyDAO = new MoneyDAO();
            Money money = new Money(Integer.parseInt(req.queryParams("money")));
            moneyDTO.setMoney(money.getMoney());
            moneyDTO.setAvailableCount(money.availablePurchaseCount());
            moneyDTO.setRound(Integer.parseInt(req.queryParams("round")));
            moneyDAO.addMoney(moneyDTO);
            return money.getMoney();
        });

        post("/manualLotto", (req, res) -> {
            LottoDAO lottoDao = new LottoDAO();
            int round = Integer.parseInt(req.queryParams("round_hidden"));

            Stream.of(req.queryParams("manualLottos").split("/"))
                    .map(lottoNumbers ->
                        Stream.of(lottoNumbers.split(","))
                                .map(number ->
                            NumberSet.of(Integer.parseInt(number))
                        ).collect(Collectors.toList())
                    ).forEach(lotto ->
                        lottoDao.addLotto(new Lotto(lotto), round)
                    );

            Map<String, Object> model = new HashMap<>();

            return 0;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
