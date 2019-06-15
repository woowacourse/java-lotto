package lottoGame.numNonRandom;

import lottoGame.util.Path;
import lottoGame.util.ViewName;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class numNonRandomController {
    public static Route inputNumNonRandom = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("numTotalLottos", req.queryParams("numTotalLottos"));

        return new HandlebarsTemplateEngine().render(new ModelAndView(model, ViewName.Handlebars.NUM_NON_RANDOM));
    };

    public static Route handleNumNonRandomPost = (req, res) -> {
        int numTotalLottos = Integer.parseInt(req.queryParams("numTotalLottos"));
        int numNonRandom = Integer.parseInt(req.queryParams("numNonRandom"));

        res.redirect(Path.Web.NON_RANDOM_LOTTOS + "?numTotalLottos=" + numTotalLottos + "&numNonRandom=" + numNonRandom);

        return null;
    };
}
