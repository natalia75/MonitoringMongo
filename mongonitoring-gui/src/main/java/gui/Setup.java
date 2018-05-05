package gui;

public interface Setup<T> {
    void setupFun(T t);

    static <T> T setup(T t, Setup<T> iSetup){
        iSetup.setupFun(t);
        return t;
    }
}
