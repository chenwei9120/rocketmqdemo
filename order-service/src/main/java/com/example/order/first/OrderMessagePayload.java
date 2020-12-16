package com.example.order.first;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author chenwei
 * @version 1.0.0
 * @ClassName OrderMessagePayload
 * @Description TODO
 * @createTime 2020-12-09 11:10:00
 */
@Data
public class OrderMessagePayload implements Serializable {

    /**
     * 账户id
     * */
    private Long accountId;

    /**
     * 金额
     *
     */
    private BigDecimal orderMoney;

    /**
     * 类型（app充值：0,卡充值：1，提现：2，卡充电冻结：3.  解冻返还 4, app充电冻结 5,充电结束补扣 6,在线退款冻结 7,人工退款冻结 8,在线退款失败冻结返还 9 人工退款失败冻结返回 10,在线退款成功 11，人工退款成功 12，充电消费 13）
     *
     */
    private Integer type;

    /**
     * orderid
     *
     */
    private Long orderId;

    /**
     * 订单号
     *
     */
    private String orderSn;
}
