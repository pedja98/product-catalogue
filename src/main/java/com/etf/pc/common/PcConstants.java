package com.etf.pc.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PcConstants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ErrorCodes {
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class SuccessCodes {
        public static final String CHAR_CREATED = "charCreated";
        public static final String ADDON_CREATED = "addonCreated";
        public static final String ADDON_UPDATED = "addonUpdated";
        public static final String TARIFF_PLAN_CREATED = "tariffPlanCreated";
        public static final String TARIFF_PLAN_UPDATED = "tariffPlanUpdated";
    }
}
