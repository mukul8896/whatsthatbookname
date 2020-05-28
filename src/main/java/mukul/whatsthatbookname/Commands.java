package mukul.whatsthatbookname;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * Commands class extending ListenerAdapter Giving our own implemention to method onMessageReceived
 * 
 * @author Mukul
 */

public class Commands extends ListenerAdapter {
	private String prefix = "/whatsthatbookname";

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		String msg=event.getMessage().getContentRaw(); String
		query=msg.substring(prefix.length()+1, msg.length());
		System.out.println(query);

		/**
		 * if else condition to validate user's command formate
		 */
		if(msg.startsWith(prefix)) { 
			event.getChannel().sendTyping().queue();
			BookSearchEngine engine=new BookSearchEngine(); 
			List<String[]> booknames=new ArrayList<String[]>(); 
			try { 
				booknames = engine.getBookNames(query); 
			}catch (Exception e) {
				event.getChannel().sendMessage("Could not search book: Some error occured").queue(); 
				e.printStackTrace(); 
			} 
			String suggestedBooks="I guess its \n";
			event.getChannel().sendMessage(suggestedBooks).queue();
			booknames.forEach(strings->{event.getChannel().sendMessage(strings[1]+"\nhttps://www.goodreads.com/book/show/"+strings[0]+"\n").queue(); });
		}
	}
}
