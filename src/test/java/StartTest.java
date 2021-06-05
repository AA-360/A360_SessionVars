import com.automationanywhere.botcommand.samples.commands.basic.*;
import org.testng.annotations.Test;
import com.automationanywhere.botcommand.data.impl.StringValue;

import javax.swing.*;

public class StartTest {
    @Test
    public void teste(){
        Start a = new Start();
        SetTextVariable tv = new SetTextVariable();
        GetTextVariable gt = new GetTextVariable();
        SetBooleanVariable bv = new SetBooleanVariable();
        SetNumberVariable nv = new SetNumberVariable();
        GetNumberVariable gn = new GetNumberVariable();
        GetBooleanVariable gb = new GetBooleanVariable();


        //a.action();
        tv.action("teste","meu texto aqui",false);
        tv.action("teste","meu texto aqui2",true);
        nv.action("numero",1.56,true);
        bv.action("prod",false,true);


        //this.alert(Double.toString(gn.action("numero").get()+1));
        //this.alert(gb.action("prod").toString());
        this.alert(gn.action("numero").toString());

        System.out.println("==================");
    }

    private void alert(String text){
        JOptionPane.showMessageDialog(null, text, "InfoBox: Title", JOptionPane.INFORMATION_MESSAGE);
    }
}