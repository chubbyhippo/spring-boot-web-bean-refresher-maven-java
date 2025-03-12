package io.github.chubbyhippo.refresher;


import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class RefresherService {
    private final ApplicationEventPublisher publisher;

    public RefresherService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void triggerRefresh() {
        publisher.publishEvent(new RefreshScopeRefreshedEvent());
    }
}
