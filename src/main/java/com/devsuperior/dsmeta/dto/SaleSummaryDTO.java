package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

public class SaleSummaryDTO {

    private String sellerName;
    private Double total ;

    public SaleSummaryDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SaleSummaryDTO(Seller entity) {
       sellerName = entity.getName();
        total = entity.getSales().stream()
                .mapToDouble(Sale::getAmount)
                .sum();
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }


}
