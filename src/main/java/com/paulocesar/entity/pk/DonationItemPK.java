package com.paulocesar.entity.pk;

import com.paulocesar.entity.Donation;
import com.paulocesar.entity.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DonationItemPK {
    private Donation donation;
    private Item item;
}
