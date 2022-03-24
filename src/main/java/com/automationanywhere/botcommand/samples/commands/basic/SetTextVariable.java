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

import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.uteis;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;
import com.sun.jna.platform.win32.Kernel32;
import org.apache.commons.io.FileUtils;
import org.ini4j.Ini;

import java.io.File;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
//import MaskFormatter;


//import java.Math;
//import Math;

@BotCommand
@CommandPkg(label = "SetTextVariable",
        node_label = "Set {{varName}} key to {{value}}",
        description = "",
        icon = "pkg.svg",
        name = "SetTextVariable"
)


public class SetTextVariable {

    @Execute
    public void action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "Key",description = "Key")
            @NotEmpty
            String varName,
            @Idx(index = "2", type = TEXT)
            @Pkg(label = "Value",description = "Value")
            @NotEmpty
            String value,
            @Idx(index = "3", type = SELECT, options = {
                    @Idx.Option(index = "3.1", pkg = @Pkg(label = "Session", value = "s")),
                    @Idx.Option(index = "3.2", pkg = @Pkg(label = "LocalStorage", value = "l"))})
            @Pkg(label = "Rule:", description = "Session-> cleared each execution\n LocalStorage->keep its value on VM", default_value = "s", default_value_type = DataType.STRING)
            @NotEmpty
                    String type,
            @Idx(index = "3.1.1", type = BOOLEAN)
            @Pkg(label = "Constante", description = "Cria a variavel como uma constante",default_value = "false",default_value_type = DataType.BOOLEAN)
            @NotEmpty
                    Boolean isConstant
    ) {

        uteis ut = new uteis();
        Ini ini = ut.getIniFile(type);
        isConstant = type.equals("s")?isConstant:false;

        try{
            //======================VALIDANDO SE JA EXISTE============
            ut.validate(ini,varName,"text");
            //=======================GRAVANDO VARIAVEL================
            ini.put(varName,"value",value);
            ini.put(varName,"type","text");
            ini.put(varName,"constant",isConstant);
            ini.store();
        }
        catch (Exception e){
            throw new BotCommandException("Erro ao gravar variavel: " + e.getMessage());
        }
        //JOptionPane.showMessageDialog(null, pid, "InfoBox: Title", JOptionPane.INFORMATION_MESSAGE);
        
    }


}
