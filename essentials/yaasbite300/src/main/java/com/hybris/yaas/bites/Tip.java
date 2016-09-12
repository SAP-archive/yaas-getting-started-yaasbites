package com.hybris.yaas.bites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tip {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    
    private String tenant; 
  
	private String tip;
  
	public Tip() {
	}

	public Tip(long id, String tip, String tenant) {
		this.id = id;
		this.tip = stripTip(tip);
		this.tenant = tenant;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTip(String tip) {
		this.tip = stripTip(tip);
	}

	public String getTip() {
		return tip;
	}
	
	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
	public String toString(){
		return "Tip[ Id: "+id +" Tip: "+ tip +" Tenant: "+tenant+"]\n";
	}
	private String stripTip(String tip){		
		return tip==null ? null : tip.substring(0, Math.min(tip.length(), 200));
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tip other = (Tip) obj;
		if (id != other.id)
			return false;
		if (tenant == null) {
			if (other.tenant != null)
				return false;
		} else if (!tenant.equals(other.tenant))
			return false;
		if (tip == null) {
			if (other.tip != null)
				return false;
		} else if (!tip.equals(other.tip))
			return false;
		return true;
	}
}
