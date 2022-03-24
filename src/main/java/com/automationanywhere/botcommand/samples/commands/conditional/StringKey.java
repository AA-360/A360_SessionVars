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

import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.uteis;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.annotations.rules.VariableType;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import org.ini4j.Ini;

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
        label = "StringKey",
        name = "StringKey",
        description = "Compara uma varaivel de texto",
        node_label = "StringKey:{{varName}} {{select}} {{compare}}"
)
public class StringKey {

    @ConditionTest
    public Boolean validate(
            @Idx(index = "1", type = AttributeType.TEXT)
            @VariableType(STRING)
            @Pkg(label = "Key", default_value_type = STRING)
            @NotEmpty
            String varName,
            @Idx(index = "2", type = AttributeType.SELECT, options = {
                    @Idx.Option(index ="2.1", pkg = @Pkg(label = "Igual a (=)", value = "=")),
                    @Idx.Option(index ="2.2", pkg = @Pkg(label = "Diferente de (≠)", value = "≠")),
                    @Idx.Option(index ="2.3", pkg = @Pkg(label = "Inclui", value = "in")),
                    @Idx.Option(index ="2.4", pkg = @Pkg(label = "Não Inclui", value = "not in"))
            })
            @Pkg(label = "Operador",default_value_type = STRING,default_value = "=")
            @NotEmpty
            String select,
            @Idx(index = "3", type = AttributeType.TEXT)
            @VariableType(STRING)
            @Pkg(label = "Valor de destino", default_value_type = STRING)
            @NotEmpty
            String compare,
            @Idx(index = "4", type = SELECT, options = {
                    @Idx.Option(index = "4.1", pkg = @Pkg(label = "Session", value = "s")),
                    @Idx.Option(index = "4.2", pkg = @Pkg(label = "LocalStorage", value = "l"))})
            @Pkg(label = "Rule:", description = "Session-> cleared each execution\n LocalStorage->keep its value on VM", default_value = "s", default_value_type = DataType.STRING)
            @NotEmpty
                    String type
    ) {
        uteis ut = new uteis();
        Ini ini = ut.getIniFile(type);

        //======================VALIDANDO SE JA EXISTE============
        if(!ut.variableExists(ini,varName)){
            throw new BotCommandException("Variavel nao encontrada!");
        }

        ut.validateType(ini,varName,"text");

        String value = ini.get(varName,"value");

        if(select.equals("=")){
            return value.equals(compare);
        }else if(select.equals("≠")){
            return !value.equals(compare);
        }else if(select.equals("in")){
            return value.contains(compare);
        }else{
            return !value.contains(compare);
        }

    }

}
