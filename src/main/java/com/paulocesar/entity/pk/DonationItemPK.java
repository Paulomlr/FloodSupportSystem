package com.paulocesar.entity.pk;

import com.paulocesar.entity.Donation;
import com.paulocesar.entity.Item;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@ToString(exclude = {"donation"})
@EqualsAndHashCode
@Embeddable
public class DonationItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_donation")
    private Donation donation;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;
}
