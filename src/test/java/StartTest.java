import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.model.property.Property;
import com.automationanywhere.botcommand.samples.commands.basic.*;
import com.automationanywhere.botcommand.samples.commands.conditional.ListKey;
import org.apache.commons.codec.digest.HmacUtils;
import org.testng.annotations.Test;
import com.automationanywhere.botcommand.data.impl.StringValue;

import javax.swing.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

import org.jooq.lambda.Unchecked;


public class StartTest {

    @Test
    public void prop(){
        Set<Property> a = new HashSet<Property>();
        a.add(new Property("hehe",new StringValue("Val1"),true));
        a.add(new Property("hehe2",new StringValue("Val2"),true));
        a.add(new Property("hehe3",new StringValue("Val3"),true));

        SetManyTextVariable sm = new SetManyTextVariable();

        sm.action(a,"s",true);



    }


    public void teste(){
        Start a = new Start();
        SetTextVariable tv = new SetTextVariable();
        GetTextVariable gt = new GetTextVariable();
        SetBooleanVariable bv = new SetBooleanVariable();
        SetListVariable lv = new SetListVariable();
        SetNumberVariable nv = new SetNumberVariable();
        GetNumberVariable gn = new GetNumberVariable();
        GetBooleanVariable gb = new GetBooleanVariable();
        GetListVariable gl = new GetListVariable();
        ListKey lval = new ListKey();
        AddToListVariable alv = new AddToListVariable();
        DeleteFromListVariable dlv = new DeleteFromListVariable();
        ClearListVariable clv = new ClearListVariable();


        List<Value> vals = new ArrayList<Value>();

        vals.add(new StringValue("OK"));
        vals.add(new StringValue("ABC"));

        //a.action();
        tv.action("teste","meu texto aqui","s",false);
        //tv.action("teste","meu texto aqui2",true);
        //bv.action("numero",1.56,true,"s");
        //bv.action("prod",false,true,"s");
        //bv.action("prod",false,"l",true);
        //lv.action("lista",vals,"l",true);
        //dlv.action("lista","OK","l");
        //alv.action("lista","NODK","l");
        //clv.action("lista","l");


        //this.alert(Double.toString(gn.action("numero").get()+1));
        //this.alert(lval.validate("lista","=","OK","","l").toString());
        //this.alert(gl.action("lista","l").get(0).toString());

        System.out.println("==================");
    }

    private void alert(String text){
        JOptionPane.showMessageDialog(null, text, "InfoBox: Title", JOptionPane.INFORMATION_MESSAGE);
    }
}