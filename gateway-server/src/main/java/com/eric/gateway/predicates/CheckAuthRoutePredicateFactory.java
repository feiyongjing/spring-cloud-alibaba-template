package com.eric.gateway.predicates;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class CheckAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {

    public CheckAuthRoutePredicateFactory() {
        super(CheckAuthRoutePredicateFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("param", "value");
    }

    @Override
    public Predicate<ServerWebExchange> apply(CheckAuthRoutePredicateFactory.Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange exchange) {
                if (!StringUtils.hasText(config.value)) {
                    // check existence of header
                    return exchange.getRequest().getQueryParams()
                            .containsKey(config.param);
                }

                List<String> values = exchange.getRequest().getQueryParams()
                        .get(config.param);
                if (values == null) {
                    return false;
                }
                for (String value : values) {
                    if (value != null && value.matches(config.value)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public String toString() {
                return String.format("CheckAuth: param1=%s value=%s", config.getParam1(),
                        config.getValue());
            }
        };
    }

    @Validated
    public static class Config {

        @NotEmpty
        private String param;

        private String value;

        public String getParam1() {
            return param;
        }

        public Config setParam(String param) {
            this.param = param;
            return this;
        }

        public String getValue() {
            return value;
        }

        public Config setValue(String value) {
            this.value = value;
            return this;
        }


    }

}
