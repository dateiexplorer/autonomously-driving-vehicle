public interface ILidarNG {

    String version();

    void scan();

    boolean on();

    boolean off();
}
