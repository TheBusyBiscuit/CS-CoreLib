package me.mrCookieSlime.CSCoreLibPlugin.java;

import java.util.Base64;

public class Converter {
	
	public static String encode(EncodingType type, String string) {
		switch (type) {
		case BASE64: {
			return new String(Base64.getEncoder().encode(string.getBytes()));
		}
		case BINARY: {
			StringBuilder binary = new StringBuilder();
			for (byte b: string.getBytes()) {
				int value = b;
				for (int i = 0; i < 8; i++) {
					binary.append((value & 128) == 0 ? 0: 1);
					value <<= 1;
				}
				binary.append(" ");
			}
			return binary.toString();
		}
		default:
			return "";
		}
	}
	
	public static String decode(EncodingType type, String string) {
		switch (type) {
		case BASE64: {
			return new String(Base64.getDecoder().decode(string));
		}
		case BINARY: {
			StringBuilder text = new StringBuilder();
			for (String segment: string.split(" ")) {
				text.append(new Character((char) Integer.parseInt(segment, 2)).toString());
			}
			return text.toString();
		}
		default:
			return "";
		}
	}

}
