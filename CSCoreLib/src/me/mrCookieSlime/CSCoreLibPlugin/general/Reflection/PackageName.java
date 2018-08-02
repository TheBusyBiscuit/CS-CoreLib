package me.mrCookieSlime.CSCoreLibPlugin.general.Reflection;

public enum PackageName {

	NMS("net.minecraft.server."),
	OBC("org.bukkit.craftbukkit.");
	
	private String path;
	
	private PackageName(String path) {
		this.path = path;
	}

	public String toPackage() {
		return path;
	}
	
}
