package lotto.controller;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningNumber;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        PurchaseAmount purchaseAmount = readPurchaseAmount();
        Lottos lottos = lottoService.generateLotto(purchaseAmount);
        outputView.printLottoCount(purchaseAmount.calculateLottoCount());
        outputView.printLottos(lottos.toString());

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
