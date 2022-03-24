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

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.uteis;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;
import org.apache.commons.lang3.StringUtils;
import org.ini4j.Ini;

import java.util.List;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
//import MaskFormatter;


//import java.Math;
//import Math;

@BotCommand
@CommandPkg(label = "SetListVariable",
        node_label = "Set {{varName}} key to {{value}}",
        description = "",
        icon = "pkg.svg",
        name = "SetListVariable"
)


public class SetListVariable {

    @Execute
    public void action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "Key",description = "Key")
            @NotEmpty
            String key,
            @Idx(index = "2", type = LIST)
            @Pkg(label = "Value",description = "Value")
            @NotEmpty
                    List<Value> value,
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
            ut.validate(ini,key,"list");
            //=======================GRAVANDO VARIAVEL================
            ut.storeKeyListValue(ini,key,value,isConstant);
        }
        catch (Exception e){
            throw new BotCommandException("Erro ao gravar variavel: " + e.getMessage());
        }
        //JOptionPane.showMessageDialog(null, pid, "InfoBox: Title", JOptionPane.INFORMATION_MESSAGE);
        
    }


}
