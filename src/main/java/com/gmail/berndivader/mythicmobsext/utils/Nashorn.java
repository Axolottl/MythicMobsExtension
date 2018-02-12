package com.gmail.berndivader.mythicmobsext.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.gmail.berndivader.mythicmobsext.Main;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

@SuppressWarnings("restriction")
public class Nashorn {
	static Nashorn nashorn;
	ScriptEngine nash;
	public Invocable invoc;
	public static String scripts;
	public static String filename="Scripts.js";
	
	public Nashorn() {
		nashorn=this;
		Thread.currentThread().setContextClassLoader(Main.getPlugin().getClass().getClassLoader());
		new NashornMythicMobsReload();
		Path p1=Paths.get(Utils.str_PLUGINPATH,filename);
		try {
			scripts=new String(Files.readAllBytes(p1));
			NashornScriptEngineFactory factory=new NashornScriptEngineFactory();
			nash=factory.getScriptEngine();
			nash.eval(scripts);
			invoc=(Invocable)nash;
		} catch (IOException | ScriptException e1) {
			e1.printStackTrace();
		}
		Main.getPlugin().saveResource(filename,false);
	}
	
	public static Nashorn get() {
		return nashorn;
	}

	public Object invoke() {
		return Nashorn.this.invoc;
	}
	
}