package Controller;

import View.*;
import Model.*;

public class Controller {

    Model m;
    View v;

    public Controller(Model m, View v)
    {
        this.m = m;
        this.v = v;

        v.addInputParser(new InputFileParser(m));
        v.addInputTabChangeListener(new InputTabChangeListener(m, v));

        if (m.isBLASTMode())
        {
            v.addReferenceHeaderParser(new ReferenceHeaderParser(m));
        }
    }
}
