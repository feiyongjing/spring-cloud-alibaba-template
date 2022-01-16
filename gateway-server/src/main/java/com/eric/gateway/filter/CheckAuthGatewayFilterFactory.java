package com.eric.gateway.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.springframework.cloud.gateway.support.GatewayToStringStyler.filterToStringCreator;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

@Component
public class CheckAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<CheckAuthGatewayFilterFactory.Config> {

    /**
     * Prefix key.
     */
    public static final String PREFIX_KEY = "prefix";

    private static final Log log = LogFactory
            .getLog(CheckAuthGatewayFilterFactory.class);

    public CheckAuthGatewayFilterFactory() {
        super(CheckAuthGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(PREFIX_KEY);
    }

    @Override
    public GatewayFilter apply(CheckAuthGatewayFilterFactory.Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange,
                                     GatewayFilterChain chain) {
                boolean alreadyPrefixed = exchange
                        .getAttributeOrDefault(GATEWAY_ALREADY_PREFIXED_ATTR, false);
                if (alreadyPrefixed) {
                    return chain.filter(exchange);
                }
                exchange.getAttributes().put(GATEWAY_ALREADY_PREFIXED_ATTR, true);

                ServerHttpRequest req = exchange.getRequest();
                addOriginalRequestUrl(exchange, req.getURI());
                String newPath = config.prefix + req.getURI().getRawPath();

                ServerHttpRequest request = req.mutate().path(newPath).build();

                exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, request.getURI());

                if (log.isTraceEnabled()) {
                    log.trace("Prefixed URI with: " + config.prefix + " -> "
                            + request.getURI());
                }

                return chain.filter(exchange.mutate().request(request).build());
            }

            @Override
            public String toString() {
                return filterToStringCreator(CheckAuthGatewayFilterFactory.this)
                        .append("prefix", config.getPrefix()).toString();
            }
        };
    }

    public static class Config {

        private String prefix;

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

    }
}