package com.metehansargin.springlearn2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RootEntity<G> {
    private boolean result;

    private String errorMessage;

    private G data;

    public static <G> RootEntity<G> ok(G data){
        RootEntity<G> rootEntity=new RootEntity<>();
        rootEntity.setResult(true);
        rootEntity.setData(data);
        rootEntity.setErrorMessage(null);

        return rootEntity;
    }
    public static <G> RootEntity<G> error(String errorMessage){
        RootEntity<G> rootEntity=new RootEntity<>();
        rootEntity.setResult(false);
        rootEntity.setData(null);
        rootEntity.setErrorMessage(errorMessage);

        return rootEntity;
    }
}
