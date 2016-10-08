package rest;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

public class SingletonRestClient {

    private static SingletonRestClient restClient;

    private ResteasyClient client;

    private SingletonRestClient() {
        this.client = new ResteasyClientBuilder().build();
    }

    public static SingletonRestClient getInstance() {
        if (restClient == null) restClient = new SingletonRestClient();
        return restClient;
    }

    public ResteasyClient getClient() {
        return this.client;
    }

}
