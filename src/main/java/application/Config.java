package application;

public class Config {
    public static final String rootPath = "src/main/java/";
    public static final String rootResourcePath = "src/main/resources/";
    public static final int defaultPort = 5000;
    public static Router router = new Router(new SpinachTestsRouteFactory().makeRoutes());

}
