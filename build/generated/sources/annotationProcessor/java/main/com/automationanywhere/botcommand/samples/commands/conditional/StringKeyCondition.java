package com.automationanywhere.botcommand.samples.commands.conditional;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.Condition;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class StringKeyCondition implements Condition {
  private static final Logger logger = LogManager.getLogger(StringKeyCondition.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  public boolean test(Map<String, Value> parameters) {
    return test(null, parameters, null);
  }

  public boolean test(GlobalSessionContext globalSessionContext, Map<String, Value> parameters,
      Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    StringKey command = new StringKey();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("varName") && parameters.get("varName") != null && parameters.get("varName").get() != null) {
      convertedParameters.put("varName", parameters.get("varName").get());
      if(convertedParameters.get("varName") !=null && !(convertedParameters.get("varName") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","varName", "String", parameters.get("varName").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("varName") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","varName"));
    }

    if(parameters.containsKey("select") && parameters.get("select") != null && parameters.get("select").get() != null) {
      convertedParameters.put("select", parameters.get("select").get());
      if(convertedParameters.get("select") !=null && !(convertedParameters.get("select") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","select", "String", parameters.get("select").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("select") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","select"));
    }
    if(convertedParameters.get("select") != null) {
      switch((String)convertedParameters.get("select")) {
        case "=" : {

        } break;
        case "≠" : {

        } break;
        case "in" : {

        } break;
        case "not in" : {

        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","select"));
      }
    }

    if(parameters.containsKey("compare") && parameters.get("compare") != null && parameters.get("compare").get() != null) {
      convertedParameters.put("compare", parameters.get("compare").get());
      if(convertedParameters.get("compare") !=null && !(convertedParameters.get("compare") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","compare", "String", parameters.get("compare").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("compare") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","compare"));
    }

    try {
      boolean result = command.validate((String)convertedParameters.get("varName"),(String)convertedParameters.get("select"),(String)convertedParameters.get("compare"));
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","validate"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(), e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }
}
