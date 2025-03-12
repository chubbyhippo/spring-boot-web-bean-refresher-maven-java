package io.github.chubbyhippo.refresher.refresh;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefreshController {
    private final RefreshService refreshService;

    public RefreshController(RefreshService refreshService) {
        this.refreshService = refreshService;
    }

    @PostMapping("/refresh")
    void refresh() {
        refreshService.triggerRefresh();
    }
}
