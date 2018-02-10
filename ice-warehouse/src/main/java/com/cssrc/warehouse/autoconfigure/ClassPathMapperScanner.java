package com.cssrc.warehouse.autoconfigure;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.util.StringUtil;
import tk.mybatis.spring.mapper.MapperFactoryBean;

import java.util.Properties;
import java.util.Set;


public class ClassPathMapperScanner extends org.mybatis.spring.mapper.ClassPathMapperScanner {

    private MapperHelper mapperHelper = new MapperHelper();

    public ClassPathMapperScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        //如果没有注册过接口，就注册默认的Mapper接口
        this.mapperHelper.ifEmptyRegisterDefaultInterface();
        GenericBeanDefinition definition;
        for (BeanDefinitionHolder holder : beanDefinitions) {
            definition = (GenericBeanDefinition) holder.getBeanDefinition();
            if (StringUtil.isNotEmpty(definition.getBeanClassName())
                    && definition.getBeanClassName().equals("org.mybatis.spring.mapper.MapperFactoryBean")) {
                definition.setBeanClass(MapperFactoryBean.class);
                definition.getPropertyValues().add("mapperHelper", this.mapperHelper);
            }
        }
        return beanDefinitions;
    }

    public void setMapperProperties(Properties properties) {
        mapperHelper.setProperties(properties);
    }
}
