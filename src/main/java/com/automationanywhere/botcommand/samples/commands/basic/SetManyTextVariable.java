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
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.model.property.Property;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.EntryList.EntryListAddButtonLabel;
import com.automationanywhere.commandsdk.annotations.rules.EntryList.EntryListEmptyLabel;
import com.automationanywhere.commandsdk.annotations.rules.EntryList.EntryListEntryUnique;
import com.automationanywhere.commandsdk.annotations.rules.EntryList.EntryListLabel;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;

import java.util.List;
import java.util.Set;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
//import MaskFormatter;


//import java.Math;
//import Math;

@BotCommand
@CommandPkg(label = "SetManyTextVariable",
        node_label = "Set many keys",
        description = "",
        icon = "pkg.svg",
        name = "SetManyTextVariable"
)


public class SetManyTextVariable {

    @Execute
    public void action(
            @Idx(index = "1", type = AttributeType.PROPERTIES)
            @Pkg(label = "keys")
            @NotEmpty
            Set<Property> properties,

            @Idx(index = "2", type = SELECT, options = {
                    @Idx.Option(index = "2.1", pkg = @Pkg(label = "Session", value = "s")),
                    @Idx.Option(index = "2.2", pkg = @Pkg(label = "LocalStorage", value = "l"))})
            @Pkg(label = "Rule:", description = "Session-> cleared each execution\n LocalStorage->keep its value on VM", default_value = "s", default_value_type = DataType.STRING)
            @NotEmpty
                    String type,
            @Idx(index = "2.1.1", type = BOOLEAN)
            @Pkg(label = "Constante", description = "Cria a variavel como uma constante",default_value = "false",default_value_type = DataType.BOOLEAN)
            @NotEmpty
                    Boolean isConstant
    ) {
        SetTextVariable st = new SetTextVariable();

        for(Property i: properties){
            st.action(i.getName(),i.getValue().toString(),type,isConstant);
        }

    }


}
