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

import com.automationanywhere.botcommand.data.impl.NumberValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.uteis;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;
import org.ini4j.Ini;

import static com.automationanywhere.commandsdk.model.AttributeType.SELECT;
import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.NUMBER;
import static com.automationanywhere.commandsdk.model.DataType.STRING;
//import MaskFormatter;


//import java.Math;
//import Math;

@BotCommand
@CommandPkg(
        label = "GetNumberVariable",
        node_label = "Get Number {{varName}} key",
        description = "",
        icon = "pkg.svg",
        name = "GetNumberVariable",
        return_type = NUMBER,
        return_required = true
)


public class GetNumberVariable {

    @Execute
    public NumberValue action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "Key",description = "Key")
            String varName,
            @Idx(index = "2", type = SELECT, options = {
                    @Idx.Option(index = "2.1", pkg = @Pkg(label = "Session", value = "s")),
                    @Idx.Option(index = "2.2", pkg = @Pkg(label = "LocalStorage", value = "l"))})
            @Pkg(label = "Rule:", description = "Session-> cleared each execution\n LocalStorage->keep its value on VM", default_value = "s", default_value_type = DataType.STRING)
            @NotEmpty
                    String type
    ) {

        uteis ut = new uteis();
        Ini ini = ut.getIniFile(type);

        //======================VALIDANDO SE JA EXISTE============
        if(!ut.variableExists(ini,varName)){
            throw new BotCommandException("Variavel '" + varName + "' nao encontrada!");
        }

        ut.validateType(ini,varName,"number");

        return new NumberValue(Double.parseDouble(ini.get(varName,"value")));

        //JOptionPane.showMessageDialog(null, pid, "InfoBox: Title", JOptionPane.INFORMATION_MESSAGE);
    }
}
