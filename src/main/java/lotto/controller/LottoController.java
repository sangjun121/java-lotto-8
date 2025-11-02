package lotto.controller;

import java.math.BigDecimal;
import java.util.List;
import lotto.controller.dto.WinningStatistic;
import lotto.domain.vo.BonusNumber;
import lotto.domain.Lottos;
import lotto.domain.vo.PurchaseAmount;
import lotto.domain.vo.WinningNumber;
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
        Lottos lottos = generateLottos(purchaseAmount);

        WinningNumber winningNumber = readWinningNumber();
        BonusNumber bonusNumber = readBonusNumber(winningNumber);

        List<WinningStatistic> winningStatistics = lottoService.calculateWinningStatistic(lottos, winningNumber,
                bonusNumber);
        BigDecimal profitRate = lottoService.calculateProfitRate(lottos, winningNumber, bonusNumber, purchaseAmount);

        printResults(winningStatistics, profitRate);
    }

    private Lottos generateLottos(PurchaseAmount purchaseAmount) {
        Lottos lottos = lottoService.generateLotto(purchaseAmount);
        outputView.printLottoCount(purchaseAmount.calculateLottoCount());
        outputView.printLottos(lottos.toString());
        return lottos;
    }

    private PurchaseAmount readPurchaseAmount() {
        try {
            int purchaseAmountInput = inputView.readPurchaseAmount();
            return PurchaseAmount.from(purchaseAmountInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readPurchaseAmount();
        }
    }

    private WinningNumber readWinningNumber() {
        try {
            List<Integer> winningNumberInput = inputView.readWinningNumber();
            return WinningNumber.from(winningNumberInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readWinningNumber();
        }
    }

    private BonusNumber readBonusNumber(WinningNumber winningNumber) {
        try {
            int bonusNumberInput = inputView.readBonusNumber();
            return BonusNumber.from(bonusNumberInput, winningNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readBonusNumber(winningNumber);
        }
    }

    private void printResults(List<WinningStatistic> winningStatistics, BigDecimal profitRate) {
        outputView.printWinningStatistics(winningStatistics);
        outputView.printProfitRate(profitRate);
    }
}
