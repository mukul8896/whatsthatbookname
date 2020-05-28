package mukul.whatsthatbookname;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

/**
 * JDA bot main class
 * main -> building bot then adding listener to bot 
 * @author Mukul
 *
 */
public class WhatTheBookNameBot {
	public static JDA jda;
	public static void main(String[] args) throws LoginException, IllegalArgumentException {
		jda= new JDABuilder(AccountType.BOT).setToken("NzE0ODQ3MDY1OTEzNTU3MDEz.Xs8_WA.sqSA_b6-Z2ec7o5UZ0G61ProIl8").build();
		jda.addEventListener(new Commands());
	}
}
