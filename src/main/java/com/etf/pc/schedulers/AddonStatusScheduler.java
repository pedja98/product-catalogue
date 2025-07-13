package com.etf.pc.schedulers;

import com.etf.pc.enums.ItemStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddonStatusScheduler {

    private final com.etf.pc.repositories.AddonRepository addonRepository;
    @Scheduled(cron = "0 0 1 * * *")
    public void deactivateExpiredAddons() {
        LocalDate today = LocalDate.now();
        List<com.etf.pc.entities.Addon> expiredAddons = addonRepository.findByStatusAndValidToLessThanEqual(ItemStatus.ACTIVE, today);

        if (expiredAddons.isEmpty()) {
            log.info("No expired addons found.");
            return;
        }

        expiredAddons.forEach(addon -> {
            addon.setStatus(ItemStatus.INACTIVE);
        });

        addonRepository.saveAll(expiredAddons);
        log.info("Deactivated {} expired addons.", expiredAddons.size());
    }
}
