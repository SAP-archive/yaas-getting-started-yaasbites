package com.hybris.yaas.bites.subscriptions;

import java.util.Map;
import java.util.Optional;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.hybris.yaas.bites.Tip;
import com.hybris.yaas.bites.exceptions.CallingYaaSServiceException;

@ManagedBean
@PropertySource("classpath:application.properties")
public class DocuServiceWrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocuServiceWrapper.class);

    @Value("${docuRepoURL}")
    private String URI;
    @Value("${projectIDAkaTenant}")
    private String tenant;
    @Value("${yaaSClientsIdentifier}")
    private String appId;
    @Value("${docuRepoType}")
    private String type;
    @Value("${yaaSClientsIClient_ID}")
    private String yaaSClientsIClient_ID;
    @Value("${yaaSClientsClient_Secret}")
    private String yaaSClientsClient_Secret;
    @Value("${docuRepoScopes}")
    private String scopes;

    @Autowired
    private OAuthWrapper oaw;


    public DocuServiceWrapper() {
    }

    public DocuServiceWrapper tenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public DocuServiceWrapper appId(String appId) {
        this.appId = appId;
        return this;
    }

    public String post(Tip t) {
        try {
            String token = getTokenForServiceToCallDocuRepoOnBehalfOfProject();
            String url = URI + "/" + tenant + "/" + appId + "/data/" + type;
            HttpEntity<String> request = new HttpEntity<>(toJson(t), headerWithToken(token));
            Map<String, String> map = new RestTemplate().postForObject(url, request, Map.class);
            if (map != null && map.containsKey("link"))
                return map.get("link");
        } catch (RestClientException e) {
            e.printStackTrace();
            if (e.getMessage().contains("Conflict"))
                return "Conflict";
        }
        throw new CallingYaaSServiceException();
    }

    public Optional<Tip> get(String id) {
        try {
            String token = getTokenForServiceToCallDocuRepoOnBehalfOfProject();
            HttpEntity<String> request = new HttpEntity<>(headerWithToken(token));
            String url = geturl4TipId(id);
            Tip tip = new RestTemplate().exchange(url, HttpMethod.GET, request, Tip.class).getBody();
            return Optional.of(tip);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        throw new CallingYaaSServiceException();
    }

    public Tip[] get() {
        try {
            String token = getTokenForServiceToCallDocuRepoOnBehalfOfProject();
            HttpEntity<String> request = new HttpEntity<>(headerWithToken(token));
            String url = URI + "/" + tenant + "/" + appId + "/data/" + type;
            Tip[] tips = new RestTemplate().exchange(url, HttpMethod.GET, request, Tip[].class).getBody();
            return tips;
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new CallingYaaSServiceException();
        }
    }

    public String put(Tip t) {
        try {
            String token = getTokenForServiceToCallDocuRepoOnBehalfOfProject();
            String id = t.getId();
            String url = geturl4TipId(id);
            LOGGER.debug("put url is [{}]", url);
            HttpEntity<String> request = new HttpEntity<>(toJson(t), headerWithToken(token));
            new RestTemplate().put(url, request);
            return url;
        } catch (RestClientException e) {
            e.printStackTrace();
            if (e.getMessage().contains("Conflict"))
                return "Conflict";
        }
        throw new CallingYaaSServiceException();
    }

    private String geturl4TipId(String id) {
        return URI + "/" + tenant + "/" + appId + "/data/" + type + "/" + id;
    }

    public HttpStatus delete(String id) {
        try {
            String token = getTokenForServiceToCallDocuRepoOnBehalfOfProject();
            String url = geturl4TipId(id);
            HttpEntity<String> request = new HttpEntity<>(headerWithToken(token));
            Object o = new RestTemplate().exchange(url, HttpMethod.DELETE, request, Object.class).getBody();
            return HttpStatus.OK;
        } catch (RestClientException e) {
            e.printStackTrace();
            return HttpStatus.NOT_MODIFIED;
        }
    }

    public HttpStatus deleteAll() {
        try {
            String token = getTokenForServiceToCallDocuRepoOnBehalfOfProject();
            String url = URI + "/" + tenant + "/" + appId;
            HttpEntity<Object> request = new HttpEntity<>(headerWithToken(token));
            new RestTemplate().exchange(url, HttpMethod.DELETE, request, Object.class).getBody();
            return HttpStatus.OK;
        } catch (RestClientException e) {
            e.printStackTrace();
            return HttpStatus.NOT_MODIFIED;
        }
    }

    private HttpHeaders headerWithToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        headers.add("Authorization", "Bearer " + token);
        return headers;
    }

    private String toJson(Object o) {
        return new Gson().toJson(o);
    }

    private String getTokenForServiceToCallDocuRepoOnBehalfOfProject() {
        Map<String, String> tokenMap = oaw.getToken().orElse(null);
        if (tokenMap == null)
            throw new CallingYaaSServiceException();
        return tokenMap.get("access_token");
    }
}

