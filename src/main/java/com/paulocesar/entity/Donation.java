package com.paulocesar.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@ToString
@Entity(name = "tb_donation")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_distribution_center")
    private DistributionCenter distributionCenter;
    private LocalDateTime sendDate;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.donation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DonationItem> items = new ArrayList<>();

    public Donation(DistributionCenter distributionCenter, LocalDateTime sendDate) {
        this.distributionCenter = distributionCenter;
        this.sendDate = sendDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donation donation = (Donation) o;
        return Objects.equals(id, donation.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
