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
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.uteis;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;
import org.ini4j.Ini;

import java.util.ArrayList;
import java.util.List;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
import static com.automationanywhere.commandsdk.model.DataType.LIST;
import static com.automationanywhere.commandsdk.model.DataType.STRING;
//import MaskFormatter;


//import java.Math;
//import Math;

@BotCommand
@CommandPkg(
        label = "AddToListVariable",
        node_label = "Add {{value}} to {{varName}} key list",
        description = "",
        icon = "pkg.svg",
        name = "AddToListVariable",
        return_type = LIST,
        return_sub_type = STRING
)


public class AddToListVariable {

    @Execute
    public ListValue<String> action(
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
                    String type
    ) {
        try{
            uteis ut = new uteis();
            Ini ini = ut.getIniFile(type);
            ListValue<String> OUTPUT = new ListValue<String>();

            //======================VALIDANDO SE JA EXISTE============
            if(!ut.variableExists(ini,varName)){
                throw new BotCommandException("Variavel '" + varName + "' nao encontrada!");
            }

            ut.validateType(ini,varName,"list");
            String csv = ini.get(varName,"value");
            ini.put(varName,"value",csv + "-;-" + value);
            ini.store();
            OUTPUT = ut.getKeyListValue(ini,varName);

            return OUTPUT;
        }
            catch (Exception e){
            throw new BotCommandException("Erro ao gravar variavel: " + e.getMessage());
        }

        //JOptionPane.showMessageDialog(null, pid, "InfoBox: Title", JOptionPane.INFORMATION_MESSAGE);
    }
}
