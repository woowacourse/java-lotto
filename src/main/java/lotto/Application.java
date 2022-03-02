package lotto;

import java.util.Scanner;
import lotto.controller.Controller;

public class Application {

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run();
    }
}
