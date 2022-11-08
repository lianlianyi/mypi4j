package cc.chenwenxi.mypi4j.service;

import cc.chenwenxi.mypi4j.domain.TimeLineNet;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OLedNetService {

    @SneakyThrows
    public List<TimeLineNet> netKline(Long apiId, String ccy, Integer minute, Integer limit){
        String template = """
                        SELECT time_bucket('{} minutes', time) AS period,
                               ccy,
                               first(net, time) AS open, last(net, time) AS close,
                               max(net) AS high, min(net) AS low
                        FROM ouyi_net
                        where api_id = {} and ccy = '{}'
                        GROUP BY period, ccy
                        ORDER BY period DESC, ccy limit {};
                """;
        String sql = StrUtil.format(template, minute, apiId, ccy, limit);
        return Db.use().query(sql, TimeLineNet.class);
    }
}
