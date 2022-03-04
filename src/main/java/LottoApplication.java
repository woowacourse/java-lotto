import controller.LottoController;
import domain.service.impl.AutoLottoServiceImpl;
import domain.service.impl.ManualLottoServiceImpl;

public class LottoApplication {
    public static void main(String[] args) {
        new LottoController(new ManualLottoServiceImpl(), new AutoLottoServiceImpl()).start();
    }
}
