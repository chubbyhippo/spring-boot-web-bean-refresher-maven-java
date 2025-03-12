package io.github.chubbyhippo.refresher;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeanService {
    public List<String> getBeans(){
       return List.of("messageService", "messageController");
    }
}
