package lisp;

public interface Procedure extends Applicable {

    Obj apply(Obj args, Cont cont);
    
    static Obj evlis(Obj args, Env env, Cont cont) {
        return args instanceof Pair
            ? args.car().eval(env, x -> evlis(args.cdr(), env, y -> cont.apply(new Pair(x, y))))
            : cont.apply(List.NIL);
    }

    @Override
    default Obj apply(Obj args, Env env, Cont cont) {
        return evlis(args, env, x -> apply(x, cont));
    }
}
