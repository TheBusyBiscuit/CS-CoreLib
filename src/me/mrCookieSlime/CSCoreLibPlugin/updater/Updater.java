package me.mrCookieSlime.CSCoreLibPlugin.updater;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Updater {
	
	private static final char[] blacklist = "abcdefghijklmnopqrstuvwxyz-+_ ()[]".toCharArray();
	private static final String[] development_builds = {"DEV", "EXPERIMENTAL", "BETA", "ALPHA", "UNFINISHED"};
	
	Plugin plugin;
	URL url;
	String localVersion;
	Thread thread;
	URL download;
	File file;
	String version;
	
	public Updater(Plugin plugin, File file, int id) {
		this.plugin = plugin;
		this.file = file;
		localVersion = plugin.getDescription().getVersion();
		
		// Checking if current Version is a dev-build
		for (String dev: development_builds) {
			if (localVersion.contains(dev)) {
                System.err.println(" ");
                System.err.println("################## - DEVELOPMENT BUILD - ##################");
                System.err.println("You appear to be using an experimental build of " + plugin.getName());
                System.err.println("Version " + localVersion);
                System.err.println(" ");
                System.err.println("Auto-Updates have been disabled. Use at your own risk!");
                System.err.println(" ");
				return;
			}
		}

		localVersion = localVersion.toLowerCase();
		
		// Deleting all unwanted characters
        for (char blocked: blacklist) {
        	localVersion = localVersion.replace(String.valueOf(blocked), "");
        }
        
        // Starting download
		try {
			this.url = new URL("https://api.curseforge.com/servermods/files?projectIds=" + id);
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				
				@Override
				public void run() {
					thread = new Thread(new UpdaterTask());
					thread.start();
				}
			}, 0l);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public class UpdaterTask implements Runnable {

		@Override
		public void run() {
			if (connect()) {
				try {
					check();
				} catch(NumberFormatException x) {
		        	System.err.println(" ");
		        	System.err.println("#################### - ERROR - ####################");
					System.err.println("Could not auto-update " + plugin.getName());
					System.err.println("Unrecognized Version: \"" + localVersion + "\"");
					System.err.println("#################### - ERROR - ####################");
					System.err.println(" ");
				}
			}
		}
		
		private boolean connect() {
	        try {
	            final URLConnection connection = url.openConnection();
	            connection.setConnectTimeout(5000);
	            connection.addRequestProperty("User-Agent", "Auto Updater (by mrCookieSlime)");
	            connection.setDoOutput(true);

	            final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            final JSONArray array = (JSONArray) JSONValue.parse(reader.readLine());
	            if (array.isEmpty()) {
	            	System.err.println("[" + plugin.getName() + " - Updater] Could not connect to BukkitDev, is it down?");
					try {
						thread.join();
					} catch (InterruptedException x) {
						x.printStackTrace();
					}
		            return false;
	            }
	            download = traceURL(((String) ((JSONObject) array.get(array.size() - 1)).get("downloadUrl")).replace("https:", "http:"));
	            version = (String) ((JSONObject) array.get(array.size() - 1)).get("name");
	            version = version.toLowerCase();
	            for (char blocked: blacklist) {
	            	version = version.replace(String.valueOf(blocked), "");
	            }
	            return true;
	        } catch (IOException e) {
				System.err.println("[" + plugin.getName() + " - Updater] Could not connect to BukkitDev, is it down?");
				try {
					thread.join();
				} catch (InterruptedException x) {
					x.printStackTrace();
				}
	            return false;
	        }
	    }
		
		private void check() {
	        String[] localSplit = localVersion.split("\\.");
	        String[] remoteSplit = version.split("\\.");
	        
	        for (int i = 0; i < remoteSplit.length; i++) {
	        	if ((localSplit.length - 1) < i) {
	        		install();
	        		return;
	        	}
	        	if (Integer.parseInt(localSplit[i]) > Integer.parseInt(remoteSplit[i])) {
	        		try {
	    				thread.join();
	    			} catch (InterruptedException x) {
	    				x.printStackTrace();
	    			}
	        		return;
	        	}
	        	if (Integer.parseInt(remoteSplit[i]) > Integer.parseInt(localSplit[i])) {
	        		install();
	        		return;
	        	}
	        }
	        System.out.println("[CS-CoreLib - Updater] " + plugin.getName() + " is up to date!");
	        try {
				thread.join();
			} catch (InterruptedException x) {
				x.printStackTrace();
			}
		}
		

	    private URL traceURL(String location) throws IOException {
	    	HttpURLConnection connection = null;
	    	
	        while (true) {
	        	URL url = new URL(location);
	        	connection = (HttpURLConnection) url.openConnection();

	        	connection.setInstanceFollowRedirects(false);
	            connection.setConnectTimeout(5000);
	            connection.addRequestProperty("User-Agent", "Auto Updater (by mrCookieSlime)");

	            switch (connection.getResponseCode()) {
	                case HttpURLConnection.HTTP_MOVED_PERM:
	                case HttpURLConnection.HTTP_MOVED_TEMP:
	                    String loc = connection.getHeaderField("Location");
	                    location = new URL(new URL(location), loc).toExternalForm();
	                    continue;
	            }
	            break;
	        }
	        
	        return new URL(connection.getURL().toString().replaceAll(" ", "%20"));
	    }

		private void install() {
			System.out.println("[CS-CoreLib - Updater] " + plugin.getName() + " is outdated!");
			System.out.println("[CS-CoreLib - Updater] Downloading " + plugin.getName() + " v" + version);
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				
				@Override
				public void run() {
					BufferedInputStream input = null;
					FileOutputStream output = null;
					System.out.println(download.toString());
			        try {
			            input = new BufferedInputStream(download.openStream());
			            output = new FileOutputStream(new File("plugins/" + Bukkit.getUpdateFolder(), file.getName()));

			            final byte[] data = new byte[1024];
			            int read;
			            while ((read = input.read(data, 0, 1024)) != -1) {
			                output.write(data, 0, read);
			            }
			        } catch (Exception ex) {
			        	System.err.println(" ");
			        	System.err.println("#################### - ERROR - ####################");
						System.err.println("Could not auto-update " + plugin.getName());
						System.err.println("#################### - ERROR - ####################");
						System.err.println(" ");
						ex.printStackTrace();
			        } finally {
			            try {
			                if (input != null) input.close();
			                if (output != null) output.close();
			                System.err.println(" ");
			                System.err.println("#################### - UPDATE - ####################");
			                System.err.println(plugin.getName() + " was successfully updated (" + localVersion + " -> " + version + ")");
			                System.err.println("Please restart your Server in order to use the new Version");
			                System.err.println(" ");
			            } catch (IOException e) {
			            	e.printStackTrace();
			            }
			            try {
							thread.join();
						} catch (InterruptedException x) {
							x.printStackTrace();
						}
			        }
				}
			}, 10L);
		}
	}

}
