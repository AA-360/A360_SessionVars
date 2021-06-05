package com.automationanywhere.botcommand.samples.commands.utils;

import com.automationanywhere.botcommand.exception.BotCommandException;
import com.sun.jna.platform.win32.Kernel32;
import org.apache.commons.io.FileUtils;
import org.ini4j.Ini;

import java.io.File;

public class uteis {
    public uteis(){}

    public String fileName(){
        int pid = Kernel32.INSTANCE.GetCurrentProcessId();
        String tempDir = System.getProperty("java.io.tmpdir") + "GlobalVars/";
        String fileName = tempDir + Integer.toString(pid) + "_global.ini";
        return fileName;
    }

    public Ini getIniFile() {
        String fileName = this.fileName();
        Ini ini;

        try{
            File iniFile = new File(fileName);
            if(!iniFile.exists()){
                iniFile.getParentFile().mkdirs();
                FileUtils.cleanDirectory(iniFile.getParentFile());
                iniFile.createNewFile();
            }
            return new Ini(iniFile);
        }
        catch (Exception e){
            throw new BotCommandException("Sessao nao iniciada!:\n" + e.getMessage());
        }
    }

    public boolean variableExists(Ini ini,String section){

        for (String sectionName: ini.keySet()) {
            if(sectionName.equals(section)){
                return true;
            }
        }
        return false;
    }

    public void validate(Ini ini,String varName,String type_){
        if(this.variableExists(ini,varName)){
            Boolean constant = Boolean.parseBoolean(ini.get(varName,"constant"));
            if(!constant) {
               this.validateType(ini,varName,type_);
            }else{
                throw new BotCommandException("Esta variavel ja foi declarada como constante e nao pode ser alterada!");
            }
        }
    }

    public void validateType(Ini ini,String varName,String type_){
        String type = ini.get(varName,"type");
        if (!type.equals(type_)) {
            throw new BotCommandException("Tipos incompativeis: a variavel '" + varName + "'(" + type + ") nao corresponde ao tipo "+ type_);
        }
    }

}
