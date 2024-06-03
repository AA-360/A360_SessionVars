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

import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.uteis;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;
import org.ini4j.Ini;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
//import MaskFormatter;


//import java.Math;
//import Math;

@BotCommand
@CommandPkg(label = "SetDefaultVariables",
        node_label = "botName: {{botName}}, Mode: {{mode}}, MainTask: {{mainTask}}",
        description = "The Variables will be set as Constant on Session mode",
        icon = "pkg.svg",
        name = "SetDefaultVariables"
)


public class SetDefaultVariables {

    @Execute
    public void action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "BotName",description = "BotName")
            @NotEmpty
            String botName,
            @Idx(index = "2", type = TEXT)
            @Pkg(label = "Mode",description = "Mode")
            @NotEmpty
            String mode,
            @Idx(index = "3", type = TEXT)
            @Pkg(label = "MainTask",description = "MainTask")
            @NotEmpty
            String mainTask
    ) {
        SetTextVariable st = new SetTextVariable();

        st.action("BOTNAME",botName,"s",true);
        st.action("MODE",mode,"s",true);
        st.action("MAINTASK",mainTask,"s",true);

    }


}
