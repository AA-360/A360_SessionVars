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

import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.uteis;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;
import org.ini4j.Ini;

import java.util.ArrayList;
import java.util.List;

import static com.automationanywhere.commandsdk.model.AttributeType.SELECT;
import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.LIST;
import static com.automationanywhere.commandsdk.model.DataType.STRING;
//import MaskFormatter;


//import java.Math;
//import Math;

@BotCommand
@CommandPkg(
        label = "ClearListVariable",
        node_label = "Clear {{key}} key list",
        description = "",
        icon = "pkg.svg",
        name = "ClearListVariable",
        return_type = LIST,
        return_required = true,
        return_sub_type = STRING
)


public class ClearListVariable {

    @Execute
    public ListValue<String> action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "Key",description = "Key")
            @NotEmpty
                    String key,
            @Idx(index = "2", type = SELECT, options = {
                    @Idx.Option(index = "2.1", pkg = @Pkg(label = "Session", value = "s")),
                    @Idx.Option(index = "2.2", pkg = @Pkg(label = "LocalStorage", value = "l"))})
            @Pkg(label = "Rule:", description = "Session-> cleared each execution\n LocalStorage->keep its value on VM", default_value = "s", default_value_type = DataType.STRING)
            @NotEmpty
                    String type
    ) {
        try{
            uteis ut = new uteis();
            Ini ini = ut.getIniFile(type);
            ListValue<String> OUTPUT = new ListValue<String>();
            List<String> RowListValues = new ArrayList<>();

            //======================VALIDANDO SE JA EXISTE============
            if(!ut.variableExists(ini,key)){
                throw new BotCommandException("Variavel '" + key + "' nao encontrada!");
            }

            ut.validateType(ini,key,"list");
            Boolean isConstant = Boolean.parseBoolean(ini.get(key,"constant"));
            ut.storeKeyListString(ini,key,RowListValues,isConstant);
            OUTPUT = ut.getKeyListValue(ini,key);
            return OUTPUT;
        }
            catch (Exception e){
            throw new BotCommandException("Erro ao gravar variavel: " + e.getMessage());
        }

        //JOptionPane.showMessageDialog(null, pid, "InfoBox: Title", JOptionPane.INFORMATION_MESSAGE);
    }
}
