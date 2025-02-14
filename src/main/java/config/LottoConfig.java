package config;

import controller.Controller;

public class LottoConfig {

    public Controller controller() {
        return Controller.getInstance();
    }
}
