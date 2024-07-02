package com.paulocesar;

import com.paulocesar.entity.DistributionCenter;

public class Main {
    public static void main(String[] args) {
        DistributionCenter dc = new DistributionCenter("Centro de Distribuição Prosperidade",
                "Av. Borges de Medeiros", "1501", "Cidade Baixa",
                "Porto Alegre", "RS", "90119-900");

        System.out.println(dc);
    }
}