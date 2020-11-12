package com.jlpay.demo.apollo.config;

import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.config.ConfigPropertySource;
import com.ctrip.framework.apollo.spring.config.ConfigPropertySourceFactory;
import com.ctrip.framework.apollo.spring.util.SpringInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * apollo热加载配置
 * @author lishaofeng
 */
@Component
public class ApolloRefreshProcessor implements ConfigChangeListener {

    private final ConfigPropertySourceFactory configPropertySourceFactory = SpringInjector
            .getInstance(ConfigPropertySourceFactory.class);

    @Autowired
    ApplicationContext applicationContext ;

    @Autowired
    RefreshScope refreshScope ;

    @PostConstruct
    public void init(){

        List<ConfigPropertySource> allConfigPropertySources = configPropertySourceFactory.getAllConfigPropertySources();
        allConfigPropertySources.forEach(configPropertySource -> {
            configPropertySource.addChangeListener(this);
        });
    }

    @Override
    public void onChange(ConfigChangeEvent changeEvent) {
        this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
        refreshScope.refreshAll();
    }



}