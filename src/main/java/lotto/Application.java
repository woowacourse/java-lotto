package lotto;

import lotto.common.config.DependencyConfig;
import lotto.controller.Controller;

public class Application {
    public static void main(String[] args) {
        DependencyConfig dependencyConfig = new DependencyConfig();
        
        Controller controller = dependencyConfig.getController();
        controller.run();
    }
}