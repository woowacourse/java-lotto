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

                ManualPurchaseNumber manualPurchaseNumber = createManualPaymentNumber(payment);

                List<Lotto> totalLottos = createTotalPurchasedLottos(payment, manualPurchaseNumber);

                OutputView.printPurchaseHistory(payment, manualPurchaseNumber);
                OutputView.printPurchaseLottos(totalLottos);

                WinStats winStats = createWinStats(totalLottos);
                OutputView.printWinStats(winStats);

                Yield yield = YieldCreator.create(payment, winStats);
                OutputView.printYield(yield);
        }

        private static List<Lotto> createTotalPurchasedLottos(Payment payment, ManualPurchaseNumber manualPurchaseNumber) {
                List<Lotto> manualPaymentLottos = createManualPaymentLottos(manualPurchaseNumber);
                List<Lotto> autoPurchasedLottos = createPurchasedLottos(payment, manualPurchaseNumber);
                List<Lotto> totalLottos = new ArrayList<>(manualPaymentLottos);
                totalLottos.addAll(autoPurchasedLottos);
                return totalLottos;
        }

        private static Payment createPayment() {
                try {
                        return PaymentCreator.create(InputView.inputPayment());
                } catch (RuntimeException e) {
                        System.err.println(e.getMessage());
                        return createPayment();
                }
        }

        private static ManualPurchaseNumber createManualPaymentNumber(Payment payment) {
                try {
                        return ManualPurchaseNumberCreator.create(InputView.inputManualPaymentNumber(), payment);
                } catch (RuntimeException e) {
                        System.err.println(e.getMessage());
                        return createManualPaymentNumber(payment);
                }
        }

        private static List<Lotto> createManualPaymentLottos(ManualPurchaseNumber manualPurchaseNumber) {
                try {
                        List<Lotto> manualPaymentLottos = new ArrayList<>();
                        List<String[]> inputs = InputView.inputManualPaymentLottosNumber(manualPurchaseNumber);
                        for (String[] input : inputs) {
                                manualPaymentLottos.add(LottoCreator.create(input));
                        }
                        return manualPaymentLottos;
                } catch (RuntimeException e) {
                        System.err.println(e.getMessage());
                        return createManualPaymentLottos(manualPurchaseNumber);
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


        private static List<Lotto> createPurchasedLottos(Payment payment, ManualPurchaseNumber manualPurchaseNumber) {
                try {
                        List<Lotto> purchasedLottos = new ArrayList<>();
                        addPurchasedLotto(payment, manualPurchaseNumber, purchasedLottos);
                        return purchasedLottos;
                } catch (RuntimeException e) {
                        System.err.println(e.getMessage());
                        return createPurchasedLottos(payment, manualPurchaseNumber);
                }
        }

        private static void addPurchasedLotto(Payment payment, ManualPurchaseNumber manualPurchaseNumber, List<Lotto> purchasedLottos) {
                List<Integer> autoCreatedLottoNumbers;
                int paymentNumber = payment.getAmount() / LOTTO_PRICE;
                for (int i = 0; i < paymentNumber - manualPurchaseNumber.getNumber(); i++) {
                        autoCreatedLottoNumbers = AutoLottoNumbersCreator.create();
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
