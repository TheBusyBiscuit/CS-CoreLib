package me.mrCookieSlime.CSCoreLibPlugin.general.Chat.Command;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public abstract class CSCommand {
	
	/**
	 * Constructor for a new Command
	 *
	 * @param  command The Name of the Command
	 * @param  description The Description for the Command
	 * @param  usage The Usage for the Command
	 * @param  plugin The Plugin which this Command belongs to
	 * @param  aliases The Aliases for the Command
	 */ 
	public CSCommand(String command, String description, String usage, Plugin plugin, String... aliases) {
        try {
        	Object map = plugin.getServer().getClass().getMethod("getCommandMap").invoke(plugin.getServer());
            Command cmd = new Command(command, description, usage, Arrays.asList(aliases)) {

    			@Override
    			public boolean execute(CommandSender arg0, String arg1, String[] arg2) {
    				return run(arg0, arg2);
    			}
            	
            };
			map.getClass().getMethod("register", String.class, Command.class).invoke(map, command, cmd);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Behind-The-Scenes Method for the Command Execution
	 *
	 * @param  sender The Sender of the Command
	 * @param  args The Arguments the Sender specified
	 * @return      Whether the Command succeeded or not
	 */ 
	protected boolean run(CommandSender sender, String[] args) {
		if (args.length == 0 && displayHelpPage(sender)) return true;
		else if (args[0].equalsIgnoreCase("help") && displayHelpPage(sender)) return true;
		else if (args[0].equals("?") && displayHelpPage(sender)) return true;
		else return onCommand(sender, args);
	}

	/**
	 * Abstract Method for no-arguments, 
	 * argument help or argument ?
	 *
	 * @param  sender The Sender of the Command
	 * @return      Whether the Command succeeded or not
	 */ 
	public abstract boolean displayHelpPage(CommandSender sender);
	
	/**
	 * Abstract Method for the Command Execution
	 *
	 * @param  sender The Sender of the Command
	 * @param  args The Arguments the Sender specified
	 * @return      Whether the Command succeeded or not
	 */ 
	public abstract boolean onCommand(CommandSender sender, String[] args);

}
