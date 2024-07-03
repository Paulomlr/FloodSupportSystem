package com.paulocesar.entity.pk;

import com.paulocesar.entity.Donation;
import com.paulocesar.entity.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationItemPK {
    private Donation donation;
    private Item item;
}
