package lotto;

import lotto.controller.Controller;
import verus.exception.ApplicationFinishedException;

public class Application {

    public static void main(String[] args) {
        try {
            new Controller(System.in, System.out).run();
        } catch (ApplicationFinishedException e) {
            System.out.println("종료되었습니다!");
        }
    }
}
