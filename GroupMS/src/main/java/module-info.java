open module GroupMS {
    requires spring.web;
    requires spring.hateoas;
    requires lombok;
    requires java.persistence;
    requires jackson.annotations;
    requires com.fasterxml.jackson.databind;
    requires spring.context;
    requires spring.data.jpa;
    requires spring.boot.autoconfigure;
    requires spring.cloud.netflix.eureka.client;
    requires spring.boot;
    requires java.validation;
    requires slf4j.api;
}