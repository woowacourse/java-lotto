import controller.MachineController;
import model.lottonumbergenerator.LottoNumberGenerator;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        final MachineController machineController = new MachineController(new LottoNumberGenerator(), new InputView(),
                new OutputView());
        machineController.runMachine();
    }
}

