package com.stars.core.expr.node.value;

import com.stars.core.module.Module;

import java.util.Map;

/**
 * Created by zhaowenshuo on 2017/3/25.
 */
public abstract class ExprValue {

    public abstract Object eval(Map<String, Module> moduleMap);

    public <T> T module(Map<String, Module> moduleMap, String moduleName) {
        return (T) moduleMap.get(moduleName);
    }

    public abstract String toString();
}