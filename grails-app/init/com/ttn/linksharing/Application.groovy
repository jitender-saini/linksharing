package com.ttn.linksharing

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration


class Application extends GrailsAutoConfiguration {  //implements EnvironmentAware
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

//    @Override
//    void setEnvironment(Environment environment) {
//        String configPath = System.properties["local.config.location"]
//        Resource resourceConfig = new FileSystemResource(configPath)
//        YamlPropertiesFactoryBean ypfb = new YamlPropertiesFactoryBean()
//        ypfb.setResources([resourceConfig] as Resource[])
//        ypfb.afterPropertiesSet()
//        Properties properties = ypfb.getObject()
//        environment.propertySources.addFirst(new PropertiesPropertySource("local.config.location", properties))
//    }
}