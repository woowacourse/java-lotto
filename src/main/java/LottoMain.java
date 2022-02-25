import controller.InputController;
import controller.machineController;
import controller.OutputController;
import model.LottoNumberGenerator.LottoNumberGenerateStrategy;

public class LottoMain {

    public static void main(String[] args) {
        final machineController machineController = new machineController(new LottoNumberGenerateStrategy(),
                new InputController(), new OutputController());
        machineController.runMachine();
    }
}

