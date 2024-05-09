package com.joonseolee.security.api;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Sample {

    public Map<String, Account> doSomething(List<Account> accounts) {
        return accounts.stream().collect(Collectors.toMap(it -> it.getOwner(), Function.identity()));
    }
}
