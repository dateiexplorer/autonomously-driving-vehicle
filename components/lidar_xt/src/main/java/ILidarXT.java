public interface ILidarXT {

    String version();

    void scan();

    boolean on();

    boolean off();
}
