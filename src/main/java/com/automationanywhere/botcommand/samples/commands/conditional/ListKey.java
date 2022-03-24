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
package com.automationanywhere.botcommand.samples.commands.conditional;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.uteis;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.annotations.rules.VariableType;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import org.ini4j.Ini;

import java.util.ArrayList;
import java.util.List;

import static com.automationanywhere.commandsdk.annotations.BotCommand.CommandType.Condition;
import static com.automationanywhere.commandsdk.model.AttributeType.SELECT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;


/**
 *
 * This example shows how to create an Condition.
 * <p>
 * Here we are checking of the provided boolean value is false.
 *
 *
 * @author Raj Singh Sisodia
 *
 */
@BotCommand(commandType = Condition)
@CommandPkg(
        label = "ListKey",
        name = "ListKey",
        description = "Compara uma varaivel de texto",
        node_label = "ListKey: {{key}} List {{select}} {{in_val}}{{not_in_val}}"
)
public class ListKey {

    @ConditionTest
    public Boolean validate(
            @Idx(index = "1", type = AttributeType.TEXT)
            @VariableType(STRING)
            @Pkg(label = "Key", default_value_type = STRING)
            @NotEmpty
                    String key,
            @Idx(index = "2", type = AttributeType.SELECT, options = {
                    @Idx.Option(index ="2.3", pkg = @Pkg(label = "Inclui", value = "includes")),
                    @Idx.Option(index ="2.4", pkg = @Pkg(label = "Não Inclui", value = "not includes")),
                    @Idx.Option(index ="2.1", pkg = @Pkg(label = "Está vazio", value = "is empty")),
                    @Idx.Option(index ="2.2", pkg = @Pkg(label = "Não está Vazio", value = "is not empty"))
            })
            @Pkg(label = "Operador",default_value_type = STRING,default_value = "includes")
            @NotEmpty
                    String select,
            @Idx(index = "2.3.1", type = AttributeType.TEXT)
            @VariableType(STRING)
            @Pkg(label = "Valor:", default_value_type = STRING)
            @NotEmpty
                    String in_val,
            @Idx(index = "2.4.1", type = AttributeType.TEXT)
            @VariableType(STRING)
            @Pkg(label = "Valor:", default_value_type = STRING)
            @NotEmpty
                    String not_in_val,
            @Idx(index = "3", type = SELECT, options = {
                    @Idx.Option(index = "3.1", pkg = @Pkg(label = "Session", value = "s")),
                    @Idx.Option(index = "3.2", pkg = @Pkg(label = "LocalStorage", value = "l"))})
            @Pkg(label = "Rule:", description = "Session-> cleared each execution\n LocalStorage->keep its value on VM", default_value = "s", default_value_type = DataType.STRING)
            @NotEmpty
                    String type
    ) {
        uteis ut = new uteis();
        Ini ini = ut.getIniFile(type);
        List<String> RowListValues = new ArrayList<>();

        //======================VALIDANDO SE JA EXISTE============
        if(!ut.variableExists(ini,key)){
            throw new BotCommandException("Variavel nao encontrada!");
        }

        ut.validateType(ini,key,"list");
        RowListValues = ut.getKeyListString(ini,key);

        if(select.equals("is empty")){
            return RowListValues.isEmpty();
        }else if(select.equals("is not empty")){
            return !RowListValues.isEmpty();
        }else if(select.equals("includes")){
            return RowListValues.contains(in_val);
        }else{
            return !RowListValues.contains(not_in_val);
        }

    }

}
