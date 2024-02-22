package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.model.property.Property;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.Boolean;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class SetManyTextVariableCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(SetManyTextVariableCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    SetManyTextVariable command = new SetManyTextVariable();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("properties") && parameters.get("properties") != null && parameters.get("properties").get() != null) {
      convertedParameters.put("properties", parameters.get("properties").get());
      if(convertedParameters.get("properties") !=null && !(convertedParameters.get("properties") instanceof Set)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","properties", "Set", parameters.get("properties").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("properties") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","properties"));
    }

    if(parameters.containsKey("type") && parameters.get("type") != null && parameters.get("type").get() != null) {
      convertedParameters.put("type", parameters.get("type").get());
      if(convertedParameters.get("type") !=null && !(convertedParameters.get("type") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","type", "String", parameters.get("type").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("type") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","type"));
    }
    if(convertedParameters.get("type") != null) {
      switch((String)convertedParameters.get("type")) {
        case "s" : {
          if(parameters.containsKey("isConstant") && parameters.get("isConstant") != null && parameters.get("isConstant").get() != null) {
            convertedParameters.put("isConstant", parameters.get("isConstant").get());
            if(convertedParameters.get("isConstant") !=null && !(convertedParameters.get("isConstant") instanceof Boolean)) {
              throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","isConstant", "Boolean", parameters.get("isConstant").get().getClass().getSimpleName()));
            }
          }
          if(convertedParameters.get("isConstant") == null) {
            throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","isConstant"));
          }


        } break;
        case "l" : {

        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","type"));
      }
    }

    try {
      command.action((Set<Property>)convertedParameters.get("properties"),(String)convertedParameters.get("type"),(Boolean)convertedParameters.get("isConstant"));Optional<Value> result = Optional.empty();
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","action"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(),e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }
}
