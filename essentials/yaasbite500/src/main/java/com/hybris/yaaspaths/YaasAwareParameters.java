package com.hybris.yaaspaths;

import java.util.Map;


public class YaasAwareParameters
{
	private String hybrisTenant;
	private String hybrisClient;
	private String hybrisUser;
	private String hybrisScopes;
	private String hybrisRequestId;
	private Integer hybrisHop;

	public YaasAwareParameters(){
		
	}
	
	public YaasAwareParameters( Map<String,String> headers ){
    	if (headers!=null){
			setHybrisHop(Integer.getInteger(headers.get("hybris-hop"),1));    	
			setHybrisClient(headers.get("hybris-client"));
			setHybrisTenant(headers.get("hybris-tenant"));
			setHybrisUser(headers.get("hybris-user"));
			setHybrisScopes(headers.get("hybris-scopes"));
			setHybrisRequestId(headers.get("hybris-request-id"));
    	}
	}
	
	public String getHybrisTenant()
	{
		return hybrisTenant;
	}

	public String getHybrisClient()
	{
		return hybrisClient;
	}

	public String getHybrisUser()
	{
		return hybrisUser;
	}

	public String getHybrisScopes()
	{
		return hybrisScopes;
	}

	public String getHybrisRequestId()
	{
		return hybrisRequestId;
	}

	public Integer getHybrisHop()
	{
		return hybrisHop;
	}

	public void setHybrisTenant(final String hybrisTenant)
	{
		this.hybrisTenant = hybrisTenant;
	}

	public void setHybrisClient(final String hybrisClient)
	{
		this.hybrisClient = hybrisClient;
	}

	public void setHybrisUser(final String hybrisUser)
	{
		this.hybrisUser = hybrisUser;
	}

	public void setHybrisScopes(final String hybrisScopes)
	{
		this.hybrisScopes = hybrisScopes;
	}

	public void setHybrisRequestId(final String hybrisRequestId)
	{
		this.hybrisRequestId = hybrisRequestId;
	}

	public void setHybrisHop(final Integer hybrisHop)
	{
		this.hybrisHop = hybrisHop;
	}

	public String toString(){
		String s = 
				"\n-> YaaSAwareParameter: YAP Client: "+ getHybrisClient() + 
				"\nYAP Scope: "+ getHybrisScopes() + 
				"\nYAP Tenant: "+ getHybrisTenant() + 
				"\nYAP User: "+ getHybrisUser()+
				"\nYAP RequestId: "+ getHybrisRequestId() +"\n";
		return s;			
	}
}
