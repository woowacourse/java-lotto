package lotto;

import lotto.domain.controller.HomeController;
import lotto.domain.controller.SaveGameController;
import lotto.domain.controller.ShowGameController;
import lotto.domain.controller.StartGameController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.get;

public class WebUILottoApplication {

    public static void main(String[] args) {
        HomeController homeController = HomeController.getInstance();
        get(homeController.HOME_URL, (req, res) -> homeController.get(req, res));

        StartGameController startGameController = StartGameController.getInstance();
        get(startGameController.START_GAME_URL, (req, res) -> startGameController.get(req));

        SaveGameController saveGameController = SaveGameController.getInstance();
        get(saveGameController.SAVE_GAME_URL, (req, res) -> saveGameController.get(req));

        ShowGameController showGameController = ShowGameController.getInstance();
        get(showGameController.SHOW_GAME_URL, (req, res) -> showGameController.get(req));
    }
    
    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
