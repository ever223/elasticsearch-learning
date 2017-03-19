package com.xiaogan.elasticsearch.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ElasticsearchConfig {
    @Value("${elasticsearch.host}")
    private String elasticsearchHost;
    @Value("${elasticsearch.port}")
    private int elasticsearchPort;

    @Bean
    public Client client() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
//                .put("cluster.user", "elastic")
//                .put("cluster.password", "changeme")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(elasticsearchHost), elasticsearchPort));

        return client;
    }
}
