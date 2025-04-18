package com.etf.pc.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PcConstants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ErrorCodes {
        public static final String CHAR_NOT_FOUND = "charNotFound";
        public static final String TARIFF_PLAN_NOT_FOUND = "tariffPlanNotFound";
        public static final String DISCOUNT_NOT_FOUND = "discountNotFound";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class SuccessCodes {
        public static final String CHAR_CREATED = "charCreated";
        public static final String ADDON_CREATED = "addonCreated";
        public static final String ADDON_UPDATED = "addonUpdated";
        public static final String TARIFF_PLAN_CREATED = "tariffPlanCreated";
        public static final String TARIFF_PLAN_UPDATED = "tariffPlanUpdated";
        public static final String TARIFF_PLAN_CHARACTERISTIC_ADDED = "tariffPlanCharacteristicAdded";
        public static final String TARIFF_PLAN_CHARACTERISTIC_DELETED = "tariffPlanCharacteristicDeleted";
        public static final String TARIFF_PLAN_DISCOUNT_CREATED = "tariffPlanDiscountCreated";
        public static final String TARIFF_PLAN_DISCOUNT_UPDATED = "tariffPlanDiscountUpdated";
    }
}
