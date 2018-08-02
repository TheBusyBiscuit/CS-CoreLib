package me.mrCookieSlime.CSCoreLibPlugin.database;

public class TableValue {
	
	public String key, value;
	
	public TableValue(String key, String value) {
		this.key = key;
		this.value = "'" + value + "'";
	}
	
	public TableValue(String key, int value) {
		this.key = key;
		this.value = String.valueOf(value);
	}
	
	public TableValue(String key, double value) {
		this.key = key;
		this.value = String.valueOf(value);
	}
	
	public TableValue(String key, float value) {
		this.key = key;
		this.value = String.valueOf(value);
	}
	
	public TableValue(String key, long value) {
		this.key = key;
		this.value = String.valueOf(value);
	}
	
	@Override
	public String toString() {
		return key + "=" + value;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		
		result = 31 * result + key.hashCode();
		result = 31 * result + value.hashCode();
		
		return result;
	}

}
