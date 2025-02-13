package configure;

import controller.LottoController;
import service.IssueLottoService;
import service.OpenLottoService;

public class Configure {
    private static LottoController lottoController;
    private static IssueLottoService issueLottoService;
    private static OpenLottoService openLottoService;

    public IssueLottoService issueLottoService() {
        if (issueLottoService == null) {
            return new IssueLottoService();
        }
        return issueLottoService;
    }

    public OpenLottoService openLottoService() {
        if (openLottoService == null) {
            return new OpenLottoService();
        }
        return openLottoService;
    }

    public LottoController lottoController() {
        if (lottoController == null) {
            return new LottoController(issueLottoService(), openLottoService());
        }
        return lottoController;
    }
}
