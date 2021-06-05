/*
 * Copyright (c) 2020 Automation Anywhere.
 * All rights reserved.
 *
 * This software is the proprietary information of Automation Anywhere.
 * You shall use it only in accordance with the terms of the license agreement
 * you entered into with Automation Anywhere.
 */

/**
 *
 */
package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Execute;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import com.sun.jna.platform.win32.Kernel32;
import org.apache.commons.io.FileUtils;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import java.io.File;
import java.text.ParseException;
//import MaskFormatter;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

//import java.Math;
//import Math;

//@BotCommand
@CommandPkg(label = "Start",
        description = "", icon = "pkg.svg", name = "Start")


public class Start {

    @Execute
    public void action() {

        int pid = Kernel32.INSTANCE.GetCurrentProcessId();
        String tempDir = System.getProperty("java.io.tmpdir") + "GlobalVars/";
        String fileName = tempDir + Integer.toString(pid) + "_global.ini";
        Ini ini;

        try{
            File iniFile = new File(fileName);
            if(!iniFile.exists()){
                iniFile.getParentFile().mkdirs();
                FileUtils.cleanDirectory(iniFile.getParentFile());
                iniFile.createNewFile();
            }
        }
        catch (Exception e){
            throw new BotCommandException("Sessao nao iniciada!:\n" + e.getMessage());
        }
    }
}
