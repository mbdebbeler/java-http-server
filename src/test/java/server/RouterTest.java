package server;

import org.junit.Test;
import org.junit.Assert;

public class RouterTest {
    @Test
    public void returnsNotFoundWhenRouteDoesNotExist() {
        Request testRequest = new Request("GET /not_found_resource");
        Router testRouter = new Router();
        StatusCode actual = testRouter.route(testRequest).getStatusCode();
        StatusCode expected = StatusCode.NOT_FOUND;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void doesNotReturnNotFoundWhenRouteDoesExist() {
        Request testRequest = new Request("GET /simple_get");
        Router testRouter = new Router();
        StatusCode actual = testRouter.route(testRequest).getStatusCode();
        StatusCode expected = StatusCode.OK;

        Assert.assertEquals(expected, actual);
    }

}
