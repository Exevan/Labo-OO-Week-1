package domain;

public enum ProductType {
	FILM , MUZIEK , SPEL;
	
	public static ProductType fromString(String s){
		for(ProductType type : ProductType.values()){
			if(type.toString().equalsIgnoreCase(s)){
				return type;
			}
		}
		return null;
	}
}
