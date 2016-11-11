package com.hybris.yaas.bites;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hybris.yaas.bites.subscriptions.DocuServiceWrapper;
import com.hybris.yaas.bites.subscriptions.OAuthWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest("server.port=8080")
@PropertySource("classpath:application.properties")

public class SanityTest {

    private final String LOCALENDPOINT = "http://localhost:8080/testTenant/tips";

    @Value("${projectIDAkaTenant}")
    private String TENANT;
    @Value("${docuRepoURL}")
    private String docuRepoURI;
    @Value("${yaaSClientsIdentifier}")
    private String appId;
    @Value("${docuRepoType}")
    private String type;
    @Value("${yaaSClientsClient_ID}")
    private String yaaSClientsClient_ID;
    @Value("${yaaSClientsClient_Secret}")
    private String yaaSClientsClient_Secret;
    @Value("${docuRepoScopes}")
    private String scopes;

    @Autowired
    private OAuthWrapper oaw;
    @Autowired
    private DocuServiceWrapper dsw;

    private HttpHeaders headersWithJustTenant;
    private HttpHeaders headersWithTenantAndScope;
    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpEntity<Tip> emptyEntityWithTenantHeader;

    @Before
    public void setUpTenantInHeadaer() {
    	
    	if (System.getProperty("yaaSClientsIdentifierFromCLI")!=null){
    		appId=System.getProperty("yaaSClientsIdentifierFromCLI");
    		oaw.setAppId(appId);
    		dsw.setAppId(appId);
    	}
    	if (System.getProperty("yaaSClientsClient_IDFromCLI")!=null){
    		yaaSClientsClient_ID=System.getProperty("yaaSClientsClient_IDFromCLI");
    		oaw.setClientId(yaaSClientsClient_ID);
    		dsw.setYaaSClientsClient_ID(yaaSClientsClient_ID);
    	}
    	if (System.getProperty("yaaSClientsClient_SecretFromCLI")!=null){
    		yaaSClientsClient_Secret=System.getProperty("yaaSClientsClient_SecretFromCLI");
       		oaw.setClientSecret(yaaSClientsClient_Secret);
       		dsw.setYaaSClientsClient_Secret(yaaSClientsClient_Secret);
       	  }
    	
    	
    	
    	
        headersWithJustTenant = new HttpHeaders();
        headersWithJustTenant.add("hybris-tenant", TENANT);
        headersWithTenantAndScope = new HttpHeaders();
        headersWithTenantAndScope.add("hybris-tenant", TENANT);
        headersWithTenantAndScope.add("hybris-scopes", "hybris.yaasbites-scopetwo");
        emptyEntityWithTenantHeader = new HttpEntity<Tip>(headersWithJustTenant);
    }

    //@Test(timeout = 10000)
    @Test
    public void siteIsAwakeAndWorking() throws Exception {

        Object o = restTemplate.exchange(LOCALENDPOINT, HttpMethod.DELETE, emptyEntityWithTenantHeader, Object.class).getBody();
        assertTrue(o != null);

        assertTips("no tips initially present");

        Tip newTip = new Tip("1", "Hello World!", TENANT);
        restTemplate.postForEntity(LOCALENDPOINT, new HttpEntity<Tip>(newTip, headersWithJustTenant), Object.class);
        assertTips("tip should get created", newTip);

        Tip newTip2 = new Tip("2", "Hello World!", TENANT);
        restTemplate.postForEntity(LOCALENDPOINT, new HttpEntity<Tip>(newTip2, headersWithJustTenant), Object.class);
        assertTips("tip should get created", newTip, newTip2);
        restTemplate.exchange(LOCALENDPOINT + "/" + newTip2.getId(), HttpMethod.DELETE, emptyEntityWithTenantHeader, String.class);
        assertTips("one tip after deleting second tip", newTip);

        Tip updatedTip = new Tip("1", "Updated tip", TENANT);
        restTemplate.put(LOCALENDPOINT + "/1", new HttpEntity<>(updatedTip, headersWithJustTenant));
        assertTips("tip should not get updated with just tenant in the header but no scope", newTip);

        restTemplate.put(LOCALENDPOINT + "/1", new HttpEntity<Tip>(updatedTip, headersWithTenantAndScope));
        assertTips("tip should get updated", updatedTip);

        restTemplate.exchange(LOCALENDPOINT + "/" + newTip.getId(), HttpMethod.DELETE, emptyEntityWithTenantHeader, String.class);
        assertTips("no tips after delete");
    }

    private void assertTips(String message, Tip... expected) {
        assertArrayEquals(message,
                expected, restTemplate.exchange(LOCALENDPOINT, HttpMethod.GET, emptyEntityWithTenantHeader, Tip[].class).getBody()
        );
    }

    @Test
    public void callingDocuRepoOnBehalfOfProject() {

        Map<String, String> tokenMap = oaw.getToken().orElse(null);
        String token = tokenMap.get("access_token");
        assertTrue(token != null);
        String allocatedScopes = tokenMap.get("scope");

        assertTrue(allocatedScopes.contains(scopes));

        String url = dsw.post(new Tip("1", "Hello World!", TENANT));
        assertTrue(
                url.contains(docuRepoURI) ||
                        url.contains("Conflict")
        );
    }

}
