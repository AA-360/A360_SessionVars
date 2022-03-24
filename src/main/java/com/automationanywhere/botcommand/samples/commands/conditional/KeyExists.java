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
        import static com.automationanywhere.commandsdk.model.AttributeType.SELECT;
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
        import com.automationanywhere.commandsdk.model.DataType;
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
@BotCommand(commandType = Condition)
@CommandPkg(label = "KeyExists", name = "KeyExists",
        description = "Verifica se a key existe",
        node_label = "{{value}}: Check {{varName}} key"
)
public class KeyExists {

    @ConditionTest
    public Boolean validate(
            @Idx(index = "1", type = AttributeType.TEXT)
            @VariableType(STRING)
            @Pkg(label = "Key", default_value_type = STRING)
            @NotEmpty
            String varName,
            @Idx(index = "2", type = SELECT, options = {
                    @Idx.Option(index = "2.1", pkg = @Pkg(label = "Existe", value = "KeyExists")),
                    @Idx.Option(index = "2.2", pkg = @Pkg(label = "Não existe", value = "KeyNotExists"))})
            @Pkg(label = "condição:", description = "", default_value = "KeyExists", default_value_type = DataType.STRING)
            @NotEmpty
                    String value,
            @Idx(index = "3", type = SELECT, options = {
                    @Idx.Option(index = "3.1", pkg = @Pkg(label = "Session", value = "s")),
                    @Idx.Option(index = "3.2", pkg = @Pkg(label = "LocalStorage", value = "l"))})
            @Pkg(label = "Rule:", description = "Session-> cleared each execution\n LocalStorage->keep its value on VM", default_value = "s", default_value_type = DataType.STRING)
            @NotEmpty
                    String type
    ) {
        uteis ut = new uteis();
        Ini ini = ut.getIniFile(type);

        return ut.variableExists(ini,varName) == value.equals("KeyExists") ;
    }

}
