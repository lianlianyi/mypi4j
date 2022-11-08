package cc.chenwenxi.mypi4j.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TimeLineNet {
    private Date period;
    private String ccy;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
}