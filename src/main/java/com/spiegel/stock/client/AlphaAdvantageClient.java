package com.spiegel.stock.client;
import com.spiegel.stock.contants.Constants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.util.Map;

public class AlphaAdvantageClient {
    private final Client httpClient;

    public AlphaAdvantageClient()
    {
        httpClient = Client.create();
    }

    public String executeGet(final String endpoint)
    {
        String endpointWithKey = endpoint+"&apikey="+ Constants.ALPHA_ADVANTAGE_KEY;
        WebResource webResource = httpClient.resource(endpointWithKey);
        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);

        return response.getEntity(String.class);
    }
}
