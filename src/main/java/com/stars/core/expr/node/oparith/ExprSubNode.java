package com.stars.core.expr.node.oparith;

import com.stars.core.expr.ExprConfig;
import com.stars.core.expr.ExprContext;
import com.stars.core.expr.node.ExprNode;

public class ExprSubNode extends ExprNode {

    private ExprNode l;
    private ExprNode r;

    public ExprSubNode(ExprConfig config, ExprNode l, ExprNode r) {
        super(config);
        this.l = l;
        this.r = r;
    }

    @Override
    public Object eval(Object obj, ExprContext ctx) {
        return (long) l.eval(obj, null) - (long) r.eval(obj, null);
    }

    @Override
    public String inorderString() {
        return String.format("(%s,%s,%s)", "Sub", l.inorderString(), r.inorderString());
    }
}
