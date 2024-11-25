package dev.wisespirit.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PurchaseOrderNumberDto extends BaseDto{
    Long purchaseOrderNumber;
}
