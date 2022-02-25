import controller.InputController;
import controller.MachineController;
import controller.OutputController;
import model.lottonumbergenerator.LottoNumberGenerator;

public class LottoMain {

    public static void main(String[] args) {
        final MachineController machineController = new MachineController(new LottoNumberGenerator(),
                new InputController(), new OutputController());
        machineController.runMachine();
    }
}

