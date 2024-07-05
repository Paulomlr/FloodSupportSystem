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
import java.util.Objects;

@Getter
@Setter
@ToString
@Embeddable
public class DonationItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_donation")
    private Donation donation;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DonationItemPK that = (DonationItemPK) o;
        return Objects.equals(donation, that.donation) && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(donation, item);
    }
}
