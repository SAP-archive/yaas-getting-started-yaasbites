package com.hybris.yaas.bites.subscriptions;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;

import com.hybris.yaas.bites.Tip;

@PropertySource("classpath:application.properties")
@ManagedBean
public class TipsDocuRepo {

    @Autowired
    private DocuServiceWrapper dsw;

    public List<Tip> findByTenant(String tenant) {
        Tip[] tips = dsw.get();
        return Arrays.asList(tips);
    }

    public String post(Tip t) {
        return dsw.post(t);
    }

    public String put(Tip t) {
        return dsw.put(t);
    }

    public void delete(String id) {
        dsw.delete(id);
    }

    public void deleteAll() {
        dsw.deleteAll();
    }

    public boolean exists(String id) {
        return dsw.get(id).isPresent();
    }

    public Optional<Tip> findOne(String id) {
        return dsw.get(id);

    }
}