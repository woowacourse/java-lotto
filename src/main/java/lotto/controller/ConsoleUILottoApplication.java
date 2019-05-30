package lotto.controller;

import lotto.model.creator.*;
import lotto.model.object.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleUILottoApplication {
        private static final int LOTTO_PRICE = 1000;

        public static void main(String[] args) {
                Payment payment = createPayment();

                ManualPaymentNumber manualPaymentNumber = createManualPaymentNumber(payment);

                List<Lotto> manualPaymentLottos = createManualPaymentLottos(manualPaymentNumber);
                List<Lotto> autoPurchasedLottos = createPurchasedLottos(payment, manualPaymentNumber);
                List<Lotto> totalLottos = new ArrayList<>(manualPaymentLottos);
                totalLottos.addAll(autoPurchasedLottos);

                OutputView.printPurchaseHistory(payment, manualPaymentNumber);
                OutputView.printPurchaseLottos(totalLottos);

                WinStats winStats = createWinStats(totalLottos);
                OutputView.printWinStats(winStats);

                Yield yield = YieldCreator.create(payment, winStats);
                OutputView.printYield(yield);
        }

        private static Payment createPayment() {
                try {
                        return PaymentCreator.create(InputView.inputPayment());
                } catch (RuntimeException e) {
                        System.err.println(e.getMessage());
                        return createPayment();
                }
        }

        private static ManualPaymentNumber createManualPaymentNumber(Payment payment) {
                try {
                        return ManualPaymentNumberCreator.create(InputView.inputManualPaymentNumber(), payment);
                } catch (RuntimeException e) {
                        System.err.println(e.getMessage());
                        return createManualPaymentNumber(payment);
                }
        }

        private static List<Lotto> createManualPaymentLottos(ManualPaymentNumber manualPaymentNumber) {
                try {
                        List<Lotto> manualPaymentLottos = new ArrayList<>();
                        List<String[]> inputs = InputView.inputManualPaymentLottosNumber(manualPaymentNumber);
                        for (String[] input : inputs) {
                                manualPaymentLottos.add(LottoCreator.create(input));
                        }
                        return manualPaymentLottos;
                } catch (RuntimeException e) {
                        System.err.println(e.getMessage());
                        return createManualPaymentLottos(manualPaymentNumber);
                }
        }

        private static WinStats createWinStats(List<Lotto> purchasedLottos) {
                try {
                        Lotto winningLotto = createWinningLotto();
                        BonusBall bonusBall = createBonusBall();
                        WinningInfo winningInfo = WinningInfoCreator.create(winningLotto, bonusBall);
                        return WinStatsCreator.create(purchasedLottos, winningInfo);
                } catch (RuntimeException e) {
                        System.err.println(e.getMessage());
                        return createWinStats(purchasedLottos);
                }
        }


        private static List<Lotto> createPurchasedLottos(Payment payment, ManualPaymentNumber manualPaymentNumber) {
                try {
                        List<Lotto> purchasedLottos = new ArrayList<>();
                        addPurchasedLotto(payment, manualPaymentNumber, purchasedLottos);
                        return purchasedLottos;
                } catch (RuntimeException e) {
                        System.err.println(e.getMessage());
                        return createPurchasedLottos(payment, manualPaymentNumber);
                }
        }

        private static void addPurchasedLotto(Payment payment, ManualPaymentNumber manualPaymentNumber, List<Lotto> purchasedLottos) {
                List<Integer> autoCreatedLottoNumbers;
                int paymentNumber = payment.getNumber() / LOTTO_PRICE;
                for (int i = 0; i < paymentNumber - manualPaymentNumber.getNumber(); i++) {
                        autoCreatedLottoNumbers = AutoLottoNumberCreator.create();
                        purchasedLottos.add(AutoPurchasedLottoCreator.create(autoCreatedLottoNumbers));
                }
        }

        private static Lotto createWinningLotto() {
                try {
                        return LottoCreator.create(InputView.inputWinningLottoNumber());
                } catch (RuntimeException e) {
                        System.err.println(e.getMessage());
                        return createWinningLotto();
                }
        }

        private static BonusBall createBonusBall() {
                try {
                        return BonusBallCreator.create(InputView.inputBonusBall());
                } catch (RuntimeException e) {
                        System.err.println(e.getMessage());
                        return createBonusBall();
                }
        }
}
