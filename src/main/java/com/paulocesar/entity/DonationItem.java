package com.paulocesar.entity;

import com.paulocesar.entity.pk.DonationItemPK;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "tb_donation_item")
public class DonationItem {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @EmbeddedId
    private DonationItemPK id = new DonationItemPK();
    private Integer quantity;

    public DonationItem(Donation donation, Item item, Integer quantity){
        id.setDonation(donation);
        id.setItem(item);
        this.quantity = quantity;
    }

    public Donation getDonation(){
        return id.getDonation();
    }

    public void setDonation(Donation donation){
        id.setDonation(donation);
    }

    public Item getItem(){
        return id.getItem();
    }

    public void setItem(Item item){
        id.setItem(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DonationItem that = (DonationItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
