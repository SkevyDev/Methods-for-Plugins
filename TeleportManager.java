package me.skevydev.methods.criados;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.skevydev.methods.Main;

public class TeleportManager {

	public static void set(Player p, ConfigurationSection config, String nome_do_local, String[] mensagem_local_setado) {	
		Location loc = p.getLocation();
		config.set(nome_do_local + ".world", loc.getWorld().getName());
		config.set(nome_do_local + ".x", loc.getX());
		config.set(nome_do_local + ".y", loc.getY());
		config.set(nome_do_local + ".z", loc.getZ());
		config.set(nome_do_local + ".pitch", loc.getPitch());
		config.set(nome_do_local + ".yaw", loc.getYaw());
		Main.getPlugin(Main.class).saveConfig();
		p.sendMessage(mensagem_local_setado);
	}
	
	public static void teleportar(Player p, ConfigurationSection config, String nome_do_local, String[] erro_quando_a_warp_nao_existir, String[] mensagem_de_teleporte) {
		if(config.getConfigurationSection(nome_do_local) == null) {
			p.sendMessage(erro_quando_a_warp_nao_existir);
		}else {
			World w = Bukkit.getWorld(config.getString(nome_do_local + ".world"));
			double x = config.getDouble(nome_do_local + ".x");
			double y = config.getDouble(nome_do_local + ".y");
			double z = config.getDouble(nome_do_local + ".z");
			float pitch = (float) config.getDouble(nome_do_local + ".pitch");
			float yaw = (float) config.getDouble(nome_do_local + ".yaw");
			Location tpToLocal = new Location(w, x, y, z);
			tpToLocal.setPitch(pitch);
			tpToLocal.setYaw(yaw);
			p.teleport(tpToLocal);
			p.sendMessage(mensagem_de_teleporte);
		}
	}
	
	public static void remover(Player p, ConfigurationSection config, String nome_do_local, String[] mensagem_local_removido) {
		config.set(nome_do_local, null);
		p.sendMessage(mensagem_local_removido);
	}
	
}
