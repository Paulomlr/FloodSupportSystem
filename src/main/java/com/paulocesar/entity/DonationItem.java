package com.paulocesar.entity;

import com.paulocesar.entity.pk.DonationItemPK;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationItem {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
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
}