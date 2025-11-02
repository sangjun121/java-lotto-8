package lotto.controller;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningNumber;
import lotto.view.InputView;

public class LottoController {
    private final InputView inputView;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        PurchaseAmount purchaseAmount = readPurchaseAmount();
        WinningNumber winningNumber = readWinningNumber();
        BonusNumber bonusNumber = readBonusNumber(winningNumber);
    }

    private PurchaseAmount readPurchaseAmount() {
        try {
            int purchaseAmountInput = inputView.readPurchaseAmount();
            return new PurchaseAmount(purchaseAmountInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readPurchaseAmount();
        }
    }

    private WinningNumber readWinningNumber() {
        try {
            List<Integer> winningNumberInput = inputView.readWinningNumber();
            return new WinningNumber(winningNumberInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readWinningNumber();
        }
    }

    private BonusNumber readBonusNumber(WinningNumber winningNumber) {
        try {
            int bonusNumberInput = inputView.readBonusNumber();
            return BonusNumber.of(bonusNumberInput, winningNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readBonusNumber(winningNumber);
        }
    }
}
