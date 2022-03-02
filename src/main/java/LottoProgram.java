import controller.InputController;
import controller.LottoController;

public class LottoProgram {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new InputController());
        lottoController.simulateLotto();
    }
}
