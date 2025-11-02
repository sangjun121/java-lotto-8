package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.config.AppConfig;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoServiceTest {
    private LottoService lottoService;

    @BeforeEach
    void setUp() {
        this.lottoService = AppConfig.getInstance().lottoService();
    }

    @Test
    void 당첨금액을_바탕으로_올바른_개수의_로또를_생성한다() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(10000);

        Lottos lottos = lottoService.generateLotto(purchaseAmount);
        int resultLottosSize = lottos.getLottos().size();

        assertThat(resultLottosSize).isEqualTo(10);
    }
}
