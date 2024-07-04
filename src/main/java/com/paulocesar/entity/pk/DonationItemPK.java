package com.paulocesar.entity.pk;

import com.paulocesar.entity.Donation;
import com.paulocesar.entity.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@ToString
@Embeddable
public class DonationItemPK {

    @ManyToOne
    @JoinColumn(name = "id_donation")
    private Donation donation;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;
}
