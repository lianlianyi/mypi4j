package cc.chenwenxi.mypi4j.task;

import cc.chenwenxi.mypi4j.domain.TimeLineNet;
import cc.chenwenxi.mypi4j.service.OLedNetService;
import cc.chenwenxi.mypi4j.tool.OledKit;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OledNetJob {

    private final OLedNetService oLedNetService;

    @Scheduled(cron = "0 0/1 * * * ?")
    @SneakyThrows
    public void a() {
        List<TimeLineNet> cny = oLedNetService.netKline(1L, "cny", 10, 10);
        TimeLineNet timeLineNet = cny.get(0);
        Double close = timeLineNet.getClose();

        List<String> strings = CollUtil.newArrayList(close.toString(), DateUtil.formatDateTime(timeLineNet.getPeriod()));
        OledKit oLed = new OledKit(0, 0);
        oLed.start();
        oLed.text(strings);
    }
}
