package Controller;

import View.*;
import Model.*;

public class Controller {

    ModelInterface m;
    View v;

    public Controller(ModelInterface m, View v)
    {
        this.m = m;
        this.v = v;

        v.addInputParser(new InputFileParser(m));
        v.addInputTabChangeListener(new InputTabChangeListener(m, v));

        v.addQualityListener(new QualityListener(m));

        if (m.isBLASTMode())
        {
            v.addReferenceHeaderParser(new ReferenceHeaderParser(m));
        }
        // Jack is trying SVN out!
     
    }
}
