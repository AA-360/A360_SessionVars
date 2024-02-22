package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class SetDefaultVariablesCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(SetDefaultVariablesCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    SetDefaultVariables command = new SetDefaultVariables();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("botName") && parameters.get("botName") != null && parameters.get("botName").get() != null) {
      convertedParameters.put("botName", parameters.get("botName").get());
      if(convertedParameters.get("botName") !=null && !(convertedParameters.get("botName") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","botName", "String", parameters.get("botName").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("botName") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","botName"));
    }

    if(parameters.containsKey("mode") && parameters.get("mode") != null && parameters.get("mode").get() != null) {
      convertedParameters.put("mode", parameters.get("mode").get());
      if(convertedParameters.get("mode") !=null && !(convertedParameters.get("mode") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","mode", "String", parameters.get("mode").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("mode") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","mode"));
    }

    if(parameters.containsKey("mainTask") && parameters.get("mainTask") != null && parameters.get("mainTask").get() != null) {
      convertedParameters.put("mainTask", parameters.get("mainTask").get());
      if(convertedParameters.get("mainTask") !=null && !(convertedParameters.get("mainTask") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","mainTask", "String", parameters.get("mainTask").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("mainTask") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","mainTask"));
    }

    try {
      command.action((String)convertedParameters.get("botName"),(String)convertedParameters.get("mode"),(String)convertedParameters.get("mainTask"));Optional<Value> result = Optional.empty();
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
