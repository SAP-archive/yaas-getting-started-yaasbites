package com.hybris.yaas.bites;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hybris.yaas.bites.exceptions.CallingYaaSServiceException;
import com.hybris.yaas.bites.exceptions.InsufficientScopesException;
import com.hybris.yaas.bites.exceptions.NoTenantException;
import com.hybris.yaas.bites.subscriptions.TipsDocuRepo;

@RestController
public class TipController {

    private final Logger LOG = LoggerFactory.getLogger(TipController.class);

    @Autowired
    private TipsDocuRepo repo;

    @CrossOrigin()
    @RequestMapping(value = "{tenantFromURL}/ping", method = RequestMethod.GET)
    public ResponseEntity<String> ping(
            @RequestHeader(value = "hybris-tenant", defaultValue = "") String tenantFromHeader,
            @RequestHeader(value = "hybris-scopes", defaultValue = "") String scopesFromHeader,
            @PathVariable String tenantFromURL
    ) {

        String s = "-> In GET " + tenantFromURL + "/ping: " +
                "  tenantFromURLPath: '" + tenantFromURL +
                "',   hybris-tenant from token: '" + tenantFromHeader +
                "',   hybris-scopes from token: '" + scopesFromHeader + "'";
        LOG.debug(s);

        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @CrossOrigin()
    @RequestMapping(value = "{tenantFromURL}/tips", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAllOfOneTenant(@RequestHeader(value = "hybris-tenant") String tenant) {
        LOG.debug("-> In DELETE /tips Tenant: " + tenant);
        if (tenant == null)
            throw new NoTenantException();
        repo.deleteAll();
        return new ResponseEntity<String>("{}", HttpStatus.OK);
    }

    @CrossOrigin()
    @RequestMapping(value = "{tenantFromURL}/tips", method = RequestMethod.GET)
    public ResponseEntity<List<Tip>> getAllTips(@RequestHeader(value = "hybris-tenant") String tenant) {
        LOG.debug("-> In GET /tips Tenant: " + tenant);
        if (tenant == null)
            throw new NoTenantException();
        return new ResponseEntity<>(repo.findByTenant(tenant), HttpStatus.OK);
    }

    @CrossOrigin()
    @RequestMapping(value = "{tenantFromURL}/tips", method = RequestMethod.POST)
    public ResponseEntity<Void> postTip(
            @RequestHeader(value = "hybris-tenant") String tenant,
            @RequestBody Tip tip, UriComponentsBuilder ucBuilder) {
        LOG.debug("-> In POST /tips Tenant: " + tenant);
        if (tenant == null)
            throw new NoTenantException();
        tip.setTenant(tenant);
        repo.post(tip);

        HttpHeaders newHeaders = new HttpHeaders();
        newHeaders.setLocation(ucBuilder.path("/tips/{id}").buildAndExpand(tip.getId()).toUri());
        return new ResponseEntity<Void>(newHeaders, HttpStatus.CREATED);
    }

    @CrossOrigin()
    @RequestMapping(value = "{tenantFromURL}/tips/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> putTip(
            @RequestHeader(value = "hybris-tenant") String tenant,
            @RequestHeader(value = "hybris-scopes") String scopes,
            @PathVariable String id, @RequestBody Tip t) {
        LOG.debug("-> In PUT /tips  Tenant: {} with scopes {}", tenant, scopes);
        if (tenant == null)
            throw new NoTenantException();
        if (scopes == null || !scopes.contains("hybris.yaasbites-scopetwo"))
            throw new InsufficientScopesException();
        t.setTenant(tenant);

        synchronized (repo) {
            //if (repo.exists(id)){
            Optional<Tip> oldTip = repo.findOne(id);
            if (oldTip.isPresent()) {
                oldTip.get().setTip(t.getTip());
                repo.put(oldTip.get());
                return new ResponseEntity<String>(HttpStatus.OK);
            }
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("{'message':'Tip '+id+' not found'}");
    }

    @CrossOrigin()
    @RequestMapping(value = "{tenantFromURL}/tips/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteTip(
            @RequestHeader(value = "hybris-tenant") String tenant,
            @PathVariable String id) {
        LOG.debug("-> In DELETE /tips  Tenant: " + tenant);
        if (tenant == null)
            throw new NoTenantException();

        synchronized (repo) {
            Optional<Tip> oldTip = repo.findOne(id);
            if (oldTip.isPresent() && oldTip.get().getTenant().equals(tenant)) {
                repo.delete(id);
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            }
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("{'message':'Tip '+id+'not found'}");
    }

    // Convert exception to an HTTP Status code
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No Tenant Defined")  // 400
    @ExceptionHandler(NoTenantException.class)
    public void anyName() {
    }

    // Convert exception to an HTTP Status code
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Missing expected scope: hybris.yaasbites-scopetwo")
    // 400
    @ExceptionHandler(InsufficientScopesException.class)
    public void anyOtherName() {
    }

    // Convert exception to an HTTP Status code
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Something went wrong calling a subscribed service")
    // 400
    @ExceptionHandler(CallingYaaSServiceException.class)
    public void andAnyOtherName() {
    }

}
