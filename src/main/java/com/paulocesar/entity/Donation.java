package com.paulocesar.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Donation {

    private Integer id;
    private DistributionCenter distributionCenter;
    private LocalDateTime sendDate;

    public Donation(DistributionCenter distributionCenter, LocalDateTime sendDate) {
        this.distributionCenter = distributionCenter;
        this.sendDate = sendDate;
    }
}
