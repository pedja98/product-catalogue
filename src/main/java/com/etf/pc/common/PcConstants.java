package com.etf.pc.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PcConstants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ErrorCodes {
        public static final String CHAR_NOT_FOUND = "charNotFound";
        public static final String CHAR_ALREADY_ADDED = "charAlreadyExistsAdded";
        public static final String DELETE_TP_CHAR_12H_PASSED = "deleteTpChar12hPassed";
        public static final String MAX_TP_LOWER_THEN_MIN = "maxTpLowerThenMin";
        public static final String TARIFF_PLAN_NOT_FOUND = "tariffPlanNotFound";
        public static final String ADDON_NOT_FOUND = "addonNotFound";
        public static final String DISCOUNT_NOT_FOUND = "discountNotFound";
        public static final String DUPLICATE_IDENTIFIER = "duplicateIdentifier";
        public static final String DISCOUNT_BAD_VALUE = "discountBadValue";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class SuccessCodes {
        public static final String CHAR_CREATED = "charCreated";
        public static final String CHAR_UPDATED = "charUpdated";
        public static final String ADDON_CREATED = "addonCreated";
        public static final String ADDON_UPDATED = "addonUpdated";
        public static final String TARIFF_PLAN_CREATED = "tariffPlanCreated";
        public static final String TARIFF_PLAN_UPDATED = "tariffPlanUpdated";
        public static final String DISCOUNT_DELETED = "discountDeleted";
        public static final String TARIFF_PLAN_CHARACTERISTIC_ADDED = "tariffPlanCharacteristicAdded";
        public static final String TARIFF_PLAN_CHARACTERISTIC_DELETED = "tariffPlanCharacteristicDeleted";
        public static final String TARIFF_PLAN_DISCOUNT_CREATED = "tariffPlanDiscountCreated";
        public static final String TARIFF_PLAN_DISCOUNT_UPDATED = "tariffPlanDiscountUpdated";
    }
}
