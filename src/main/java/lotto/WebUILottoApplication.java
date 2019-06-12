package lotto;

import lotto.domain.LottoTicket;
import lotto.domain.NumberOfCustomLotto;
import lotto.domain.Price;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static WebUILottoData data = new WebUILottoData();

    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");
        Map<String, Object> model = new HashMap<>();

        get("/home", (req, res) -> {
            model.clear();
            return render(model, "view/lotto_home.html");
        });

        get("/rule", (req, res) -> {
            model.clear();
            return render(model, "view/lotto_rule.html");
        });

        get("/contact", (req, res) -> {
            model.clear();
            return render(model, "view/lotto_contact.html");
        });

        get("/start", (req, res) -> {
            model.clear();
            return render(model, "view/lotto_buy.html");
        });

        get("/purchase", (req, res) -> {
            try {
                Price price = new Price(req.queryParams("price"));
                data.setPrice(price);
                model.put("amount", price.getNumberOfLotto());
                return render(model, "view/lotto_manual_amount.html");
            } catch (Exception e) {
                model.put("error_price", e.getMessage());
                return render(model, "view/lotto_buy.html");
            }
        });

        get("/manual_amount", (req, res) -> {
            try {
                NumberOfCustomLotto number = new NumberOfCustomLotto(req.queryParams("manual_amount"), data.getPrice());
                data.setNumberOfCustomLotto(number);
                model.remove("error_manual");
                model.put("manual_amount", number.getNumberOfCustomLotto());
                model.put("auto_amount", ((int) model.get("amount")) - number.getNumberOfCustomLotto());
                return render(model, "view/lotto_manual.html");
            } catch (Exception e) {
                model.put("error_amount", e.getMessage());
                return render(model, "view/lotto_manual_amount.html");
            }
        });

        post("/numbers", (req,res) -> {
            List<String> manualLottos = new ArrayList<>();
            try {
                for(int i = 0 ; i < (int) model.get("manual_amount") ; i++){
                    manualLottos.add(req.queryParams("manual_lotto" + i));
                }
                LottoTicket lottoTicket = new LottoTicket(data.getPrice(), manualLottos);
                for(int i = 0 ; i < lottoTicket.getLottos().size() ; i++){

                }
                data.setLottos(lottoTicket.getLottos());
                model.put("lottos",data.getLottos());
                return render(model, "view/lotto_winning.html");
            } catch (Exception e){
                model.put("error_manual", e.getMessage());
                return render(model, "view/lotto_manual_error.html");
            }
        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
