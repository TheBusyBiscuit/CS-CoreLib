package me.mrCookieSlime.CSCoreLibPlugin.database;

@Deprecated
public class SQLColumn {
	
	private String name;
	private DataType type;
	private boolean nullable;
	private String defaultValue;
	
	public SQLColumn(String name, boolean canBeNull, DataType type) {
		this.name = name;
		this.type = type;
		this.nullable = canBeNull;
		this.defaultValue = null;
	}
	
	public SQLColumn(String name, boolean canBeNull, String defaultValue, DataType type) {
		this.name = name;
		this.type = type;
		this.nullable = canBeNull;
		this.defaultValue = "'" + defaultValue + "'";
	}
	
	public SQLColumn(String name, boolean canBeNull, int defaultValue, DataType type) {
		this.name = name;
		this.type = type;
		this.nullable = canBeNull;
		this.defaultValue = String.valueOf(defaultValue);
	}
	
	public SQLColumn(String name, boolean canBeNull, float defaultValue, DataType type) {
		this.name = name;
		this.type = type;
		this.nullable = canBeNull;
		this.defaultValue = String.valueOf(defaultValue);
	}
	
	public SQLColumn(String name, boolean canBeNull, double defaultValue, DataType type) {
		this.name = name;
		this.type = type;
		this.nullable = canBeNull;
		this.defaultValue = String.valueOf(defaultValue);
	}
	
	public SQLColumn(String name, boolean canBeNull, long defaultValue, DataType type) {
		this.name = name;
		this.type = type;
		this.nullable = canBeNull;
		this.defaultValue = String.valueOf(defaultValue);
	}
	
	public String getName() {
		return this.name;
	}
	
	public DataType getType() {
		return this.type;
	}
	
	@Override
	public String toString() {
		return name + " " + type.toString() + (!nullable ? " NOT NULL": "") + (defaultValue != null ? " DEFAULT " + defaultValue: "");
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		
		result = 31 * result + name.hashCode();
		result = 31 * result + (nullable ? 1 : 0); 
		result = 31 * result + type.hashCode();
		if (defaultValue != null) result = 31 * result + defaultValue.hashCode();
		
		return result;
	}
	
	public static class DataType {
		
		private String str;
		
		public DataType(String str) {
			this.str = str;
		}
		
		public static DataType BIT(int size) {
			return new DataType("BIT(" + size + ")");
		}
		
		public static DataType CHAR(int size) {
			return new DataType("CHAR(" + size + ")");
		}
		
		public static DataType VARCHAR(int size) {
			return new DataType("VARCHAR(" + size + ")");
		}

		public static DataType TEXT() {
			return new DataType("TEXT");
		}
		
		public static DataType TINYTEXT() {
			return new DataType("TINYTEXT");
		}
		
		public static DataType MEDIUMTEXT() {
			return new DataType("MEDIUMTEXT");
		}
		
		public static DataType LONGTEXT() {
			return new DataType("LONGTEXT");
		}
		
		public static DataType BLOB() {
			return new DataType("BLOB");
		}
		
		public static DataType MEDIUMBLOB() {
			return new DataType("MEDIUMBLOB");
		}
		
		public static DataType ENUM(String... values) {
			StringBuilder builder = new StringBuilder("ENUM(");
			
			boolean start = true;
			for (String value: values) {
				if (!start) builder.append(",");
				builder.append("'");
				builder.append(value);
				builder.append("'");
				start = false;
			}
			
			return new DataType(builder.toString() + ")");
		}
		
		public static DataType SET() {
			return new DataType("SET");
		}

		// -128 to 127 									/ 0 to 255
		public static DataType Int8(int size) {
			return new DataType("TINYINT(" + size + ")");
		}
		
		public static DataType Int8() {
			return new DataType("TINYINT");
		}
		
		// -32768 to 32767 								/ 0 to 65535
		public static DataType Int16(int size) {
			return new DataType("SMALLINT(" + size + ")");
		}
		
		public static DataType Int16() {
			return new DataType("SMALLINT");
		}
		
		// -32768 to 32767 								/ 0 to 16777215
		public static DataType Int24(int size) {
			return new DataType("MEDIUMINT(" + size + ")");
		}
		
		public static DataType Int24() {
			return new DataType("MEDIUMINT");
		}
		
		// -2147483648 to 2147483647 					/ 0 to 4294967295
		public static DataType Int32(int size) {
			return new DataType("INT(" + size + ")");
		}
		
		public static DataType Int32() {
			return new DataType("INT");
		}
		
		// -9223372036854775808 to 9223372036854775807 	/ 0 to 18446744073709551615
		public static DataType Int64(int size) {
			return new DataType("BIGINT(" + size + ")");
		}
		
		public static DataType Int64() {
			return new DataType("BIGINT");
		}

		public static DataType FLOAT() {
			return new DataType("FLOAT");
		}
		
		public static DataType FLOAT(int size) {
			return new DataType("FLOAT(" + size + ")");
		}
		
		public static DataType FLOAT(int size, int digits) {
			return new DataType("FLOAT(" + size + "," + digits + ")");
		}

		public static DataType DOUBLE() {
			return new DataType("DOUBLE");
		}
		
		public static DataType DOUBLE(int size) {
			return new DataType("DOUBLE(" + size + ")");
		}
		
		public static DataType DOUBLE(int size, int digits) {
			return new DataType("DOUBLE(" + size + "," + digits + ")");
		}

		public static DataType DECIMAL() {
			return new DataType("DOUBLE");
		}
		
		public static DataType DECIMAL(int size) {
			return new DataType("DOUBLE(" + size + ")");
		}
		
		public static DataType DECIMAL(int size, int digits) {
			return new DataType("DECIMAL(" + size + "," + digits + ")");
		}
		
		public static DataType DATE() {
			return new DataType("DATE");
		}
		
		public static DataType DATETIME() {
			return new DataType("DATETIME");
		}
		
		public static DataType TIMESTAMP() {
			return new DataType("TIMESTAMP");
		}
		
		public static DataType TIME() {
			return new DataType("TIME");
		}
		
		public static DataType YEAR() {
			return new DataType("YEAR");
		}
		
		@Override
		public String toString() {
			return str;
		}
		
		@Override
		public int hashCode() {
			return str.hashCode();
		}
	}

}
