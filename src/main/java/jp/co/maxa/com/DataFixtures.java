package jp.co.maxa.com;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import jp.co.maxa.com.context.DefaultRepository;
import jp.co.maxa.com.model.master.ProjectMaster;
import lombok.Setter;

/**
 * データ生成用のサポートコンポーネント。
 * <p>テストや開発時の簡易マスタデータ生成を目的としているため本番での利用は想定していません。
 */
@Component
@ConditionalOnProperty(prefix = "extension.datafixture", name = "enabled", matchIfMissing = false)
@Setter
public class DataFixtures {

    @Autowired
    private DefaultRepository rep;
    @Autowired
    @Qualifier(DefaultRepository.BEAN_TRANSACTION_MANAGER)
    private PlatformTransactionManager tx;

    @PostConstruct
    public void initialize() {
        new TransactionTemplate(tx).execute((status) -> {
            initializeInTx();
            return true;
        });
    }

    public void initializeInTx() {
    	projectMaster("グミ", "100円", "ゼラチンの塊").save(rep);
    	projectMaster("チョコ", "200円", "カカオの塊").save(rep);
    	projectMaster("タバコ", "460円", "ヤニ棒").save(rep);
    }

    // master domain
    public ProjectMaster projectMaster(String name, String budget, String description) {
    	ProjectMaster m = new ProjectMaster();
        m.setName(name);
        m.setBudget(budget);
        m.setDescription(description);
        return m;
    }
}
