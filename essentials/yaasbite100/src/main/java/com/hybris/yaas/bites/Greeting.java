package com.hybris.yaas.bites;

public class Greeting {

    private String content;
 
    public Greeting(){
    }
    
    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Greeting other = (Greeting) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}

}

