package com.metehansargin.springlearn2.controller;

import com.metehansargin.springlearn2.model.RootEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootBaseController {

    public <G>RootEntity<G> ok(G data){
        return RootEntity.ok(data);
    }

    public <G> RootEntity<G> error(String errorMessage){
        return RootEntity.error(errorMessage);
    }

}
