package io.github.chubbyhippo.refresher.refresh;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.stereotype.Service;

@Service
public class RefreshService {
    private static final Logger log = LoggerFactory.getLogger(RefreshService.class);
    private final RefreshEndpoint refreshEndpoint;

    public RefreshService(RefreshEndpoint refreshEndpoint) {
        this.refreshEndpoint = refreshEndpoint;
    }

    public void triggerRefresh() {
        var refreshResult = refreshEndpoint.refresh();
        log.info("Refreshed: {}", refreshResult);
    }
}
