package me.mrCookieSlime.CSCoreLibPlugin.Guide;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class Guides {
	
	public static Guides instance;
	
	List<PluginGuide> guides;
	
	public Guides() {
		this.guides = new ArrayList<PluginGuide>();
		instance = this;
	}
	
	public static Guides getInstance() {
		return instance;
	}
	
	public List<PluginGuide> list() {
		return guides;
	}
	
	public PluginGuide get(int id) {
		return guides.get(id);
	}
	
	public boolean set(int index, PluginGuide guide) {
		if ((guides.size() - 1) >= index) {
			if (guides.get(index) != null) {
				guides.set(index, guide);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			for (int i = 0; i <= index; i++) {
				if (i == index) {
					guides.add(guide);
				}
				else if (i >= guides.size()) {
					guides.add(null);
				}
			}
			return true;
		}
	}
	
	public void refresh(int id, PluginGuide guide) {
		guides.set(id, guide);
	}

}
