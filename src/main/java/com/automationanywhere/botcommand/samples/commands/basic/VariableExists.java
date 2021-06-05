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

import com.automationanywhere.botcommand.data.impl.BooleanValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.uteis;
import com.automationanywhere.commandsdk.annotations.*;
import org.ini4j.Ini;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.BOOLEAN;
import static com.automationanywhere.commandsdk.model.DataType.STRING;
//import MaskFormatter;


//import java.Math;
//import Math;

//@BotCommand
@CommandPkg(
        label = "VariableExists",
        description = "",
        icon = "pkg.svg",
        name = "VariableExists",
        return_type = BOOLEAN,
        return_required = true
)


public class VariableExists {

    @Execute
    public BooleanValue action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "Key",description = "Key")
            String varName
    ) {

        uteis ut = new uteis();
        Ini ini = ut.getIniFile();

        if(!ut.variableExists(ini,varName)){
            return new BooleanValue(false);
        }else{
            return new BooleanValue(true);
        }

    }
}
