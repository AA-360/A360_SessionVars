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

import static com.automationanywhere.commandsdk.annotations.BotCommand.CommandType.Condition;
import static com.automationanywhere.commandsdk.model.DataType.BOOLEAN;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import com.automationanywhere.botcommand.data.impl.BooleanValue;
import com.automationanywhere.botcommand.samples.commands.utils.uteis;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.ConditionTest;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.annotations.rules.VariableType;
import com.automationanywhere.commandsdk.model.AttributeType;
import org.ini4j.Ini;

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
//@BotCommand(commandType = Condition)
@CommandPkg(label = "NotKeyExists", name = "NotKeyExists",
        description = "Verifica se a key nao existe")
public class NotKeyExists {

    @ConditionTest
    public Boolean validate(
            @Idx(index = "1", type = AttributeType.TEXT)
            @VariableType(STRING)
            @Pkg(label = "Key", default_value_type = STRING)
            @NotEmpty
                    String varName
    ) {
        uteis ut = new uteis();
        Ini ini = ut.getIniFile();

        return !ut.variableExists(ini,varName) ;
    }

}
